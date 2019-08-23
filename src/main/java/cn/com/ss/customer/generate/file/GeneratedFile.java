package cn.com.ss.customer.generate.file;

/**
 * @author chenshijie
 * @title 文件生成抽象类
 * @email chensj@winning.com.cm
 * @package cn.com.ss.customer.generate.file
 * @date 2018-05-25 20:31
 */
public abstract class GeneratedFile {
    /**
     * 文件存放的路径
     */
    protected String targetProject;

    /**
     * 构造方法
     * @param targetProject
     *            the target project
     */
    public GeneratedFile(String targetProject) {
        super();
        this.targetProject = targetProject;
    }

    /**
     * 返回生成的文件的全部内容
     *
     * @return Returns the content.
     */
    public abstract String getFormattedContent();

    /**
     * 获取文件的名称
     * @return Returns the file name.
     */
    public abstract String getFileName();

    /**
     *
     * @return the target project
     */
    public String getTargetProject() {
        return targetProject;
    }

    /**
     * 文件存储的包
     * @return Returns the target project.
     */
    public abstract String getTargetPackage();

    /**
     * 获取文件的内容
     */
    @Override
    public String toString() {
        return getFormattedContent();
    }

    /**
     * 检查是否合并
     * @return true, if is mergeable
     */
    public abstract boolean isMergeable();
}
