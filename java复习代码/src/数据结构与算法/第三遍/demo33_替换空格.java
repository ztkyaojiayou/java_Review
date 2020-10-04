package 数据结构与算法.第三遍;

public class demo33_替换空格 {
    //暴力方法
    public String replaceSpace01(String s) {
        int len = s.length();
        char[] chars = new char[len * 3];
        int index = 0;
        for (int i = 0;i<len;i++){
            char c = s.charAt(i);
            if (c == ' '){
                chars[index++] = '%';
                chars[index++] = '2';
                chars[index++] = '0';
            }else {
                chars[index++] = 'c';
            }
        }
        //由字符串数组转化为字符串的方法，使用构造函数即可
        String res = new String(chars, 0, index);
        return res;
    }
}
