package 数据结构与算法.od机考;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author :zoutongkun
 * @date :2022/7/31 11:02 上午
 * @description :
 * @modyified By:
 */
public class Main02 {
    public static void main(String[] args) {
        int MAX_NUM = 1000;
        int maxSum = Integer.MIN_VALUE;
        int[][] arr = new int[MAX_NUM][MAX_NUM];
        int[] cnt = new int[MAX_NUM];
        int[] dp = new int[MAX_NUM];


        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        for (int j = 1; j <= n; j++) {
            Arrays.fill(cnt, 0);
            for (int i = j; i <= n; i++) {
                for (int k = 1; k <= m; k++) {
                    cnt[k] += arr[i][k];
                    dp[1] = cnt[1];
                    if (dp[1] > maxSum) {
                        maxSum = dp[1];
                    }
                    if (k == 1) {
                        continue;
                    } else {
                        if (dp[k - 1] < 0) {
                            dp[k] = cnt[k];
                        } else {
                            dp[k] = dp[k - 1] + cnt[k];
                        }
                    }
                    if (dp[k] > maxSum) {
                        maxSum = dp[k];
                    }
                }
            }
        }
        System.out.println(maxSum);
    }

}
