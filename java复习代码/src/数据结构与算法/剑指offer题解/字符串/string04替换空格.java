package 数据结构与算法.剑指offer题解.字符串;

/**
 * 题目：将一个字符串中的空格替换成 "%20"。
 *      例如：Input:
 *           "A B"
 *
 *           Output:
 *           "A%20B"
 *
 * 首先注意：空格不是空字符串，切记！！！
 * 这里让求的是把字符串中的空格替换成%20，其中一种实现方式就是申请一个临时数组，然后再遍历这个字符串的每个字符，
 * 如果不是空格就把遍历的字符添加到临时数组中，如果是空格就添加3个字符'%'，'2'，'0'分别到临时数组中，最后再把临时数组转化为字符串即可。
 */
public class string04替换空格 {
    /**
     * 方法1：使用一个临时数组
     * @param s
     * @return
     */
        public String replaceSpace01(String s) {
            int length = s.length();
            char[] array = new char[length * 3];
            int index = 0;//用于记录新字符串的长度
            for (int i = 0; i < length; i++) {
                char c = s.charAt(i);
                if (c == ' ') {
                    array[index++] = '%';
                    array[index++] = '2';
                    array[index++] = '0';
                } else {
                    array[index++] = c;
                }
            }
            String newStr = new String(array, 0, index);
            return newStr;
        }


    /**
     * 方法2：直接使用StringBuilder中的append方法（不推荐）
     * 和上面差不多，就是把字符串中的每个字符一个个添加到StringBuilder中，如果遇到空格就把他换成%20
     */
        public String replaceSpace02(String s) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ')
                    stringBuilder.append("%20");
                else
                    stringBuilder.append(s.charAt(i));
            }
            return stringBuilder.toString();
        }
    }


