package 华为od机考练习.常规题库;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 103）Redraiment的走法--即最长上升子序列的长度（典型的动态规划题）
 *
 * @author :zoutongkun
 * @date :2022/7/29 4:40 下午
 * @description :
 * @modyified By:
 */
public class Main103Redraiment的走法_最长上升子序列的长度 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(lengthOfLIS(nums));
    }

    public static int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int res = 0;
        //dp[i] 表示 以nums[i] 结尾的序列的最长上升子序列的长度
        //也即：nums前i个数字的最长子序列长度。
        int[] dp = new int[len];
        //初始化：长度至少为1，即由它本身构成
        //使用现成的api更方便
        Arrays.fill(dp, 1);
        for (int i = 1; i < len; i++) {
            // 为什么j<j即可？因为子序列是不要求连续的，
            // 因此在i之前的所有情况都要考虑呀
            for (int j = 0; j < i; j++) {
                //若小于，则可以状态转移
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                res = Math.max(res, dp[i]);
            }
        }
        //返回最终结果
        return res;

    }
}
