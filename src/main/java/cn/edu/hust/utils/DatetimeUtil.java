package cn.edu.hust.utils;

import org.joda.time.DateTime;

/**
 * Created by lxiao on 2017/2/28.
 */
public class DatetimeUtil {
    public static String getYYYYMMDD() {
        return DateTime.now().toString("yyyyMMdd");
    }
}
