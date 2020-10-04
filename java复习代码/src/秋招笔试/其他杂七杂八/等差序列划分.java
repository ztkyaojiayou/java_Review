package 秋招笔试.其他杂七杂八;

import java.util.Scanner;

public class 等差序列划分 {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int[] arr = new int[n];
           for (int i = 0;i<n;i++){
                int b = sc.nextInt();
                    arr[i] = b;
            }
            for (int i = 0;i<arr.length; i++){
                if (arr[i] != -1){
                    break;
                }
                System.out.println(1);
            }

            //一般情况
                    int[] dp = new int[arr.length];
                    int sum = 0;
                    for (int i = 2; i < dp.length; i++) {
                        if (arr[i] == -1){

                        }
                        if (arr[i] - arr[i - 1] == arr[i - 1] - arr[i - 2]) {
                            dp[i] = 1 + dp[i - 1];
                            sum += dp[i];
                        }
                    }
                System.out.println(sum);

        }
    }
