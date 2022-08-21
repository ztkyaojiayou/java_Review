package 数据结构与算法.od机考.华为od机考练习.常规题库;

/**
 * 40）统计字符
 * 描述
 * 输入一行字符，分别统计出包含英文字母、空格、数字和其它字符的个数。
 * <p>
 * 数据范围：输入的字符串长度满足1≤n≤1000
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
        String letterCnt = str.replaceAll("[^a-zA-Z]", "");
        String spaceCnt = str.replaceAll("[^ ]", "");
        String digitCnt = str.replaceAll("[^0-9]", "");
        String otherCnt = str.replaceAll("[a-zA-Z0-9 ]", "");
        System.out.println(letterCnt.length());
        System.out.println(spaceCnt.length());
        System.out.println(digitCnt.length());
        System.out.println(otherCnt.length());
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

