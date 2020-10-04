package 秋招笔试.美团;

import java.util.Scanner;

public class test02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = null;
        str = sc.nextLine();
        int len = str.length();
        int count01 = 0;
        int count02 = 0;
        int res = 0;
        for (int i = 0;i<len;i++){
            if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'){
                count01++;
            }else {
                count02++;
            }
        }
        if (count01 >= count02){
            res = (count01 - count02) / 2;
        }else {
            res = (count02 - count01) / 2;
        }
        System.out.println(res);
    }
}
