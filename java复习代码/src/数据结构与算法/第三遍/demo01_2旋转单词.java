package 数据结构与算法.第三遍;

public class demo01_2旋转单词 {
    public String ReverseSentence(String str) {
        char[] str_arr = str.toCharArray();
        int len = str_arr.length;
        int i = 0;
        //先旋转单个字符串
        for (int j = 0; j < len; j++) {
            if (str_arr[j] == ' ' || j == len) {//当到一个字符串的尽头时，就旋转。
                reverse(str_arr, i, j - 1);
                i = j + 1;
            }
        }
        //再旋转整个字符串
        reverse(str_arr, 0, len - 1);
        String res_str = new String(str_arr);
        return res_str;
    }

    //反转每个单词
    private void reverse(char[] chars, int i, int j) {
        while (i < j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
    }
}
