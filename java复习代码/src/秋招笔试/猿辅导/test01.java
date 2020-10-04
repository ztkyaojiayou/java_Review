package 秋招笔试.猿辅导;

import java.util.Scanner;

public class test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();//牌的张数
        int b = sc.nextInt();//洗牌次数
        int[] arr = new int[N];
        for (int i = 0;i<N;i++){
            int c = sc.nextInt();
            arr[i] = c;
        }
        int[] res = new int[N];
        //此时就有了arr了
        for (int i = 0;i<b;i++){
            for (int k = 0;k<N;k++){
                int j = N/2;
                res[k] = arr[j];
                j++;
                int p = 0;
                res[k+1] = arr[p];
                p++;
            }

            arr = res;

        }
        for (int i =0;i<res.length;i++){
            System.out.print(res[i]);
        }
    }
}
