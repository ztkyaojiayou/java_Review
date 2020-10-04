package 秋招笔试.vivo;

import java.util.Scanner;

public class test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int count = 0;
       for (int i = 0;i<str.length();i++){
           String res = str.substring(i+1);
           if (test01.method(res)){
               System.out.println(res);
               break;
           }
           count++;
           if (count == str.length()-1){
               System.out.println("false");
           }
       }

       }


    //判断是否回文
    public static boolean method(String str){
        int len = str.length();
        int left = 0;
        int right = len-1;
        while (left <right){
if (str.charAt(left) != str.charAt(right)){
    return false;
            }
           left++;
           right--;
        }
        return true;
    }
}
