package 数据结构与算法.离职后刷题.第四遍必会版;

/**
 * 和上一题几乎完全相同，核心都是先判断为回文串，然后再处理即可
 */
public class demo94_2动态规划之回文子串的总个数 {
    public int countSubstrings(String str) {
        int res_len = str.length();
        //dp[i][j] 表示从i到j所构成的字符串是否为回文子串的状态
        boolean[][] dp = new boolean[res_len][res_len];
        //初始化，易知，对角线上的肯定为回文串
        for (int i = 0; i < res_len; i++) {
            dp[i][i] = true;
        }
        //一般情况
        for (int j = 1; j < res_len; j++) {
            for (int i = 0; i < j; i++) {
                if (str.charAt(i) == str.charAt(j)) {//前提
                    //如aba或aa等就肯定是回文串呀
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                //每见到一个回文子串，就统计一下（关键）
                if (dp[i][j]) {
                    res_len++;//为什么要从len相加？因为每单个字符肯定是回文串，所以至少有len个嘛~
                }
            }
        }
        return res_len;
    }
}
