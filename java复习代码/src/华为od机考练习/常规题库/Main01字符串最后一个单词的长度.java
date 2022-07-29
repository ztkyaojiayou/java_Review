package 华为od机考练习.常规题库;

/**
 * 1）字符串最后一个单词的长度
 *
 * @author :zoutongkun
 * @date :2022/7/23 1:37 下午
 * @description :
 * @modyified By:
 */

import java.util.Scanner;

/**
 * 方法1：
 */
class Main01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int i = s.length() - 1;
        int t = 0;
        while (i >= 0 && s.charAt(i) != ' ') {
            t++;
            i--;
        }
        System.out.println(t);
    }
}

/**
 * 方法2：
 */
class Main001 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] strArr = str.split(" ");
        System.out.println(strArr[strArr.length - 1].length());
    }
}

