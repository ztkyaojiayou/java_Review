package 数据结构与算法.od机考.华为od机考练习.常规题库;

import java.util.Scanner;

/**
 * 32) 密码截取--返回有效密码串（也即对称字符串）的最大长度
 * 即求最长回文子串的长度
 * <p>
 * 描述
 * Catcher是MCA国的情报员，他工作时发现敌国会用一些对称的密码进行通信，比如像这些ABBA，ABA，A，123321，
 * 但是他们有时会在开始或结束时加入一些无关的字符以防止别国破解。
 * 比如进行下列变化 ABBA->12ABBA,ABA->ABAKK,123321->51233214　。
 * 因为截获的串太长了，而且存在多种可能的情况（abaaab可看作是aba,或baaab的加密形式），
 * Cathcer的工作量实在是太大了，他只能向电脑高手求助，你能帮Catcher找出最长的有效密码串吗？
 *
 * @author :zoutongkun
 * @date :2022/7/25 12:28 上午
 * @description :
 * @modyified By:
 */
public class Main32密码截取_求最长回文子串的长度 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        //静态函数main中只能调用静态方法哦！！！
        System.out.println(longestPalindrome(str));

    }

    /**
     * 主方法
     *
     * @param str
     * @return
     */
    public static int longestPalindrome(String str) {
        //所有位置的回文串的最大长度中的最大值，即真正的最大长度
        int res = 0;
        int len = str.length();
        //以当前位置结尾的回文串的最大长度
        int maxLen = 0;
        //dp[i][j] 表示 s[i, j] 是否是回文串,注意：包括i和j
        boolean[][] dp = new boolean[len][len];
        //初始化，易知，对角线上的肯定为回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        //一般情况，两层循环，j前i后
        for (int j = 1; j < len; j++) {
            //固定一个位置，先找出这个区间中的所有字符串构成的回文串的最大长度，再以此类推
            for (int i = 0; i < j; i++) {
                //此时表示(i+1,j-1)无法构成区间,即中间只有一个字符了，易知，当然是回文
                //易知，i相当于头指针，j相当于尾指针
                if (str.charAt(i) == str.charAt(j)) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {//否则，里面子串的回文性质就决定了整个子串的回文性质（核心）
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    //若dp[i][j]为真时，就表示s(i,j)为回文，
                    // 于是记录/更新该回文串的起始下标以及该回文串的长度
                    if (dp[i][j]) {
                        maxLen = Math.max(maxLen, j - i + 1);
                    }
                }
            }
            res = Math.max(res, maxLen);
        }
        //返回结果
        return res;
    }
}
