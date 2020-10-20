package 数据结构与算法.第三遍;

import java.lang.reflect.Array;
import java.util.Arrays;

public class demo96_最长连续子序列_非动态规划版 {
    /**
     * 方法：使用排序即可（推荐），不需要使用动态规划。
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        //先排序
        Arrays.sort(nums);
        int len = nums.length;
        int maxLen = 1, curMax = 1;
        for (int i = 1; i < len; i++) {
            //若连续
            if (nums[i] != nums[i - 1]) {
                if (nums[i - 1] + 1 == nums[i]) {
                    curMax++;
                } else {
                    maxLen = Math.max(maxLen, curMax);
                    curMax = 1;
                }
            }
        }
        return Math.max(maxLen, curMax);
    }
}