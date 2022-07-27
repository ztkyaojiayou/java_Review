package 华为od机考练习.常规题库;

import java.util.Scanner;

/**
 * 106）字符逆序
 * @author :zoutongkun
 * @date :2022/7/27 12:15 上午
 * @description :
 * @modyified By:
 */
public class Main106 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        //转为StringBuilder，用于逆序
        StringBuilder sb = new StringBuilder(str);
        //一步到位
        System.out.println(sb.reverse());
//        String reversedStr = sb.reverse().toString();
//        System.out.println(reversedStr);

    }
}
