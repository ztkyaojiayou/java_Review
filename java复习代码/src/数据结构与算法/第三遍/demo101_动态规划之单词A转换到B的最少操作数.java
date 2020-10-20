package 数据结构与算法.第三遍;

/**
 * 较难
 */
public class demo101_动态规划之单词A转换到B的最少操作数 {
    //注意：对一个单词进行如下三种操作：（1）插入一个字符 （2）删除一个字符 （3）替换一个字符
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        //dp[i][j]的含义是：把word1 的前 i 个字符转换成 word2 的前 j 个字符所需要的最少步数。（关键，务必理解）
        int[][] dp = new int[len1 + 1][len2 + 1];
        //初始化
        for (int i = 0;i<len1+1;i++){
          dp[i][0] = i;
        }
        for (int j = 0;j<len2+1;j++){
            dp[0][j] = j;
        }
        //一般情况
        for (int j = 1;j<len2+1;j++){
            for (int i = 1;i<len1+1;i++){
                if (word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1],dp[i-1][j]),dp[i][j-1]) + 1;
                }
            }
        }
        //最后返回结果即可
        return dp[len1][len2];
    }
}
