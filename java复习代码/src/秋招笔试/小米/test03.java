package 秋招笔试.小米;


import java.util.HashMap;
import java.util.Scanner;

public class test03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        StringBuilder sb = new StringBuilder();
        HashMap<Character, Boolean> map = new HashMap<>();
        for (int i = 0;i<str.length();i++){
            char c = str.charAt(i);
            if (map.containsKey(c)){
                continue;
            }
            map.put(c,true);
            sb.append(c);
        }
        String ret = sb.toString();
        System.out.println(ret);
    }
}
