package 秋招笔试.顺丰;

import java.util.HashMap;
import java.util.Scanner;

public class test02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().toLowerCase();
        String regex = " ";
        String[] arr = str.split(regex);
        int len = arr.length;
        int count = 0;
        String target = "apple";
        for (int i = 0; i < len; i++) {
            String s = arr[i];
            if (s.equals(target)) {
                count++;
            }
        }
        //输出
        System.out.println(count);
    }
}
