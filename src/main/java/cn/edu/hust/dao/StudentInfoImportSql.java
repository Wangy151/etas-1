package cn.edu.hust.dao;

import org.apache.ibatis.jdbc.SQL;

/**
 * Created by xiaolei03 on 17/1/5.
 */
public class StudentInfoImportSql {

    public String getInsertSql() {
        return new SQL()
                .INSERT_INTO("student_info_import")
                .INTO_COLUMNS("xh", "name", "csrq", "yjxkdm", "yjxkmc")
                .INTO_COLUMNS("ejxkdm", "ejxkmc", "ds", "lwtm", "rxnf")
                .INTO_COLUMNS("hxwsj", "dbsj")
                .INTO_VALUES("#{model.xh}", "#{model.name}", "#{model.csrq}", "#{model.yjxkdm}", "#{model.yjxkmc}")
                .INTO_VALUES("#{model.ejxkdm}", "#{model.ejxkmc}", "#{model.ds}", "#{model.lwtm}", "#{model.rxnf}")
                .INTO_VALUES("#{model.hxwsj}", "#{model.dbsj}")
                .toString();
    }

}
