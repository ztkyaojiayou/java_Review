package 秋招笔试.其他杂七杂八;

import java.io.IOException;
import java.util.Scanner;

class Main031{
    public static void main(String[] args) throws IOException {
        //输入
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();//输入字符串
//处理
        int res = Main031.Dance(str1, str2);
        //输出
        System.out.println(res);
    }
    public static int Dance(String str1,String str2){
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        int count = 0;

      for (int i = 0;i<arr1.length;i++){
          if (arr1[i] == arr2[i]){
              count += 20;
          }else {
              count -=10;
          }

          if (count < 0){
              count = 0;
          }
      }
        return count;
    }
}

