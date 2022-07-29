package 华为od机考练习.常规题库;

/**
 * 21）简单密码（转换）
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
            if (charArr[i] >= 'A' && charArr[i] <= 'Y') {
                //如果是A~Y的大写字母则需要将其+32位转换成小写再向后移1位，则相当于加33
                charArr[i] += 33;
            } else if (charArr[i] == 'Z') {
                //如果是Z则加密成a，单独处理即可
                charArr[i] = 'a';
                //而若为小写字母，则一一转换为对应的数字即可
            } else if (charArr[i] == 'a' || charArr[i] == 'b' || charArr[i] == 'c') {
                charArr[i] = '2';
            } else if (charArr[i] == 'd' || charArr[i] == 'e' || charArr[i] == 'f') {
                charArr[i] = '3';
            } else if (charArr[i] == 'g' || charArr[i] == 'h' || charArr[i] == 'i') {
                charArr[i] = '4';
            } else if (charArr[i] == 'j' || charArr[i] == 'k' || charArr[i] == 'l') {
                charArr[i] = '5';
            } else if (charArr[i] == 'm' || charArr[i] == 'n' || charArr[i] == 'o') {
                charArr[i] = '6';
            } else if (charArr[i] == 'p' || charArr[i] == 'q' || charArr[i] == 'r' || charArr[i] == 's') {
                charArr[i] = '7';
            } else if (charArr[i] == 't' || charArr[i] == 'u' || charArr[i] == 'v') {
                charArr[i] = '8';
            } else if (charArr[i] == 'w' || charArr[i] == 'x' || charArr[i] == 'y' || charArr[i] == 'z') {
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
