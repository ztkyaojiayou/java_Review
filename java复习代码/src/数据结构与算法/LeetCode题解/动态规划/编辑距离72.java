package 数据结构与算法.LeetCode题解.动态规划;

/**
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：（1）插入一个字符 （2）删除一个字符 （3）替换一个字符
 *  
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 */

public class 编辑距离72 {
        public int minDistance(String word1, String word2) {
            //1.先获取两个字符串的长度，用于创建新数组
            int len1 = word1.length();
            int len2 = word2.length();

            //2.再创建一个二维数组，其大小设置为“在各自字符串的长度上加1”，目的是在各字符串前面加入一个空格，用于表示第一行和第一列的情况（即边界情况）
            //其中，dp[i][j]的含义是：把word1 的前 i 个字符转换成 word2 的前 j 个字符所需要的最少步数。（关键，务必理解）
            int[][] dp = new int[len1 + 1][len2 + 1];

            /**
             * （关键）务必理解“dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作。”，这对于这道题至关重要，现详解如下：
             * 以 word1 为 "horse"，word2 为 "ros"，且 dp[5][3] 为例，即要将 word1的前 5 个字符转换为 word2的前 3 个字符，也就是将 horse 转换为 ros，因此有：
             * (1) dp[i-1][j-1]，即先将 word1 的前 4 个字符 hors 转换为 word2 的前 2 个字符 ro，然后将第五个字符 word1[4]（因为下标基数以 0 开始） 由 e 替换为 s（即替换为 word2 的第三个字符，word2[2]）
             * (2) dp[i][j-1]，即先将 word1 的前 5 个字符 horse 转换为 word2 的前 2 个字符 ro，然后在末尾补充一个 s，即插入操作
             * (注意：由于这也是一步操作，因此状态方程后面又加了一个1，务必注意和理解)
             * (3) dp[i-1][j]，即先将 word1 的前 4 个字符 hors 转换为 word2 的前 3 个字符 ros，然后删除 word1 的第 5 个字符
             * 也即： dp[i-1][j-1]到dp[i][j]需要进行替换操作，dp[i-1][j]到d[i][j]需要进行删除操作，dp[i][j-1] 到d[i][j]需要进行添加操作。
             */
            //3.确定初始/边界情况（即第一行和第一列的情况）:从一个字符串变成空字符串，非空字符串的长度就是编辑距离
            //3.1第一行：是 word1 为空变成 word2  的前 j 个字符所需要的最少步数，易知，此时即为从无到有，直接不断地插入即可
            for (int j = 0; j < len2+1; j++) {
                dp[0][j] = j;
            }
            //3.2第一列：是 word2 为空，即把word1 的前i个字符变成空字符串所需要的最少步数，易知直接不断地删除即可
            for (int i = 0; i < len1+1; i++) {
                dp[i][0] = i;
            }

            //4.再考虑一般情况，使用状态方程转化为初始情况即可，也是先j后i
            for (int j = 1; j < len2+1; j++) {
                for (int i = 1; i < len1+1; i++) {
                    //4.1这里要考虑一种特殊情况，即当word1[i] == word2[j]时，此时只需要把word1的前i-1个字符串替换成word2的前j-1个字符串即可
                    //这里的下标 i 不包括 word[i]，同理下标 j 不包括 word[j]，由于我们加了一个空字符，也即行数和列数多设置一行、一列，
                    //也就是状态方程中的下标 i 和 j 和字符的下标 i、j 有一个位置的偏差。因此下面这里写的是word1.charAt(i - 1) == word2.charAt(j - 1)
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];//表示替换操作
                    }
                    else {//4.2对于其他情况，则都可以转化为：通过各种插入，删除，替换操作之后，选出能取到最小值的方案，最后再执行一次插入或删除或替换操作即可
                        //dp[i][j - 1]：表示插入操作；
                        //dp[i - 1][j]：表示删除操作
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                    }
                }
            }
            return dp[len1][len2];//把字符串的长度传入即可得到结果
        }
    }
