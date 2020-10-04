package 秋招笔试.新浪;

import java.util.HashMap;
import java.util.Scanner;

public class test02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        boolean res = test02.method(str);
        if (res){
            System.out.println("true");
        }else {
            System.out.println("false");
        }
    }
    public static boolean method(String str){
        if(str==null||str.isEmpty()){
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<>();//（字符，次数）
        char[] chars = str.toCharArray();
     for (int i = 0;i<chars.length;i++){
         if (map.containsKey(chars[i])){
             return false;
         }
         map.put(chars[i],1);
     }
        return true;
    }
}
