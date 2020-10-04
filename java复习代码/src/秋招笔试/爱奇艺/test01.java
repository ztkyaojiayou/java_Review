package 秋招笔试.爱奇艺;

import java.util.HashMap;
import java.util.Scanner;

public class test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(test01.method(str));
    }
    public static int method(String str){
        if (str == null){
            return 0;
        }
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int left = 0,right = 0;right < str.length();right++){
            if (map.containsKey(str.charAt(right))){
                left = Math.max(left,map.get(str.charAt(right))+ 1);
            }
            map.put(str.charAt(right),right);
            res = Math.max(res,right-left + 1);
        }
        return res;
    }
}
