package 秋招笔试.神策;

import java.util.Scanner;

//判断一个数是否是2的整数次幂
public class test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        boolean res = isPowerOfTwo(a);
        if (res){
            System.out.println("True");
        }else {
            System.out.println("False");
        }
    }

        public static boolean isPowerOfTwo(int n) {
            if (n == 0) return false;
            long x = (long) n;
            return (x & (-x)) == x;
        }

}
