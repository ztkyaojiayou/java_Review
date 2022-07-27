package 数据结构与算法.第三遍;

import java.util.HashSet;
import java.util.List;
/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
 * 判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * <p>
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * <p>
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * <p>
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * <p>
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
//解法：使用动态规划算法
//通过每个word与“s”是不是匹配。我们定义一个布尔类型的dp数组，长度为s.length + 1。
//其中，dp[i]表示字符串s中的前i个字符能否拆分成wordDict。我们将每次的都记录下来。

/**
 * 这个方法的想法是对于给定的字符串（s）可以被拆分成子问题 s1 和 s2 。
 * 如果这些子问题都可以独立地被拆分成符合要求的子问题，那么整个问题 s 也可以满足。
 * 也就是，如果 "catsanddog" 可以拆分成两个子字符串s1： "catsand" 和 s2："dog" 。
 * 子问题 "catsand" 可以进一步拆分成 "cats" 和 "and" ，
 * 这两个独立的部分都是字典的一部分，所以 "catsand" 满足题意条件，
 * 再往前， "catsand" 和 "dog" 也分别满足条件，
 * 所以整个字符串 "catsanddog" 也满足条件。
 */
public class demo90_动态规划之单词能否被拆分 {
    public boolean wordBreak(String str, List<String> wordDict) {
        //先把字典表存入set中，用于判断
        HashSet<String> set = new HashSet<>(wordDict);
        int len = str.length();
        //dp[i]就表示字符串s中的前i个字符能否拆分成字典表wordDict中的字符
        boolean[] dp = new boolean[len + 1];
        //初始化
        dp[0] = true;
        //一般情况
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(str.substring(j, i))) {//把前i个字符分成两块，分别判断既可
                    dp[i] = true;
                    break;//对于当前字符，找到就将其设为true，同时退出当前循环
                }
            }
        }
        return dp[len];
    }

    //自写一遍
    public boolean wordBreak01(String str, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        int len = str.length();
        //dp表示前i个字符能被拆分（因此长度往后移一位，因为最短也是前1个字符呀）
        //默认全为false
        Boolean[] dp = new Boolean[len + 1];
        // 字典表wordDict中默认含有空字符串
        dp[0] = true;
        for (int i = 1; i < len + 1; i++) {
            //固定当前字符，将由该字符结尾所构成的字符串拆分成两个子字符串分别判断是否可拆分
            //注意：是要对所有的拆分结果做判断，因此需要使用一个循环，但只要有一种拆分方式在字典中即可
            for (int j = 0; j < i; j++) {
                //拆成两段分别判断
                //只要有一种拆分方式在字典中就说明可以拆分
                if (dp[j] && set.contains(str.substring(j, i))) {
                    dp[i] = true;
                    //再看下一个dp
                    break;
                }
            }
        }
        return dp[len];
    }
}
