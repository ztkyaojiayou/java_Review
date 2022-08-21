package 数据结构与算法.od机考.华为od机考练习.常规题库;

/**
 * 97)记负均正
 *
 * @author :zoutongkun
 * @date :2022/7/28 9:52 上午
 * @description :
 * @modyified By:
 */

import java.util.*;

class Main97 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
        //这个题告诉了咱们要输入几个数，因此可以不需要while
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


/**
 * 105)记负均正II
 * 描述
 * 输入 n 个整型数，统计其中的负数个数并求所有非负数的平均值，
 * 结果保留一位小数，如果没有非负数，则平均值为0
 * 本题有多组输入数据，输入到文件末尾。
 *
 * @author :zoutongkun
 * @date :2022/7/28 9:52 上午
 * @description :
 * @modyified By:
 */

class Main105 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int curNum;
        int countN = 0;
        int countP = 0;
        double sum = 0.0;
        //这个题没有告诉咱们要输入几个数，因此需要while来终止输入
        while (in.hasNextInt()) {
            curNum = in.nextInt();
            if (curNum < 0) {
                countN++;
            } else if (curNum > 0) {
                countP++;
                sum += curNum;
            }
        }
        //这里要求分两行输出，上一题只要求按一行输出即可
        System.out.println(countN);
        double avg = countP == 0 ? 0.0 : sum / countP;
        System.out.println(String.format("%.1f", avg));
//        if(countP==0){
//            System.out.printf("0.0");
//        }else{
//            System.out.printf("%.1f\n",sum/countP);
//        }

    }
}
