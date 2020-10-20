package 数据结构与算法.LeetCode题解.动态规划;

public class 字符串的最小回文串的划分次数 {
    /**
     * 动态规划实现：切割字符串使得每一个子串为回文字符串，所需要的最少切割次数
     *
     */
        public int minCut(String str) {
            int len = str.length();
            int[] f = new int[len+1]; //最小分割的数组
            int[][] dp = new int[len][len]; //动态规划实现回文字符串判断
            /*
             * 初始化最坏情况  每一个字符串都作为单独的一个字符串作为回文字符串  这个时候f[0] = n-1-0
             * 需要n-1次切割  后续相同
             */
            for (int i = 0; i <= len; i++) {
                f[i] = len - 1 -i;
            }
            for (int i = len - 1; i >= 0; i--) {
                dp[i][i] = 1;
                for (int j = i; j < len; j++) {
                    if(str.charAt(i) == str.charAt(j) && ((j - i < 2) || dp[i+1][j-1] == 1)) {
                        dp[i][j] = 1;
                        f[i] = Math.min(f[i], f[j+1]+1);
                    }
                }
            }
            return f[0];
        }
}
