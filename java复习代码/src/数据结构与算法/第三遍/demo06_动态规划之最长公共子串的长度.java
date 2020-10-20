package 数据结构与算法.第三遍;

//典型的动态规划
public class demo06_动态规划之最长公共子串的长度 {
    public int longestCommonString(String s1,String s2) {
        //结果集
        int res = 0;
        //定义dp数组，dp[i][j]表示s1以i结尾和s2以j结尾的字符串所构成的最长公共子串
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        //确定初始值，其实可以不写，因为数组默认就全为0
        //for (int j = 0;j<s2.length();j++){
        //    dp[0][j] = 0;
        //}
        //for (int i = 0;i<s1.length();i++){
        //    dp[i][0] = 0;
        //}

        //一般情况
        for (int i = 1;i<s1.length();i++){
            for (int j = 1;j<s2.length();j++){
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = 0;
                }
                res = Math.max(res,dp[i][j]);
            }
        }
        return res;
    }
}
