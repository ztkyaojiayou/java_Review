package 数据结构与算法.od机考.华为od机考练习.常规题库;

/**
 * 55）挑7
 * 描述
 * 输出 1到n之间 的与 7 有关数字的个数。
 * 一个数与7有关是指这个数是 7 的倍数，或者是包含 7 的数字
 * （如 17 ，27 ，37 ... 70 ，71 ，72 ，73...）
 *
 * @author :zoutongkun
 * @date :2022/7/29 11:44 上午
 * @description :
 * @modyified By:
 */

import java.util.*;

/**
 * 思路解析：
 * 从能否被7整除的角度切入，此时易知就两种情况：
 * 1）能整除, 则对7取余==0,
 * 2）不能整除,则转化成字符串,看里面是否包含7，这个很妙！
 */
public class Main55挑7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                if (i % 7 == 0) {
                    sum++;
                } else {
                    //int转String
//                    //s1 = 1,即会忽略前置0，务必注意，但这里倒不影响（其实只要是int就不需要考虑这个问题）
//                    String s1 = String.valueOf(00001);
                    String s = String.valueOf(i);
                    if (s.contains("7")) {
                        sum++;
                    }
                }
            }
            System.out.println(sum);
        }
    }
}
