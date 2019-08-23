package cn.com.ss.customer.generate.util;

/**
 * @author chenshijie
 * @title java方法权限
 * @email chensj@winning.com.cm
 * @package cn.com.ss.customer.generate.util
 * @date 2018-05-25 21:13
 */
public enum JavaVisibility {

    PUBLIC("public "), //$NON-NLS-1$
    PRIVATE("private "), //$NON-NLS-1$
    PROTECTED("protected "), //$NON-NLS-1$
    DEFAULT(""); //$NON-NLS-1$

    private String value;

    private JavaVisibility(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
