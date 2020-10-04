package 秋招笔试.去哪儿;

import java.util.Scanner;

public class test02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = Integer.valueOf(sc.nextLine());
        String regex = " ";
        String[] s1 = sc.nextLine().split(regex);
        String regex1 = " ";
        String[] s2 = sc.nextLine().split(regex1);
        int count = 0;
        for (int i = 0;i<s1.length;i++){
            for (int j = 0;j<=i;j++){
                if (s1[i].equals(s2[j])){//要用equals比较
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
