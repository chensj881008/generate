package cn.com.ss.generate.convert.type;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static cn.com.ss.generate.util.StringUtils.stringHasValue;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.ss.generate.convert.type
 * @date 2018-05-22 21:14
 */
public class FullyJavaTypeInfo {

    private static final String JAVA_LANG = "java.lang"; 

    private static FullyJavaTypeInfo intInstance = null;

    private static FullyJavaTypeInfo stringInstance = null;

    private static FullyJavaTypeInfo booleanPrimitiveInstance = null;

    private static FullyJavaTypeInfo objectInstance = null;

    private static FullyJavaTypeInfo dateInstance = null;

    private static FullyJavaTypeInfo criteriaInstance = null;

    private static FullyJavaTypeInfo generatedCriteriaInstance = null;

    /** The short name without any generic arguments. */
    private String baseShortName;

    /** The fully qualified name without any generic arguments. */
    private String baseQualifiedName;

    private boolean explicitlyImported;

    private String packageName;

    private boolean primitive;

    private boolean isArray;

    private PrimitiveTypeWrapper primitiveTypeWrapper;

    private List<FullyJavaTypeInfo> typeArguments;

    // the following three values are used for dealing with wildcard types
    private boolean wildcardType;

    private boolean boundedWildcard;

    private boolean extendsBoundedWildcard;

    /**
     * Use this constructor to construct a generic type with the specified type parameters.
     *
     * @param fullTypeSpecification
     *            the full type specification
     */
    public FullyJavaTypeInfo(String fullTypeSpecification) {
        super();
        typeArguments = new ArrayList<FullyJavaTypeInfo>();
        parse(fullTypeSpecification);
    }

    public boolean isExplicitlyImported() {
        return explicitlyImported;
    }

    /**
     * Returns the fully qualified name - including any generic type parameters.
     *
     * @return Returns the fullyQualifiedName.
     */
    public String getFullyQualifiedName() {
        StringBuilder sb = new StringBuilder();
        if (wildcardType) {
            sb.append('?');
            if (boundedWildcard) {
                if (extendsBoundedWildcard) {
                    sb.append(" extends "); 
                } else {
                    sb.append(" super "); 
                }

                sb.append(baseQualifiedName);
            }
        } else {
            sb.append(baseQualifiedName);
        }

        if (typeArguments.size() > 0) {
            boolean first = true;
            sb.append('<');
            for (FullyJavaTypeInfo fqjt : typeArguments) {
                if (first) {
                    first = false;
                } else {
                    sb.append(", "); 
                }
                sb.append(fqjt.getFullyQualifiedName());

            }
            sb.append('>');
        }

        return sb.toString();
    }

    public String getFullyQualifiedNameWithoutTypeParameters() {
        return baseQualifiedName;
    }

    /**
     * Returns a list of Strings that are the fully qualified names of this type, and any generic type argument
     * associated with this type.
     *
     * @return the import list
     */
    public List<String> getImportList() {
        List<String> answer = new ArrayList<String>();
        if (isExplicitlyImported()) {
            int index = baseShortName.indexOf('.');
            if (index == -1) {
                answer.add(calculateActualImport(baseQualifiedName));
            } else {
                // an inner class is specified, only import the top
                // level class
                StringBuilder sb = new StringBuilder();
                sb.append(packageName);
                sb.append('.');
                sb.append(calculateActualImport(baseShortName.substring(0, index)));
                answer.add(sb.toString());
            }
        }

        for (FullyJavaTypeInfo fqjt : typeArguments) {
            answer.addAll(fqjt.getImportList());
        }

        return answer;
    }

    private String calculateActualImport(String name) {
        String answer = name;
        if (this.isArray()) {
            int index = name.indexOf("["); 
            if (index != -1) {
                answer = name.substring(0, index);
            }
        }
        return answer;
    }

    public String getPackageName() {
        return packageName;
    }

    /**
     * Gets the short name.
     *
     * @return Returns the shortName - including any type arguments.
     */
    public String getShortName() {
        StringBuilder sb = new StringBuilder();
        if (wildcardType) {
            sb.append('?');
            if (boundedWildcard) {
                if (extendsBoundedWildcard) {
                    sb.append(" extends "); 
                } else {
                    sb.append(" super "); 
                }

                sb.append(baseShortName);
            }
        } else {
            sb.append(baseShortName);
        }

        if (typeArguments.size() > 0) {
            boolean first = true;
            sb.append('<');
            for (FullyJavaTypeInfo fqjt : typeArguments) {
                if (first) {
                    first = false;
                } else {
                    sb.append(", "); 
                }
                sb.append(fqjt.getShortName());

            }
            sb.append('>');
        }

        return sb.toString();
    }

