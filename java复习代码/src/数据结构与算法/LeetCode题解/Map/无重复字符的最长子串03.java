package 数据结构与算法.LeetCode题解.Map;

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
 * 子串是pww，wwk等很多个子串，它是连在一起的。
 * 子序列是 pwk，pke等很多个子序列 ，但是子序列中的字符在字符串中不一定是连在一起的。
 */
//使用map来遍历字符串并存储字符，同时使用滑动窗口（没懂）
    //写法1
class 无重复字符的最长子串03_1 {
    public int lengthOfLongestSubstring(String s) {
                int result = 0;
                Map<Character, Integer> map = new HashMap<>();
                for (int start = 0, end = 0; end < s.length(); end++) {
                    char endChar = s.charAt(end);//获取该窗口最末尾的字符
                    if (map.containsKey(endChar)) {//若map中已经存在该字符
                        start = Math.max(map.get(endChar), start);//则重新设置滑动窗口，即新窗口的起点改为该字符所处的索引值
                    }
                    result = Math.max(result, end - start + 1);//若没有重复，则起点不变，终点end一直后移，同时使滑动窗口值不断加一
                    map.put(s.charAt(end), end + 1);//同时把end所对应的字符当做key存入map中，把end+1作为value存入，加 1
                    // 表示从该字符位置后一个字符才开始不重复（没懂）
                }
                return result;//返回该值，即为无重复的最长子串数
            }
        }


//写法2
class Solution03_2 {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        int result = 0;
        //通过哈希表来快速定位重复字符的最新位置
        Map<Character, Integer> map = new HashMap<>();

        //快慢指针：快指针指向最新的下标，慢指针指向不重复的最小下标
        int start = 0;
        int end = 0;
        while (end < s.length()) {
            if (map.containsKey(s.charAt(end))) {//获取该窗口最末尾的字符，且若map中已经存在该字符
                start = Math.max(start,map.get(s.charAt(end)) + 1);//则重新设置滑动窗口，即新窗口的起点改为该字符所处的索引值
            }
            map.put(s.charAt(end), end);//若没有重复，则把end所对应的字符当做key存入map中，把索引值end作为value存入
            result = Math.max(result, end - start + 1);//使滑动窗口值不断加一
            end++;//若没有重复，则起点不变，终点end一直后移
        }

        return result;
    }
}