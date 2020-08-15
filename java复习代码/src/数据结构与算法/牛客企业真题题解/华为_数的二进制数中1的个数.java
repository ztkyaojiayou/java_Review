package 数据结构与算法.牛客企业真题题解;

import java.util.Scanner;

public class 华为_数的二进制数中1的个数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int num = sc.nextInt();
            int count = 0;
            for(int i = 0;i < 32;i++){
                int temp = num & (1<< i);
                if (temp != 0){
                    count ++;
                }
            }
            System.out.println(count);
        }
    }
}
