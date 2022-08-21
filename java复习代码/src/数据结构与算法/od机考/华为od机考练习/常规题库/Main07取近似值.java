package 数据结构与算法.od机考.华为od机考练习.常规题库;

/**
 * 07)取近似值--入门级
 * 描述
 * 写出一个程序，接收一个正浮点数值，输出该数值的近似整数值。
 * 如果小数点后数值大于等于 0.5 ,向上取整；小于 0.5 ，则向下取整。
 * <p>
 * 数据范围：保证输入的数字在 32 位浮点数范围内
 *
 * @author :zoutongkun
 * @date :2022/7/28 11:48 上午
 * @description :
 * @modyified By:
 */

import java.util.*;

class Main07取近似值 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入一个浮点数
        float ans = sc.nextFloat();
        //得到小数位，判断是否大于0.5
        //若大于，则当前浮点数的整数位+1
        //否则，就取当前浮点数的整数位
        System.out.println(ans - (int) ans >= 0.5 ? (int) (ans + 1) : (int) ans);
    }
}

