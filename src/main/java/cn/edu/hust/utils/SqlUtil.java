package cn.edu.hust.utils;

import java.util.List;

/**
 * Created by lxiao on 2017/2/19.
 * sql工具类
 */
public class SqlUtil {
    /**
     * 将数组拼接为sql查询
     * @param arr 数组
     * @return 如: 'M201476135', 'M201476136'
     */
    public static String arrayToSql(String[] arr) {
        String sql = "";
        for (int i = 0; i < arr.length; i++) {
            sql += "'" + arr[i] + "'";
            if (i < arr.length - 1) {
                sql += ", ";
            }
        }
        return sql;
    }

    public static String listToSql(List<String> list) {
        String[] arr = new String[list.size()];
        int i = 0;
        for (String ss : list) {
            arr[i++] = ss;
        }

        return arrayToSql(arr);
    }
}
