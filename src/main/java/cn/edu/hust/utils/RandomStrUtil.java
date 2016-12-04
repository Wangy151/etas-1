package cn.edu.hust.utils;

import java.util.Random;

/**
 * Created by xiaolei03 on 16/12/3.
 */
public class RandomStrUtil {
    /**
     * 随机生成字符串
     * @param length length表示生成字符串的长度
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
