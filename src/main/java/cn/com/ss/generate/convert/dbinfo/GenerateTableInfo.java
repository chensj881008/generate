package cn.com.ss.generate.convert.dbinfo;

import java.util.*;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.ss.generate.convert.dbinfo
 * @date 2018-05-22 21:48
 */
public class GenerateTableInfo {

    List<DBColumnInfo> primaryKeyColumns;

    protected List<DBColumnInfo> baseColumns;

    protected List<DBColumnInfo> blobColumns;

    protected Map<String, Object> attributes;

    public GenerateTableInfo() {
        this.primaryKeyColumns = new ArrayList<>();
        this.baseColumns = new ArrayList<>();
        this.blobColumns = new ArrayList<>();
        this.attributes =  new HashMap<String, Object>();
    }

    public void addPrimaryKeyColumn(String columnName) {
        boolean found = false;
        // first search base columns
        Iterator<DBColumnInfo> iter = baseColumns.iterator();
        while (iter.hasNext()) {
            DBColumnInfo introspectedColumn = iter.next();
            if (introspectedColumn.getActualColumnName().equals(columnName)) {
                primaryKeyColumns.add(introspectedColumn);
                iter.remove();
                found = true;
                break;
            }
        }

        // search blob columns in the weird event that a blob is the primary key
        if (!found) {
            iter = blobColumns.iterator();
            while (iter.hasNext()) {
                DBColumnInfo introspectedColumn = iter.next();
                if (introspectedColumn.getActualColumnName().equals(columnName)) {
                    primaryKeyColumns.add(introspectedColumn);
                    iter.remove();
                    found = true;
                    break;
                }
            }
        }
    }
}
