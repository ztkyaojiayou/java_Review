package 华为od机考练习.常规题库;

/**
 * 56）完全数计算
 * 知识点：
 * 1）一个整数的最大真因子或约数，小于等于该数的二分之一
 * 2）第一个完全数是6，若小于6则输出0
 *
 * @author :zoutongkun
 * @date :2022/7/27 5:31 下午
 * @description :
 * @modyified By:
 */

import java.util.Scanner;

public class Main56 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int num = sc.nextInt();
            //第一个完全数是6，若小于6则输出0
            if (num < 6) {
                System.out.println(0);
                continue;
            }
            //计数变量
            int cnt = 0;
            for (int i = 6; i <= num; i++) {
                int sum = 0;
                //统计约数/因子的和，计数到该数的1/2即可
                for (int j = 1; j <= i / 2; j++) {
                    if (i % j == 0)
                        sum += j;
                }
                //完全数判断
                if (sum == i)
                    cnt++;
            }

            //输出结果
            System.out.println(cnt);
        }
    }
}

/**
 * 自写一遍
 */
class Main560 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        if (num < 6) {
            System.out.println(0);
        } else {
            //用于统计完全数的个数
            int cnt = 0;

            //从6开始，处理每一个数
            for (int i = 6; i <= num; i++) {
                //用于计算当前数字各因子的和--切不可定义在第一层循环外面！！！
                int sum = 0;
                //只需要判断到i/2即可
                for (int j = 1; j <= i / 2; j++) {
                    //查找因子/约数--即能被整除的数
                    if (i % j == 0) {
                        sum += j;
                    }
                }
                //判断是否为完全数
                if (sum == i) {
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }
}

