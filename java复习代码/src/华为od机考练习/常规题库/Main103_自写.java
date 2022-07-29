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
public class Main103_自写 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = sc.nextInt();
        }
        //定义dp:dp[i] 表示 以nums[i] 结尾的序列的最长上升子序列的长度
        //也即：nums前i个数字的最长子序列长度。
        int[] dp = new int[len];
        //初始化：以每一个元素结尾所构成的最长上升子序列的长度至少为1，即它本身
        Arrays.fill(dp, 1);
        //一般情况
        int maxLen = 0;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                //若小于/即能构成上升序列时，则转移
                if (nums[j] < nums[i]) {
                    //转移并比较，取最大值
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                //再以i为维度取最大值
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        System.out.println(maxLen);
    }
}
