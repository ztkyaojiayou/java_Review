package 数据结构与算法.LeetCode题解.动态规划;

/**
 * 647. 回文子串（类比第五题，思路相同）
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 * 示例 1:
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 *
 * 示例 2:
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 *
 * 注意:
 * 输入的字符串长度不会超过1000。
 */

/**
 * 解题思路：
 * 这道题基本和第5题的思路是一样的，同样是使用动态规划的方法。
 * 只是这里需要找的是回文子串的个数，
 * 首先第一步，我们需要定义dp数组的含义，定义二维布尔数组dp[i][j]dp[i][j]数组表示：
 * 字符串s[i⋯j]是否为回文子串，如果是，dp[i][j] = true，如果不是，dp[i][j] = false。
 *
 * 如何我们现在已经知道了dp[i+1][j-1]了，那我们如何计算dp[i][j]呢？
 *
 * 通过观察，我们发现：
 * 如果s[i] == s[j]，那么说明只要dp[i+1][j-1]是回文子串，那么是dp[i][j]也就是回文子串。
 * 如果s[i] != s[j]，那么说明dp[i][j]必定不是回文子串。
 *
 * 但要注意一个特例，即当i和j相邻的时候，此时无法使用状态方程求解，要单独拎出来考虑。
 * 由于最终需要输出最长的回文子串的数量，我们在遍历的时候记录一下即可。
 */
public class 回文子串的总个数647 {
        public int countSubstrings(String s) {
            //0.特判
            if(s == null || s.equals("")){
                return 0;
            }

            int len = s.length();//结果值初始化为该字符串的长度值，
            // 因为至少每个单独的字符都是一个回文子串，所以至少就有那么多个回文子串。

            //1.定义一个 boolean类型的二维数组
            //其中，dp[i][j] 表示从i到j所构成的字符串是否为回文子串的状态，
            //若是，则为true，否则，就为false，且每个位置的初始值默认就是false。
            boolean[][] dp = new boolean[len][len];

            //2.确定初始情况，对角线肯定为true，因为它就是表示由两个相同的字符所构成的字符串，肯定为回文子串。
            for(int i = 0; i<len; i++) {
                dp[i][i] = true;
            }

            //3.再看一般情况，使用状态方程
            for (int j = 1; j < len; j++) {//使用遍历，即以j结尾，依次判断前面的字符串是否为回文子串，
                // 如：若j = 5，则判读（0,5），（1,5）...(4,5)的情况
                for (int i = 0; i < j; i++) {
                    if(s.charAt(i) == s.charAt(j)) {//3.1只有当两头的字符相等时，才有可能是回文子串，
                        //即只需考虑这种情况即可（否则肯定不是回文子串（默认就是false））

                        //3.2特殊情况，1)即若i和j之间没有元素了（即相邻），则就不能通过递推关系式计算啦，
                        //此时要其两头都相等，则最终的结果也是回文子串。
                        if(j-i == 1 || j - i < 3){//2)根据表达式 [i + 1, j - 1] 不构成区间（即无法再分解成更小的问题啦），
                            // 即长度严格小于 2时，即 j - 1 - (i + 1) + 1< 2，整理得 j- i< 3。
                            dp[i][j] = true;
                        } else{
                            //否则，就可以转化到其里面区间（即区间（i+1，j-1）的字符串是否是回文子串
                            dp[i][j] = dp[i+1][j-1];
                        }
                    }
                    //4.每判断完一个字符串，就在结果中记一笔（只有这里和第5题不同，其他地方基本都相同，不难）
                    if(dp[i][j]){//若为真，即表示从i到j所构成的字符串是回文子串，结果值加一。
                        len++;
                    }
                }
            }
            //5.返回结果即可
            return len;
        }
    }
