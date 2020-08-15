package 数据结构与算法.牛客企业真题题解;

import java.util.Scanner;

public class 华为_最小公倍数 {
    //利用“最小公倍数 = 两数相乘/最大公约数求最大公约数”，先求最大公约数即可，使用辗转相除法
    public static int getRes(int m,int n){
        //互换
        if(m < n){
            int temp = m;
            m = n;
            n = temp;
        }
        int k;
        while (n!= 0){
            k = m%n;
            m = n;
            n = k;
        }
        return m;
    }

    //输入两个数的模板
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){//多个案例时
            int m = sc.nextInt();
            int n = sc.nextInt();



            int tempRes = getRes(m,n);
            System.out.println(m*n/tempRes);
        }
    }
}
