package 数据结构与算法.离职后刷题.第四遍必会版;

import java.util.HashMap;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * 输入: "bacabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的一个最长子串是 "bac"，所以其长度为 3。（或者abc）
 * <p>
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 */
//使用map即可，明确map中存放什么东西最重要！
public class demo14_1map之无重复字符的最长子串的长度 {
    public int lengthSubString(String str) {
        int res = 0;
        //左边界
        int left = 0;
        //存放当前字符和对应的下标
        HashMap<Character, Integer> map = new HashMap<>();
        for (int right = 0; right < str.length(); right++) {
            //若存在，则把左下标移至该值在原map中（也即第一次出现的位置）对应的下标的下一个位置，重新计算
            if (map.containsKey(str.charAt(right))) {
                left = map.get(str.charAt(right)) + 1;
            }
            //存储右边界元素和对应的下表，
            //而若为重复元素，则更新其下标（并不是else的关系！！！）
            map.put(str.charAt(right), right);
            //随时更新结果
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    //自写一遍
    public int lengthSubString02(String str) {
        int res = 0;
        int left = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int right = 0; right < str.length(); right++) {
            if (map.containsKey(str.charAt(right))) {
                left = map.get(str.charAt(right)) + 1;
            }
            map.put(str.charAt(right), right);
            //更新长度
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}
