package cn.hzr0523.util;

/**
 * hezhi
 * 2018/5/3 15:58
 */
public class StringUtil {
    /**
     * 判断字符是否为空
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        if(s == null || "".equals(s)) {
            return true;
        }
        return false;
    }

    public static boolean isEmptyArray(Object[] objects) {
        if(objects == null || objects.length < 1) {
            return true;
        }
        return false;
    }
}
