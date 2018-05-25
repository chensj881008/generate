package cn.com.ss.customer.generate.domain;

import cn.com.ss.customer.generate.util.FullyQualifiedJavaType;
import cn.com.ss.customer.generate.util.JavaVisibility;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.ss.customer.generate.domain
 * @date 2018-05-25 21:07
 */
public class Method {


    public Method() {
        parameters = new ArrayList<>();
        exceptions = new ArrayList<>();
        bodyLines = new ArrayList<>();
    }

    /**
     * 方法的访问权限
     */
    private JavaVisibility visibility;

    private List<String> bodyLines;

    /** 是否是构造方法 */
    private boolean constructor;

    /** 返回类型 */
    private FullyQualifiedJavaType returnType;

    /** 方法名 */
    private String name;

    /** 参数信息 */
    private List<Parameter> parameters;

    /** 错误 */
    private List<FullyQualifiedJavaType> exceptions;

    private  StringBuilder javaDocLine;


   public List<String> getBodyLines() {
        return bodyLines;
    }

    public void setBodyLines(List<String> bodyLines) {
        this.bodyLines = bodyLines;
    }

    public boolean isConstructor() {
        return constructor;
    }

    public void setConstructor(boolean constructor) {
        this.constructor = constructor;
    }

    public FullyQualifiedJavaType getReturnType() {
        return returnType;
    }

    public void setReturnType(FullyQualifiedJavaType returnType) {
        this.returnType = returnType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<FullyQualifiedJavaType> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<FullyQualifiedJavaType> exceptions) {
        this.exceptions = exceptions;
    }

    public JavaVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(JavaVisibility visibility) {
        this.visibility = visibility;
    }

    public void addBodyLine(String body){
        bodyLines.add(body);
    }

    public StringBuilder getJavaDocLine() {
        return javaDocLine;
    }

    public void setJavaDocLine(StringBuilder javaDocLine) {
        this.javaDocLine = javaDocLine;
    }

    public void addJavaDocLine(String doc){
        this.javaDocLine.append(doc);
    }

    public String generateJavaDocLine(){
        return  this.javaDocLine.toString();
    }
}
