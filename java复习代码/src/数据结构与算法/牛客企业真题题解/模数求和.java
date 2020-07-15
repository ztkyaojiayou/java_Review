package 数据结构与算法.牛客企业真题题解;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 题目描述
 * 现给定n个整数，并定义一个非负整数m，且令f(m) = (m%a1)+(m%a2)+...+(m%an)。
 * 此处的X % Y的结果为X除以Y的余数。
 * 现请你找出一个m，求出f(m)的最大值。
 *
 * 输入描述:
 * 输入包含两行，第一行为一正整数n，(1<n<=3000)
 * 第二行为n个整数a1,a2,...,an ，其中(2<=ai<=10^5)
 * 输出描述:
 * 输出仅包含一行，输出f(m)的最大值
 *
 * 示例1
 * 输入
 * 3
 * 3 4 6
 * 输出
 * 10
 *
 * 说明
 * 就样例而言，当m取11时可取得最大值。
 */

 class Main{//在考试中需要写public才能被系统找到
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
        int n = Integer.parseInt( br.readLine() );
        //int[] arr = new int[n];
        String[] strArr = br.readLine().split( " " );

        int sum = 0;
        for( int i=0; i<n; i++ ) {
            sum += Integer.parseInt( strArr[i] )-1;
        }

        System.out.println( sum );
        return;
    }
}