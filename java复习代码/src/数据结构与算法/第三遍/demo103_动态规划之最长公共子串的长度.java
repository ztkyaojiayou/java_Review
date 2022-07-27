package 数据结构与算法.第三遍;

//典型的动态规划，很简单（美团一面）
//https://blog.csdn.net/weixin_44844089/article/details/105372089?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-1.pc_relevant_default&spm=1001.2101.3001.4242.2&utm_relevant_index=4
//子串：必须连续，需要再找dp的最大值！！！
//子序列：可以不连续，可以不用再找dp的最大值！！！

/**
 * （1）最长公共子串的长度
 * 算法设计：
 * 1.我们定义一个矩阵c【str1.length + 1】【str2.length + 1】，
 * c【i】【j】代表的含义就是Len（i，j），也就是存储str1【0…i】和str2【0…j】的最长公共子串的长度。
 * 2.每次求c【i】【j】，我们就要先判断str1【i】== str2【j】，
 * 如果相等，那么c【i】【j】 = c【i - 1】【j - 1】 + 1
 * 3.如果不等，那么问题就来了，这时候就和前面不一样了，因为公共子串是必须连续的，
 * 这时候断了，那就必须将c【i】【j】 = 0，
 * 这是为什么呢？因为c【i + 1 】【j + 1】的计算结果是要根据c【i】【j】来计算的呀，
 * 只有这样才可以让后面的计算结果都使用前面正确的计算结果。
 * 但是同时有一个要注意的地方就是，我们需要去记录result的值。
 */
public class demo103_动态规划之最长公共子串的长度 {
    public int longestCommonString(String s1, String s2) {
        //结果集
        int res = 0;
        //定义dp数组，dp[i][j]表示s1以i结尾和s2以j结尾的字符串所构成的最长公共子串的长度
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        //确定初始值，其实可以不写，因为数组默认就全为0
        //空字符串与有长度的字符串的最长公共子串的长度肯定为0呀。
        for (int j = 0; j < s2.length(); j++) {
            dp[0][j] = 0;
        }
        for (int i = 0; i < s1.length(); i++) {
            dp[i][0] = 0;
        }

        //一般情况
        for (int i = 1; i < s1.length(); i++) {
            for (int j = 1; j < s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {//若相等，就在原来的基础上加1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {//否则置零
                    dp[i][j] = 0;
                }
                //更新结果
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}


/**
 * （2）最长公共子序列的长度（整体思路相同）
 * 思路解析：
 * 1）状态表示： 定义 f[i][j]表示字符串text1的[1,i]区间和字符串text2的[1,j]区间的最长公共子序列长度（下标从1开始）。
 * <p>
 * 2）状态计算：
 * 可以根据text1[i]和text2[j]的情况，分为两种决策：
 * 1、若text1[i] == text2[j] ，也就是说两个字符串的最后一位相等，
 * 那么问题就转化成了字符串text1的[1,j-1]区间和字符串text2的[1,j-1]区间的最长公共子序列长度再加上一，
 * 即f[i][j] = f[i - 1][j - 1] + 1。（下标从1开始）
 * <p>
 * 2、若text1[i] != text2[j]，也就是说两个字符串的最后一位不相等，
 * 那么字符串text1的[1,i]区间和字符串text2的[1,j]区间的最长公共子序列长度无法延长，
 * 因此f[i][j]就会继承f[i-1][j]与f[i][j-1]中的较大值，
 * 即f[i][j] = max(f[i - 1][j],f[i][j - 1])  （ 下标从1开始）
 * <p>
 * 因此，状态转移方程为：
 * f[i][j] = f[i-1][j-1] + 1 ,当text1[i] == text2[j]。
 * f[i][j] = max(f[i - 1][j],f[i][j - 1])，当text1[i] != text2[j]​ 。
 * <p>
 * 4）初始化：
 * f[i][0] = f[0][j] = 0，(0 <=i<=n, 0<=j<=m)
 * 空字符串与有长度的字符串的最长公共子序列长度肯定为0。
 * <p>
 * 5）实现细节：
 * 我们定义的状态表示f数组和text数组下标均是从1开始的，而题目给出的text数组下标是从0开始的，
 * 为了一 一对应，在判断text1和text2数组的最后一位是否相等时，往前错一位，
 * 即使用text1[i - 1]和text2[j - 1]来判断。
 * <p>
 * 6）这里解释一下为什么f数组和text数组均定义成下标从1开始。
 * 原因是因为状态转移方程 f[i][j] = max(f[i - 1][j],f[i][j - 1]),
 * 当我们的f数组定义成下标从1开始以后，我们就可以在代码中不用对下标越界问题做出额外判断。
 * 其实我们也可以发现一个问题，就是题目给定的原数组，
 * 比如text数组，如果下标从1开始的话，状态表示会更加的清晰，推导状态转移方程的过程也会更加好理解。
 * <p>
 * 7）时间复杂度分析： O(nm)O(nm)，其中nn 和 mm 分别是字符串 text1 和 text2的长度。
 */
//参考链接：https://leetcode-cn.com/problems/longest-common-subsequence/solution/zui-chang-gong-gong-zi-xu-lie-tu-jie-dpz-6mvz/
class demo103_动态规划之最长公共子序列的长度 {
    class Solution {
        public int longestCommonSubsequence(String s1, String s2) {
            int len1 = s1.length();
            int len2 = s2.length();
            //dp[i][j]表示字符串s1以i结尾所构成的字符串和字符串s2以j结尾所构成的字符串的最长公共子序列长度（下标从1开始）
            //而题目要求的是整个公共子序列中的最长长度，因此需要随时更新dp的最大值（老套路啦）
            //但是：易知最长的公共子序列肯定是在dp[len1][len2]处取得，
            // 因为字符串越长，肯定其公共的子序列也越多呀，因此其实也可以不用再时刻更新dp的最大值。
            // 亲测二者都可以通过，但为了逻辑的一致性，这里还是写上叭~
            int[][] dp = new int[len1 + 1][len2 + 1];
            int res = 0;
            //确定初始值，其实可以不写，因为数组默认就全为0
            //空字符串与有长度的字符串的最长公共子序列长度肯定为0
            for (int i = 0; i < len1; i++) {
                dp[i][0] = 0;
            }
            for (int j = 0; j < len2; j++) {
                dp[0][j] = 0;
            }

            for (int i = 1; i < len1 + 1; i++) {
                for (int j = 1; j < len2 + 1; j++) {
                    //若其前一个字符对应相等，则易知公共长度加1
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        //此时就转化成了字符串text1的[1,j-1]区间和字符串text2的[1,j-1]区间的最长公共子序列长度再加上一（因为公共长度多1了呀）
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        //此时最长公共子序列长度无法延长
                        // f[i][j]会继承f[i-1][j]与f[i][j-1]中的较大值
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                    // 上面求得只是每一个字符串处的最长公共子串
                    // 而题目是要求整个公共子串中最长的，因此每求出一个dp值就要更新一下最大值（老套路，但该题可以省略）
                    res = Math.max(res, dp[i][j]);
                }
            }
            return res;
            //但是，不更新dp，而直接返回dp[len1][len2]也是对的
            //return dp[len1][len2];
        }
    }
}
