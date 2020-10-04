package 秋招笔试;

import java.util.ArrayList;
import java.util.Scanner;

public class test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.valueOf(sc.nextLine());
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            list.add(sc.nextLine());
        }
        System.out.println(list);
        for(String str:list){
            method(str);
        }
        long pow = (long) Math.pow(3, 5);
    }

    public static void method(String str){
        String[] s = str.split(" ");
        long a = Long.valueOf(s[0]);
        long b = Long.valueOf(s[1]);
        String c = s[2];
        if (c.equals("+")) {
            System.out.println(a + b);
        }else if(c.equals("-")){
            System.out.println(a-b);
        }
        long d = 0;
        d = (long) Math.pow(a,b);
    }
}
