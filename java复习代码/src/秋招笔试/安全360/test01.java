package 秋招笔试.安全360;

import java.util.ArrayList;
import java.util.Scanner;

public class test01 {
    public static void main(String[] args) {
        String str;
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        while (sc.hasNext()){
    str = sc.nextLine();
    list.add(str);
}
for (int i = 0;i<list.size();i++){
    test01.method(list.get(i));
}
    }
    public static void method(String str){

        int low = 0;
        int up = 0;
        int num = 0;
        int rare = 0;
        char[] chars = str.toCharArray();
        for (int i = 0;i<chars.length;i++){
            if (str.length() < 8){
                System.out.println("Irregular password");
                break;
            }
            if (rare(str)){
                if (Character.isLowerCase(chars[i])){
                    low++;
                }else if (Character.isUpperCase(chars[i])){
                    up++;
                }else if (Character.isDigit(chars[i])){
                    num++;
                }else {
                    rare++;
                }
            }else {
                System.out.println("Irregular password");
            }
       }

        if (low != 0 && up != 0 && num != 0 && rare != 0){
            System.out.println("Ok");
        }else {
            System.out.println("Irregular password");
        }
    }

    public static boolean rare(String str) {
        char[] temp = "!@#$￥%^&*()-_=+[{}];:'|,.<>?/\"。、".toCharArray();
        for(int i=0;i<str.length();i++) {
            char c = str.charAt(i);
            for(char j:temp) {
                if(c==j) {
                    return true;
                }
            }
        }
        return false;
    }

}