    public String getShortNameWithoutTypeArguments() {
        return baseShortName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FullyJavaTypeInfo)) {
            return false;
        }

        FullyJavaTypeInfo other = (FullyJavaTypeInfo) obj;

        return getFullyQualifiedName().equals(other.getFullyQualifiedName());
    }

    @Override
    public int hashCode() {
        return getFullyQualifiedName().hashCode();
    }

    @Override
    public String toString() {
        return getFullyQualifiedName();
    }

    public boolean isPrimitive() {
        return primitive;
    }

    /**
     * Gets the primitive type wrapper.
     *
     * @return Returns the wrapperClass.
     */
    public PrimitiveTypeWrapper getPrimitiveTypeWrapper() {
        return primitiveTypeWrapper;
    }

    public static final FullyJavaTypeInfo getIntInstance() {
        if (intInstance == null) {
            intInstance = new FullyJavaTypeInfo("int"); 
        }

        return intInstance;
    }

    public static final FullyJavaTypeInfo getNewMapInstance() {
        // always return a new instance because the type may be parameterized
        return new FullyJavaTypeInfo("java.util.Map"); 
    }

    public static final FullyJavaTypeInfo getNewListInstance() {
        // always return a new instance because the type may be parameterized
        return new FullyJavaTypeInfo("java.util.List"); 
    }

    public static final FullyJavaTypeInfo getNewHashMapInstance() {
        // always return a new instance because the type may be parameterized
        return new FullyJavaTypeInfo("java.util.HashMap"); 
    }

    public static final FullyJavaTypeInfo getNewArrayListInstance() {
        // always return a new instance because the type may be parameterized
        return new FullyJavaTypeInfo("java.util.ArrayList"); 
    }

    public static final FullyJavaTypeInfo getNewIteratorInstance() {
        // always return a new instance because the type may be parameterized
        return new FullyJavaTypeInfo("java.util.Iterator"); 
    }

    public static final FullyJavaTypeInfo getStringInstance() {
        if (stringInstance == null) {
            stringInstance = new FullyJavaTypeInfo("java.lang.String"); 
        }

        return stringInstance;
    }

    public static final FullyJavaTypeInfo getBooleanPrimitiveInstance() {
        if (booleanPrimitiveInstance == null) {
            booleanPrimitiveInstance = new FullyJavaTypeInfo("boolean"); 
        }

        return booleanPrimitiveInstance;
    }

    public static final FullyJavaTypeInfo getObjectInstance() {
        if (objectInstance == null) {
            objectInstance = new FullyJavaTypeInfo("java.lang.Object"); 
        }

        return objectInstance;
    }

    public static final FullyJavaTypeInfo getDateInstance() {
        if (dateInstance == null) {
            dateInstance = new FullyJavaTypeInfo("java.util.Date"); 
        }

        return dateInstance;
    }

    public static final FullyJavaTypeInfo getCriteriaInstance() {
        if (criteriaInstance == null) {
            criteriaInstance = new FullyJavaTypeInfo("Criteria"); 
        }

        return criteriaInstance;
    }

    public static final FullyJavaTypeInfo getGeneratedCriteriaInstance() {
        if (generatedCriteriaInstance == null) {
            generatedCriteriaInstance = new FullyJavaTypeInfo(
                    "GeneratedCriteria"); 
        }

        return generatedCriteriaInstance;
    }

    public int compareTo(FullyJavaTypeInfo other) {
        return getFullyQualifiedName().compareTo(other.getFullyQualifiedName());
    }

    public void addTypeArgument(FullyJavaTypeInfo type) {
        typeArguments.add(type);
    }

    private void parse(String fullTypeSpecification) {
        String spec = fullTypeSpecification.trim();

        if (spec.startsWith("?")) { 
            wildcardType = true;
            spec = spec.substring(1).trim();
            if (spec.startsWith("extends ")) { 
                boundedWildcard = true;
                extendsBoundedWildcard = true;
                spec = spec.substring(8);  // "extends ".length()
            } else if (spec.startsWith("super ")) { 
                boundedWildcard = true;
                extendsBoundedWildcard = false;
                spec = spec.substring(6);  // "super ".length()
            } else {
                boundedWildcard = false;
            }
            parse(spec);
        } else {
            int index = fullTypeSpecification.indexOf('<');
            if (index == -1) {
                simpleParse(fullTypeSpecification);
            } else {
                simpleParse(fullTypeSpecification.substring(0, index));
                int endIndex = fullTypeSpecification.lastIndexOf('>');
                if (endIndex == -1) {
                   /* throw new RuntimeException(getString(
                            "RuntimeError.22", fullTypeSpecification)); */
                }
                genericParse(fullTypeSpecification.substring(index, endIndex + 1));
            }

            // this is far from a perfect test for detecting arrays, but is close
            // enough for most cases.  It will not detect an improperly specified
            // array type like byte], but it will detect byte[] and byte[   ]
            // which are both valid
            isArray = fullTypeSpecification.endsWith("]"); 
        }
    }

    private void simpleParse(String typeSpecification) {
        baseQualifiedName = typeSpecification.trim();
        if (baseQualifiedName.contains(".")) { 
            packageName = getPackage(baseQualifiedName);
            baseShortName = baseQualifiedName
                    .substring(packageName.length() + 1);
            int index = baseShortName.lastIndexOf('.');
            if (index != -1) {
                baseShortName = baseShortName.substring(index + 1);
            }

            if (JAVA_LANG.equals(packageName)) { 
                explicitlyImported = false;
            } else {
                explicitlyImported = true;
            }
        } else {
            baseShortName = baseQualifiedName;
            explicitlyImported = false;
            packageName = ""; 

            if ("byte".equals(baseQualifiedName)) { 
                primitive = true;
                primitiveTypeWrapper = PrimitiveTypeWrapper.getByteInstance();
            } else if ("short".equals(baseQualifiedName)) { 
                primitive = true;
                primitiveTypeWrapper = PrimitiveTypeWrapper.getShortInstance();
            } else if ("int".equals(baseQualifiedName)) { 
                primitive = true;
                primitiveTypeWrapper = PrimitiveTypeWrapper
                        .getIntegerInstance();
            } else if ("long".equals(baseQualifiedName)) { 
                primitive = true;
                primitiveTypeWrapper = PrimitiveTypeWrapper.getLongInstance();
            } else if ("char".equals(baseQualifiedName)) { 
                primitive = true;
                primitiveTypeWrapper = PrimitiveTypeWrapper
                        .getCharacterInstance();
            } else if ("float".equals(baseQualifiedName)) { 
                primitive = true;
                primitiveTypeWrapper = PrimitiveTypeWrapper.getFloatInstance();
            } else if ("double".equals(baseQualifiedName)) { 
                primitive = true;
                primitiveTypeWrapper = PrimitiveTypeWrapper.getDoubleInstance();
            } else if ("boolean".equals(baseQualifiedName)) { 
                primitive = true;
                primitiveTypeWrapper = PrimitiveTypeWrapper
                        .getBooleanInstance();
            } else {
                primitive = false;
                primitiveTypeWrapper = null;
            }
        }
    }

    private void genericParse(String genericSpecification) {
        int lastIndex = genericSpecification.lastIndexOf('>');
        if (lastIndex == -1) {
            // shouldn't happen - should be caught already, but just in case...
        /*    throw new RuntimeException(getString(
                    "RuntimeError.22", genericSpecification)); */
        }
        String argumentString = genericSpecification.substring(1, lastIndex);
        // need to find "," outside of a <> bounds
        StringTokenizer st = new StringTokenizer(argumentString, ",<>", true); 
        int openCount = 0;
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if ("<".equals(token)) { 
                sb.append(token);
                openCount++;
            } else if (">".equals(token)) { 
                sb.append(token);
                openCount--;
            } else if (",".equals(token)) { 
                if (openCount == 0) {
                    typeArguments
                            .add(new FullyJavaTypeInfo(sb.toString()));
                    sb.setLength(0);
                } else {
                    sb.append(token);
                }
            } else {
                sb.append(token);
            }
        }

        if (openCount != 0) {
          /*  throw new RuntimeException(getString(
                    "RuntimeError.22", genericSpecification)); */
        }

        String finalType = sb.toString();
        if (stringHasValue(finalType)) {
            typeArguments.add(new FullyJavaTypeInfo(finalType));
        }
    }

    /**
     * Returns the package name of a fully qualified type.
     *
     * <p>This method calculates the package as the part of the fully qualified name up to, but not including, the last
     * element. Therefore, it does not support fully qualified inner classes. Not totally fool proof, but correct in
     * most instances.
     *
     * @param baseQualifiedName
     *            the base qualified name
     * @return the package
     */
    private static String getPackage(String baseQualifiedName) {
        int index = baseQualifiedName.lastIndexOf('.');
        return baseQualifiedName.substring(0, index);
    }

    public boolean isArray() {
        return isArray;
    }

    public List<FullyJavaTypeInfo> getTypeArguments() {
        return typeArguments;
    }
}
