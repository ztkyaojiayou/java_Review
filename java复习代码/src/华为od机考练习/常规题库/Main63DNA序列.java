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

import java.util.*;

public class Main63DNA序列 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            int subStrLen = Integer.parseInt(sc.nextLine());
            double GC_Ratio = 0.0;
            String result = "";
            //遍历字符串截取目标长度的子串即可，因为题目要求，子串的顺序是不能变的，
            //也即只能是从前往后的顺序，因此遍历即可
            for (int i = 0; i < str.length() - subStrLen + 1; i++) {
                //截取目标长度的子串
                String subStr = str.substring(i, i + subStrLen);
                //将子串中除了CG外的字符去掉，以便计算GC-Ratio
                String cgStr = subStr.replaceAll("[^CG]", "");
                double cur = (double) cgStr.length() / (double) subStrLen;
//                //int型除以int型得到的结果就是个int，这个时候再转有意义吗？
//                double i1 = cgStr.length() / subStrLen;
                //这里是大于时才更新，因此等于的情况就不会更新，
                //因此就完美避开了后面还有多个GC-Ratio值相等的子串的情况
                if (cur > GC_Ratio) {
                    GC_Ratio = cur;
                    result = subStr;
                }
            }
            System.out.println(result);
        }
    }
}

