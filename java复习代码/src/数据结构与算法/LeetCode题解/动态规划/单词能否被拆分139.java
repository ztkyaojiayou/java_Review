package 数据结构与算法.LeetCode题解.动态规划;

import java.util.HashSet;
import java.util.List;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
 * 判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 *
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
public class 单词能否被拆分139 {
        public boolean wordBreak(String str, List<String> wordDict) {
           //1.把字典表存入HashSet中
            HashSet<String> set = new HashSet<>(wordDict); //public HashSet(Collection<? extends E> c)构造一个包含指定 collection 中的元素的新 set。
            //2.再创建一个数组，长度设置为s.length() + 1，dp[i]就表示字符串s中的前i个字符能否拆分成字典表wordDict中的字符
            int len = str.length();
            boolean[] dp = new boolean[len + 1];
            //3.确定初始值/边界情况
            dp[0] = true;//字典表wordDict中默认含有空字符串
            //4.考虑一般情况，使用状态方程
            for(int i = 1; i<=len; i++){//下标i用于考虑所有从当前字符串开始的可能的子字符串
                for(int j = 0; j<i; j++){//通过下标j把每一个子字符串又拆分成两个子字符串（即前j个字符构成的字符串s1和由j至i所构成的字符串s2），
                    // 以此类推，直到转化为初始情况

                    //不断地利用j来切分该字符串，只要存在一个切分点使得这两个被切分的子字符串在字典表中就将其设为true
                    // 若这两个子字符串都在字典表中，则让dp[i]为true，否则令其为false。
                    if(dp[j] && set.contains(str.substring(j,i))){//dp[j]就表示前j个字符串组成的子字符串，[j,i]就表示后一个子字符串
                        dp[i] = true;//即说明字符串s中的前i个字符能拆分成字典表wordDict中的字符
                        break;//对于当前的i，只要找到一个符合要求的值即可终止后面的查找，直接跳到下一个i即可
                    }
                }
            }
            //5.最后返回结果即可
            return dp[len];
        }
    }

