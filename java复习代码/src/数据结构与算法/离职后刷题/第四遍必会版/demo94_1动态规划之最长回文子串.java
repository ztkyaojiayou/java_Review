package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo94_1动态规划之最长回文子串 {
    public String longestPalindrome(String str) {
        int len = str.length();
        int maxLen = 0;
        int begin = 0;
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
                if (str.charAt(i) == str.charAt(j)) {//易知，i相当于头指针，j相当于尾指针
                    if (j - i < 3) {//此时表示(i+1,j-1)无法构成区间,即中间只有一个字符了，易知，当然是回文
                        dp[i][j] = true;
                    } else {//否则，里面子串的回文性质就决定了整个子串的回文性质（核心）
                        dp[i][j] = dp[i + 1][j - 1];
                    }

                    //若dp[i][j]为真时，就表示s(i,j)为回文，
                    // 于是记录/更新该回文串的起始下标以及该回文串的长度
                    if (dp[i][j]) {
                        //更新回文串的长度
                        maxLen = Math.max(maxLen, j - i + 1);
                        begin = i;//此时的起始位置
                    }
                }
            }
        }
        //返回结果（是要求这个具体的回文串）即可，使用substring截取
        //不过其实这类题一般只要求这个回文串的最大长度的呀，此时就是返回maxLen即可
        return str.substring(begin, begin + maxLen);
    }
}
