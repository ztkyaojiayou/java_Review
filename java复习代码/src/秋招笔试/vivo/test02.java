package 秋招笔试.vivo;

import java.util.Scanner;

public class test02 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        //abda ada
        if(check(str)) {
            System.out.println(str);
        }else {
                for(int i=0;i<str.length();i++) {
                String s = str.substring(0, i)+str.substring(i+1, str.length());
                if(check(s)) {
                    System.out.println(s);
                    return;
                }
            }
            System.out.println(false);
        }
    }

    public static boolean check(String str) {
        if(str==null||str.length()==0) {
            return true;
        }
        int i = 0;
        int j = str.length()-1;
        while(i<j) {
            if(str.charAt(i)!=str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }
}
