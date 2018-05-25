package cn.com.ss.customer.generate.code;

import cn.com.ss.customer.generate.domain.TableInfo;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.ss.customer.generate.file
 * @date 2018-05-25 20:52
 */
public abstract class AbstractGenerator {

    private TableInfo tableInfo;

    public TableInfo getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }
}
