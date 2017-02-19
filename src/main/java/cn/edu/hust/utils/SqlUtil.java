package cn.edu.hust.utils;

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
}
