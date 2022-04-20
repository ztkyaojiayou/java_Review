package 数据结构与算法.离职后刷题.第四遍必会版;

import java.util.Arrays;
//这个题主要好像都是使用的该方法，而并不是动态规划！！！

public class demo96_连续子序列的最长长度_非动态规划版 {
    /**
     * 方法：使用排序即可（推荐），不需要使用动态规划，很简单。
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
            //若连续，就加1
            if (nums[i] != nums[i - 1]) {
                if (nums[i - 1] + 1 == nums[i]) {
                    curMax++;
                } else {//否则，置零，重新计数
                    maxLen = Math.max(maxLen, curMax);
                    curMax = 1;
                }
            }
        }
        // 但是易知，上面的代码处理在边界case 如[-1,0],是不会走else语句块的，
        // 也即不会比较maxLen与curMax的值，
        // 因此需要在最后一道防线拦截一次 Math.max(maxLen,curMax);
        //return maxLen;//此时不能这样写，因为该语句会漏掉上面说的形如[-1,0]的边界情况
        return Math.max(maxLen, curMax);
    }

    //自写一遍
    public int longestConsecutive01(int[] nums) {
        int len = nums.length;
        //连续序列的长度，易知，怎么着最短也是1呀
        int curLen = 1;
        int maxLen = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i] - 1 == nums[i - 1]) {
                    curLen++;
                } else {
                    //说明当前这个连续序列到此为止，于是更新到最大长度
                    maxLen = Math.max(maxLen, curLen);
                    //重新置1，计算下一个连续序列
                    curLen = 1;
                }
            }
        }
        //最后，再加一道，保证取到最大值，因为好像有一个边界在循环体中未处理
        return Math.max(maxLen,curLen);
    }
}