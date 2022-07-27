package 华为od机考练习.常规题库;

/**
 * 11_12数字或字符串反转
 *
 * @author :zoutongkun
 * @date :2022/7/23 6:24 下午
 * @description :
 * @modyified By:
 */

import java.util.Scanner;

/**
 * 方法1：适用于数字
 */
public class Main11_12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int in = sc.nextInt();
        String str = String.valueOf(in);
        for (int i = str.length() - 1; i >= 0; i--) {
            System.out.print(str.charAt(i));
        }
    }
}

/**
 * 方法2：适用于数字或字符串
 */
class Main110 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //虽然说是传入一个int型，但在录入时其实可以不用区分，直接看成一个字符串即可~
        String s = sc.nextLine();
        //反转--只有StringBuilder才可以操作，String不行，因此需要StringBuilder
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        //println自动调用传入对象的toString方法,因此无需再写toString去转了
        System.out.println(sb);
    }
}
