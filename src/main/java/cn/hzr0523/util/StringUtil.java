package cn.hzr0523.util;

/**
 * hezhi
 * 2018/5/3 15:58
 */
public class StringUtil {
    public static boolean isEmpty(String s) {
        if(s == null || "".equals(s)) {
            return true;
        }
        return false;
    }
}
