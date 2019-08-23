package cn.com.ss.customer.generate.file.java;

import cn.com.ss.customer.generate.domain.TableInfo;
import cn.com.ss.customer.generate.file.GeneratedFile;

/**
 * @author chenshijie
 * @title java文件生成
 * @email chensj@winning.com.cm
 * @package cn.com.ss.customer.generate.file.java
 * @date 2018-05-25 20:35
 */
public class GeneratedJavaFile extends GeneratedFile {

    /** 文件编码. */
    private String fileEncoding;

    private TableInfo tableInfo;

    /**
     * 构造方法
     * @param tableInfo
     */
    public GeneratedJavaFile(TableInfo tableInfo) {
        super(tableInfo.getDomainPath());
        this.tableInfo = tableInfo;
    }

    @Override
    public String getFormattedContent() {
        return null;
    }

    @Override
    public String getFileName() {
        return tableInfo.getDomainName() + ".java";
    }

    @Override
    public String getTargetPackage() {
        return tableInfo.getDomainPackage();
    }

    @Override
    public boolean isMergeable() {
        return false;
    }
}
