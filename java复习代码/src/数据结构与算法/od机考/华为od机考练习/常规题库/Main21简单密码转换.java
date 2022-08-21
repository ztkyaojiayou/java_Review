package 数据结构与算法.od机考.华为od机考练习.常规题库;

/**
 * 21）简单密码（转换）
 * 描述
 * 现在有一种密码变换算法。
 * 九键手机键盘上的数字与字母的对应： 1--1， abc--2, def--3, ghi--4, jkl--5, mno--6, pqrs--7, tuv--8 wxyz--9, 0--0，
 * 把密码中出现的小写字母都变成九键键盘对应的数字，如：a 变成 2，x 变成 9.
 * 而密码中出现的大写字母则变成小写之后往后移一位，如：X ，先变成小写，再往后移一位，变成了 y ，例外：Z 往后移是 a 。
 * 数字和其它的符号都不做变换。
 *
 * @author :zoutongkun
 * @date :2022/7/23 7:15 下午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main21简单密码转换 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] charArr = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            //当前字符
            char curChar = charArr[i];
            if (curChar >= 'A' && curChar <= 'Y') {
                //如果是A~Y的大写字母则需要将其+32位转换成小写再向后移1位，则相当于加33
                charArr[i] += 33;
            } else if (curChar == 'Z') {
                //如果是Z则加密成a，单独处理即可
                charArr[i] = 'a';
                //而若为小写字母，则一一转换为对应的数字即可
            } else if (curChar == 'a' || curChar == 'b' || curChar == 'c') {
                charArr[i] = '2';
            } else if (curChar == 'd' || curChar == 'e' || curChar == 'f') {
                charArr[i] = '3';
            } else if (curChar == 'g' || curChar == 'h' || curChar == 'i') {
                charArr[i] = '4';
            } else if (curChar == 'j' || curChar == 'k' || curChar == 'l') {
                charArr[i] = '5';
            } else if (curChar == 'm' || curChar == 'n' || curChar == 'o') {
                charArr[i] = '6';
            } else if (curChar == 'p' || curChar == 'q' || curChar == 'r' || curChar == 's') {
                charArr[i] = '7';
            } else if (curChar == 't' || curChar == 'u' || curChar == 'v') {
                charArr[i] = '8';
            } else if (curChar == 'w' || curChar == 'x' || curChar == 'y' || curChar == 'z') {
                charArr[i] = '9';
            }
            //其他字符则无需转换，原样输出即可
            //这里的输出思路是：一个一个字符判断并同时输出，但使用的是print，因为不能换行（还没有输出完毕）
            System.out.print(charArr[i]);
        }
        //最后换个行即可
        System.out.println();
    }
}
