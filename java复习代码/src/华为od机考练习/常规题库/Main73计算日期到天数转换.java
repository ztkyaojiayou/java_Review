package 华为od机考练习.常规题库;

/**
 * 73）计算日期到天数转换
 * 具体做法：
 * 1)可以用一个12大小的数组记录平年1到12月份，每个月的天数，
 * 然后根据输入的月份，累加该月前面的所有天数，
 * 再加上该月到现在为止的天数。
 * 2)如果该年份整除4且不整除100，
 * 或者整除100就是闰年，对于闰年而言，上述累加和中大于2月份的要多加一天。
 * 2月因为用的就是day的天数，而不是月份的天数，因此不用管。
 *
 * @author :zoutongkun
 * @date :2022/7/26 1:28 下午
 * @description :
 * @modyified By:
 */

import java.util.Scanner;

public class Main73计算日期到天数转换 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //使用一个数组来记录各个月份的天数
        //默认处理器为平年，即2月先按28天算
        int[] dayOfMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
//        while(sc.hasNext()){
        //由于case中的三个数是在一行，因此就用nextInt()方法即可
        //年份只影响是否闰年
        int year = sc.nextInt();
        //可直接用于计算天数
        int month = sc.nextInt();
        int day = sc.nextInt();
        //统计天数
        int totalDays = 0;
        //先求当前月份的前面所有月份的天数
        for (int i = 0; i < month - 1; i++) {
            totalDays += dayOfMonth[i];
        }
        //再求当月天数
        totalDays += day;
        //闰年判断
        //可以看年份：根据闰年规则“四年一闰，百年不闰，四百年一闰”，年份满足下列条件之一，则为闰年。
        //（1）能被4整除且不能被100整除（如2004年是闰年，而1900年不是）
        //（2）能被400整除（如2000年是闰年）
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            //大于2月的多加一天，因为此时二月为29天
            if (month > 2) {
                totalDays++;
            }
        }
        System.out.println(totalDays);
//        }
    }
}

