package 数据结构与算法.第二遍.动态规划;

/**
 * 题目描述：给定两个字符串，求出他们之间最长相同子字符串长度。
 * 解题步骤：采用动态规划，与求解最长公共子序列类似，只不过状态定义要修改。
 * 1、 状态定义：dp[i][j]表示以s1[i]和s2[j]为最后一个元素的最长公共子串，
 * 如果最长公共子串存在，公共子串的最后一个元素一定是s1[i]或者s2[j]他们相等。
 * 2、 状态转移方程：
 * 如果s1[i]和s2[j]相等，那么：dp[i][j] = dp[i-1][j-1]+1；
 * 如果s1[i]和s2[j]不相等，则：dp[i][j] = 0；
 * 3、 初始化：初始dp[0][j]和dp[i][0]都为false
 * 4、 输出：dp[i][j]的最大值
 */
public class 最长公共子串_美团一面 {
    public int longestCommonString(String s1,String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        ////初始化，其实可以不写，因为数组默认就全为0
        //for (int j = 0;j<s2.length();j++){
        //    dp[0][j] = 0;
        //}
        //for (int i = 0;i<s1.length();i++){
        //    dp[i][0] = 0;
        //}
        int max_len = 0;
        //两层循环，对于动态规划，不用考虑这种性能问题，因为一般都有两层循环
        for (int i = 1; i <= s1.length(); i++) {//相当于先固定第一个字符，每个字符都要匹配一遍
            for (int j = 1; j <= s2.length(); j++) {
                if(s1.charAt(i-1)==s2.charAt(j-1)){//如果对应的元素相等，则说明存在公共子串，于是使用状态方程迭代
                    dp[i][j] = dp[i-1][j-1]+1;
                } else{//否则置零，因为二者还没有公共子串
                    dp[i][j] = 0;
                }
                //更新以第一个字符为基准的最长子串的"最大长度"
                max_len = Math.max(max_len, dp[i][j]);
            }
        }
        //最后，返回最大长度
        return max_len;
    }
}
