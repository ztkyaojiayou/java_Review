package 秋招笔试.华为;

import java.util.Scanner;

public class test03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();//答案字符串
        int len1 = s1.length();
        int len2 = s2.length();//答案字符串长度
        int count = 0;
        if (len1 == len2){
            for (int i = 0;i<len1;i++){
                if (s1.charAt(i) != s2.charAt(i)){
                    count++;
                }
            }
        }

        System.out.println("(" + count + "," + len2 + ")");

    }
}
