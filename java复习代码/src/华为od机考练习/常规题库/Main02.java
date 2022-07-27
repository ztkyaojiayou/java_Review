package 华为od机考练习.常规题库;

import java.io.IOException;
import java.util.Scanner;

/**
 * 2）计算某字符出现次数
 * @author :zoutongkun
 * @date :2022/7/23 1:52 下午
 * @description :
 * @modyified By:
 */

public class Main02 {
        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            //录入第一行--且都统一转小写
            String str = sc.nextLine().toLowerCase();
            //录入第二行--，便于比较
            char s = sc.nextLine().toLowerCase().charAt(0);
            int count = 0;
            for(int i = 0 ; i < str.length();i++){
                if(str.charAt(i) == s){
                    count++;
                }
            }
            System.out.println(count);
        }
}


class Main002 {
        public static void main(String[] args) throws IOException {
            Scanner sc = new Scanner(System.in);
            String str =  sc.nextLine().toLowerCase();
            char c = sc.nextLine().toLowerCase().charAt(0);
            int cnt = 0;
            for(int i = 0;i<str.length();i++){
                if(str.charAt(i) == c){
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
}