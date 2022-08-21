package 数据结构与算法.od机考.华为od机考练习.常规题库;

/**
 * 107）求解立方根--求平方根也是类似的方法
 * 1。输入描述：
 * 待求解参数，为double类型（一个实数）
 * 2.输出描述：
 * 输出参数的立方根。保留一位小数。
 * <p>
 * 思路解析：
 * 方法一：二分
 * 具体做法
 * 做过求平方根的同学应该都知道，这题最先想到的应该就是二分法吧。
 * <p>
 * 如果一个数num>1，那么这个数的立方根一定在1~num之间。
 * 如果一个数num<-1，那么这个数的立方根一定在num~-1
 * 如果一个数-1<num<1，那么这个数的立方根一定在-1~1之间 如num = 2.7
 * 可以设置左边界为min(-1,2.7) = -1 右边界 max(1,2.7) = 2.7
 * 所以left = -1，right = 2.7
 *
 * @author :zoutongkun
 * @date :2022/7/29 5:35 下午
 * @description :
 * @modyified By:
 */

import java.io.IOException;
import java.util.Scanner;

public class Main107_自写 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        double num = sc.nextDouble();
        boolean flag = false;
        //二分查找
        double right, left, mid = 0.0;
        //初始化
        right = Math.max(1.0, num);
        left = Math.min(-1.0, num);
        //控制精度
        while (right - left > 0.001) {
            //目标值
            mid = (right + left) / 2;
            //判断
            if (mid * mid * mid > num) {
                right = mid;
            } else if (mid * mid * mid < num) {
                left = mid;
            } else {
                System.out.printf("%.1f%n", mid);
                flag = true;
                break;
            }
        }
        //退出循环--使用flag标记
        if (!flag) {
            System.out.printf("%.1f%n", right);
        }
    }
}


