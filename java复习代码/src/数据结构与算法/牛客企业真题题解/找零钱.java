package 数据结构与算法.牛客企业真题题解;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 题目描述
 * Z国的货币系统包含面值1元、4元、16元、64元共计4种硬币，以及面值1024元的纸币。
 * 现在小Y使用1024元的纸币购买了一件价值为N (0 < N \le 1024)N(0<N≤1024)的商品，请问最少他会收到多少硬币？
 *
 * 输入描述:
 * 一行，包含一个数N。
 *
 * 输出描述:
 * 一行，包含一个数，表示最少收到的硬币数。
 */

//贪心算法：即尽量找大面值的硬币
public class 找零钱 {
    public static void main (String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int n = Integer.parseInt(str);
        int n1 = 0,n2 = 0,n3 = 0,n4 = 0;
        int m = 1024 - n;
        n1 = m/64;//面值为64的最多硬币数
        n2 = m%64/16;//面值为16的最多硬币数
        n3 = m%64%16/4;//面值为4的最多硬币数
        n4 = m%64%16%4/1;//面值为1的最多硬币数
        int sum = n1 + n2 + n3 +n4;//再相加即可
        System.out.println(sum);
    }
}
