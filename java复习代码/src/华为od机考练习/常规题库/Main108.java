package 华为od机考练习.常规题库;

/**
 * 106）求最小公倍数
 * 描述
 * 正整数A和正整数B 的最小公倍数是指 能被A和B整除的最小的正整数值，
 * 设计一个算法，求输入A和B的最小公倍数
 * <p>
 * 解析：
 * 1）先通过辗转相除法求最大公约数：取两个数中最大的数做除数，较小的数做被除数，
 * 用最大的数除较小数，如果余数为0，则较小数为这两个数的最大公约数，
 * 如果余数不为0，用较小数除上一步计算出的余数，
 * 直到余数为0，则这两个数的最大公约数为上一步的余数。
 * 2）再根据公式ab=最小公倍数gcd(a,b)，得到最小公倍数。
 *
 * @author :zoutongkun
 * @date :2022/7/28 7:38 下午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main108 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        //再根据公式ab=最小公倍数gcd(a,b)，得到最小公倍数（这个公式需要记一下）
        System.out.println(a * b / gcd(a, b));
    }

    /**
     * 通过辗转相除法求最大公约数
     * 使用递归求
     *
     * @param a
     * @param b
     * @return
     */
    private static int gcd(int a, int b) {
        //直到余数为0，取上一步的余数即为这两个数的最大公约数
        return b == 0 ? a : gcd(b, a % b);
    }
}

/**
 *
 */
class Main1080 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println();
    }

    //求最大公约数
    private static int gcd(int a, int b) {
        //当为0时，取上一步的余数即为这两个数的最大公约数
        if (b == 0){
            return a;
        }
        //当余数不为0时，则继续用上一步的除数除以对应的余数
        return gcd(b,a%b);
    }
}

