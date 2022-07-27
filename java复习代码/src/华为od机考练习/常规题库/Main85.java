package 华为od机考练习.常规题库;

import java.util.Scanner;

/**
 * 85）最长回文子串
 * @author :zoutongkun
 * @date :2022/7/26 4:43 下午
 * @description :
 * @modyified By:
 */
public class Main85 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(longestPalindrome(str));
    }

    public static int longestPalindrome(String str) {
        int len = str.length();
        int maxLen = 0;
        //dp[i][j] 表示 s[i, j] 是否是回文串,注意：包括i和j
        boolean[][] dp = new boolean[len][len];
        //初始化，易知，对角线上的肯定为回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        //一般情况，两层循环，j前i后
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                //大前提：当前对应的字符相等
                if (str.charAt(i) == str.charAt(j)) {
                    //此时表示(i+1,j-1)无法构成区间,即中间只有一个字符了，易知，当然是回文
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {//否则，里面子串的回文性质就决定了整个子串的回文性质（核心）
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    //若dp[i][j]为真时，就表示s(i,j)为回文，
                    //于是更新最长长度
                    if (dp[i][j]) {
                        //更新回文串的长度
                        maxLen = Math.max(maxLen, j - i + 1);
                    }
                }
            }
        }
        //返回结果
        return maxLen;
    }
}
