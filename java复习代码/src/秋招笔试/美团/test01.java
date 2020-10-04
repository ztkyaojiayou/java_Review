package 秋招笔试.美团;

import java.util.Scanner;

public class test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[] arr = new int[len];
        for (int i = 0;i<len;i++){
            int b = sc.nextInt();
            arr[i] = b;
        }
        //处理
        int cur = 1;
        int res = 0;

        //特判
        if (arr == null || arr.length == 0){
            System.out.println(0);
        }
        //遍历
        for (int i = 0;i<len;i++){
            for (int j = 1;j<= len;j++){
                arr[i] = arr[i] ^ (cur % j);
            }
            cur++;

        }
            //此时arr就变成了bn的各个数了
        for (int i = 0;i<len;i++){
          res = res ^ arr[i];
        }

        System.out.println(res);
    }
}
