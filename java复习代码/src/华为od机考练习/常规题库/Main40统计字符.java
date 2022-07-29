package 华为od机考练习.常规题库;

/**
 * 40）统计字符
 *
 * @author :zoutongkun
 * @date :2022/7/25 10:44 上午
 * @description :
 * @modyified By:
 */

import java.util.*;

class Main40 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //根据题目的输入情况，可以不用while
//        while(sc.hasNextLine()) {
        String str = sc.nextLine();
        //即把不需要统计的字符全部替换为空
        String str1 = str.replaceAll("[^a-zA-Z]", "");
        String str2 = str.replaceAll("[^ ]", "");
        String str3 = str.replaceAll("[^0-9]", "");
        String str4 = str.replaceAll("[a-zA-Z0-9 ]", "");
        System.out.println(str1.length());
        System.out.println(str2.length());
        System.out.println(str3.length());
        System.out.println(str4.length());
//        }
    }
}

/**
 * 自写一遍--方法2
 */
class Main400 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int letters = 0;
        int spaces = 0;
        int digits = 0;
        int others = 0;
        char[] strArr = str.toCharArray();
        //就使用Character中的方法判断
        for (char c : strArr) {
            if (Character.isLetter(c)) {
                letters++;
            } else if (Character.isSpaceChar(c)) {
                spaces++;
            } else if (Character.isDigit(c)) {
                digits++;
            } else {
                others++;
            }
        }
        //再打印
        System.out.println(letters);
        System.out.println(spaces);
        System.out.println(digits);
        System.out.println(others);
    }
}

