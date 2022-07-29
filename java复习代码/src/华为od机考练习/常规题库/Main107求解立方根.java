package 华为od机考练习.常规题库;

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
import java.util.*;

public class Main107求解立方根 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        double num = sc.nextDouble();
        double x = method(num);
        System.out.printf("%.1f", x);
//        //相当于下面这个写法
//        System.out.println(String.format("%.1f",x));
    }

    //使用类似二分的思路
    public static double method(double num) {
        double right, left, mid = 0.0;
        //一定要注意边界条件，输入的num可能是负数
        //将x<-1的边界范围定为[x,1]，
        //x>1的边界范围定为[-1,x]
        right = Math.max(1.0, num);
        left = Math.min(-1.0, num);
        //控制精度，即只要这个范围在0.001之内就可以返回了
        //（题干倒是没说，因此只要大于一位小数的精度即可）
        while (right - left > 0.001) {
            //取中间值
            mid = (left + right) / 2;
            //如果乘积大于num，说明立方根在mid的左侧
            if (mid * mid * mid > num) {
                right = mid;
            } else if (mid * mid * mid < num) { //如果乘积小于num，说明立方根在mid的右侧
                left = mid;
            } else {//即表示找到了，直接返回该值
                return mid;
            }
        }
        //由于他们的差别是0.001级别的，因此当取一位小数时，其实返回任意一个值都行！
        return left;
    }
}


