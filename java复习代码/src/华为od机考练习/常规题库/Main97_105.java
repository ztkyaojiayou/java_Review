package 华为od机考练习.常规题库;

/**
 * 97)记负均正
 *
 * @author :zoutongkun
 * @date :2022/7/28 9:52 上午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main97 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
        int n = sc.nextInt();
        int countNegative = 0;
        int countPositive = 0;
        int posiotiveSum = 0;
        for (int i = 0; i < n; i++) {
            int number = sc.nextInt();
            //0不用管，只管正数和负数
            if (number < 0) {
                countNegative++;
            } else if (number > 0) {
                posiotiveSum += number;
                countPositive++;
            }
        }
        //%.1f表示保留后一位，能四舍五入。
        //正数个数可能为0（但其实题干的数据范围又说没有0，就离谱！！！）
        //求平均值--要转为double型（强转即可）
        double average = countPositive == 0 ? 0.0 : (double) posiotiveSum / (double) countPositive;
        //平均值输出时，保留一位小数
        System.out.println(countNegative + " " + String.format("%.1f", average));
//        }
    }
}

