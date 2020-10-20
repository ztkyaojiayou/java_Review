package 数据结构与算法.第三遍;

public class demo94_1动态规划之最长回文子串 {
    public String longestPalindrome(String s) {
        int len = s.length();
        int maxLen = 1;
        int begin = 0;
        char[] chars = s.toCharArray();
        //dp[i][j] 表示 s[i, j] 是否是回文串,注意：包括i和j
        boolean[][] dp = new boolean[len][len];
        //初始化，易知，对角线上的肯定为回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        //一般情况，两层循环，j前i后
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (chars[i] == chars[j]) {
                    if (j - i < 3) {//此时表示(i+1,j-1)无法构成区间,即中间只有一个字符了，易知，当然是回文
                        dp[i][j] = true;
                    } else {//否则，里面子串的回文性质就决定了整个子串的回文性质（核心）
                        dp[i][j] = dp[i + 1][j - 1];
                    }

                    //若dp[i][j]为真时，就表示s(i,j)为回文，于是记录/更新该回文串的起始下标以及该回文串的长度
                    if (dp[i][j] && j - i + 1 > maxLen) {
                        maxLen = j - i + 1;//此时的回文串的长度
                        begin = i;//此时的起始位置
                    }
                }
            }
        }
        //返回结果即可，使用substring截取
        return s.substring(begin, begin + maxLen);
    }
}
