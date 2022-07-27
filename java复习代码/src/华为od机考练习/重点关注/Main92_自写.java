package 华为od机考练习.重点关注;

/**
 * 92）在字符串中找出连续最长的数字串
 * 方法：使用正则最方便--推荐
 * @author :zoutongkun
 * @date :2022/7/22 9:30 下午
 * @description :
 * @modyified By:
 */

import java.util.Scanner;

public class Main92_自写 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int maxLen = 0;
        while (sc.hasNext()){
            String[] numArr = sc.nextLine().split("[^\\d]");
            //遍历，找到最大长度的字符串，记录下最大长度
            for (String str : numArr) {
                if (str != null){
                    maxLen = Math.max(maxLen,str.length());
                }
            }
            //再遍历一遍，输出最大长度的字符串
            for (String str : numArr) {
                if (str != null){
                    if (str.length() == maxLen){
                        /**
                         * print在本次输出之后不会换行，println在本次输出之后会换行
                         * 由于可能有多个相同长度的值，且要求输出不带空格，因此使用print方法打印
                         */
                        System.out.print(str);
                    }
                }
            }
            System.out.println(","+maxLen);
        }
    }
}

