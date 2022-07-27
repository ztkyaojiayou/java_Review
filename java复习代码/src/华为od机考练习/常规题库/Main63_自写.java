package 华为od机考练习.常规题库;

/**
 * 63）DNA序列
 * 1.输入描述：
 * 输入一个string型基因序列，和int型子串的长度
 * 2.输出描述：
 * 找出GC比例最高的子串,如果有多个则输出第一个的子串
 *
 * @author :zoutongkun
 * @date :2022/7/25 4:39 下午
 * @description :
 * @modyified By:
 */

import java.util.Scanner;

public class Main63_自写 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int subStrLen = Integer.parseInt(sc.nextLine());
        double gCRatio = 0.0;
        String result = "";
        for (int i = 0; i < str.length() - subStrLen + 1; i++) {
            String subStr = str.substring(i, i + subStrLen);
            //将子串中除了CG外的字符去掉，以便计算GC-Ratio--使用正则表达式，妙哉！
            String cgStr = subStr.replaceAll("[^CG]", "");
            //计算GC-Ratio值
            double cur = (double) cgStr.length() / (double) subStrLen;
            //当有更大的GC_Ratio值时，则更新该值和对应的子串
            if (cur > gCRatio) {
                gCRatio = cur;
                result = subStr;
            }
        }
        System.out.println(result);
    }
}

