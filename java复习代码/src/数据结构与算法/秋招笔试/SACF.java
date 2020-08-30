package 数据结构与算法.秋招笔试;

import java.util.Scanner;

class Main022 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        int count = 0;
        int n = s1.length();
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count -= 10;
            }else {
                count += 20;
            }

            if(count < 0){
                count = 0;
            }
        }
        System.out.println(count);

    }
}

