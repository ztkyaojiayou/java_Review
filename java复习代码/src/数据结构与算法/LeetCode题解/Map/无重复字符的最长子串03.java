package 数据结构与算法.LeetCode题解.Map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: "bacabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的一个最长子串是 "bac"，所以其长度为 3。（或者abc）
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 注意：区分子串和子序列
 * 给定 "pwwkew" ，
 * 子串是类似于pww，wwk等的串，它是连在一起的。
 * 子序列是 pwk，pke等很多个子序列 ，但是子序列中的字符在字符串中不一定是连在一起的。
 */

/**
 * 使用map来遍历字符串并存储字符，同时使用双指针
 *
 * 设立左指针a和右指针b,均从0开始
 * b指针向右侧伸缩{
 * 对每个A[b]，判断是否在之前的数组出现过;
 * 如果出现，指针a指向出现过的位置的下一个位置，因为子串是要求连在一起的；
 * 更新右指针和最大长度;
 * 参考链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-cshi-xian-/
 */
    //写法1(推荐）
class 无重复字符的最长子串的长度03_1 {
    public int lengthOfLongestSubstring(String s) {
        //表示最长子串的长度
                int res = 0;
        //通过哈希表来快速定位重复字符的最新位置（可以使用数组优化，new int[26])
                Map<Character, Integer> map = new HashMap<>();
        //快慢指针：快指针end指向最新的下标，慢指针start指向不重复的最小下标
                for (int start = 0,end= 0;end < s.length(); end++) {
                    char endChar = s.charAt(end);//获取该窗口最末尾的字符
                    if (map.containsKey(endChar)) {//若map中已经存在该字符
                        //则重新设置子串，即新子串的起点改为该字符在map中的位置的下一个位置，即当前字符所处的索引值+1
                        start = Math.max(start,map.get(endChar)+1);
                    }
                    //若没有重复,把end所对应的字符当做key存入map中，把end作为value存入
                    map.put(s.charAt(end), end);
                    //更新结果
                    res = Math.max(res, end - start + 1);//起点不变，终点end一直后移

                }
                //返回结果
                return res;
            }
        }


//写法2
class Solution03_2 {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        //结果
        int res = 0;
        //通过哈希表来快速定位重复字符的最新位置
        Map<Character, Integer> map = new HashMap<>();
        //快慢指针：快指针指向最新的下标，慢指针指向不重复的最小下标
        int start = 0;
        int end = 0;
        while (end < s.length()) {
            if (map.containsKey(s.charAt(end))) {//获取该窗口最末尾的字符，且若map中已经存在该字符
                start = Math.max(start,map.get(s.charAt(end)) + 1);//则重新设置滑动窗口，即新窗口的起点改为该字符所处的索引值+1
            }
            map.put(s.charAt(end), end);//若没有重复，则把end所对应的字符当做key存入map中，把索引值end作为value存入
            res = Math.max(res, end - start + 1);//同时，更新结果集
            end++;//若没有重复，则起点不变，终点end一直后移
        }
        //返回该值，即为无重复的最长子串数
        return res;
    }
}