package 数据结构与算法.od机考.华为od机考练习.常规题库;

/**
 * 11_12数字颠倒或字符串反转
 * 12）字符串反转
 * 描述
 * 接收一个只包含小写字母的字符串，然后输出该字符串反转后的字符串。（字符串长度不超过1000）
 * 11）数字颠倒/反转
 * 输入一个整数，将这个整数以字符串的形式逆序输出
 * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
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
class Main11_12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        //数字转为String--会自动忽略数字中的前置零（默认不会输入0001这样的int型）
        String str = String.valueOf(num);
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
