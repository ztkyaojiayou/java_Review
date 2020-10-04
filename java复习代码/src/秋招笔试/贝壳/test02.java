package 秋招笔试.贝壳;

import java.util.Scanner;

public class test02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        String str = sc.nextLine();
        int len = str.length();
        int count = 1;
        for (int i = 0;i<len;i++){
            if (str.charAt(i) == str.charAt(i+1)){
                count++;
            }
        }

        if (count == len){
            System.out.println(len);
        }

    }
}
