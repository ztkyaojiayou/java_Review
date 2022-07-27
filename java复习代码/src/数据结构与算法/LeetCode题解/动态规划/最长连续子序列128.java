package 数据结构与算法.LeetCode题解.动态规划;

import java.util.*;

/**
 * 128. 最长连续子序列
 * 给定一个未排序的整数数组，找出最长连续子序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */

/**
 * 思路解析：看题面一眼就可以想到动态规划，而且确实可以使用动态规划来做，
 * 但这里只提供一个更加通俗易懂的解法，即：
 * （1）先排序，
 * （2）题意要求连续序列，即可以比较nums[i] 与 nums[i−1]，
 *     1）若不相等，表示是递增的趋势，递增后需要判断是否连续，即相邻的元素差值是否为1，
 *        1.若为1，则说明加入该元素后能构成连续递增的序列，因此将curMax加1,；
 *        2.否则，说明加入该元素会破坏当前的连续序列，因此要记录当前连续递增的序列的长度max，
 *          同时要将当前序列长度curMax重置为 1，重新查找下一个连续递增序列
 *     2）若相等，那么我们当前的序列既不会增长也不会中断，我们只需要继续考虑下一个数字即可。
 * （3）下面的代码处理边界case 如[-1,0],不会比较max与cur的值，需要在最后一道防线拦截一次 Math.max(max,cur);
 *
 * 具体算法：
 * 在我们开始算法之前，首先检查输入的数组是否为空数组，如果是，函数直接返回 0 。
 * 对于其他情况，我们将 nums 数组排序，并考虑除了第一个数字以外的每个数字与它前一个数字的关系。
 * 如果当前数字和前一个数字相等，那么我们当前的序列既不会增长也不会中断，我们只需要继续考虑下一个数字。
 * 如果不相等，我们必须要检查当前数字是否能延长答案序列（也就是 nums[i] == nums[i-1] +1）。
 * 如果可以增长，那么我们将当前数字添加到当前序列并继续。
 * 否则，当前序列被中断，我们记录当前序列的长度，并将序列长度重置为 1 。
 * 由于 nums 中的最后一个数字也可能是答案序列的一部分，所以我们将当前序列的长度和记录下来的最长序列的长度的较大值返回。
 *
 * 复杂度分析
 * 时间复杂度：O(nlgn)
 * 算法核心的 for循环恰好运行 n 次，所以算法的时间复杂度由 sort 函数的调用决定，
 * 通常会采用 O(nlgn) 时间复杂度的算法。
 * 空间复杂度：O(1)（或者 O(n)）
 * 以上算法的具体实现中，由于我们将数组就低排序，所以额外的空间复杂度是常数级别的。
 * 如果不允许修改输入数组，我们需要额外的线性长度的空间来保存中间结果和排好序的数组。
 *
 * 参考链接：https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/java-pai-xu-ji-he-ha-xi-biao-bing-cha-ji-by-lzhlyl/
 */

/**
 * 方法1：排序（推荐）
 */
public class 最长连续子序列128 {
    public int longestConsecutive(int[] nums) {
        //0.特判
        if (nums == null || nums.length == 0){
            return 0;
        }
        //1.先对数组排序（升序）并定义所需变量，即最终结果值max和当前的最长序列的长度curMax
        Arrays.sort(nums);
        int len = nums.length;//数组长度
        int maxLen = 1, curMax = 1;//最终结果值maxLen，当前的最长序列的长度curMax
        //2.开始遍历数组
        for (int i = 1; i < len; i++) {
            if (nums[i] != nums[i - 1]) {//2.1若前后两个数不相等，则说明有可能能构成连续递增序列，于是继续判断；
                //而若相等，那么说明当前的序列既不会增长也不会中断，我们只需要继续考虑下一个数字，
                //则不会进入该if语句块，而是直接i++进入下一轮。
                //1）即为能构成连续递增序列的条件，于是curMax加1
                if (nums[i - 1] + 1 == nums[i]) {
                    curMax++;
                } else {//2）否则，即表示前后两数不相等且不连续，此时说明加入该元素会破坏当前的连续序列，
                    // 因此要记录当前连续递增的序列的长度maxLen,
                    // 同时要将当前序列长度curMax重置为 1，重新查找下一个连续递增序列
                    maxLen = Math.max(maxLen, curMax);
                    curMax = 1;
                }
            }
        }
        // 3.最后，返回上述的最大值max即可，
        // 但是易知，上面的代码处理在边界case 如[-1,0],是不会走else语句块的，也即不会比较max与cur的值，
        // 因此需要在最后一道防线拦截一次 Math.max(max,cur);
        return Math.max(maxLen, curMax);
        //return maxLen;//此时不能这样写，因为该语句会漏掉上面说的形如[-1,0]的边界情况
    }
}

/**
 * 方法2：使用set+贪心（推荐）
 */
class Solution02 {

    public int longestConsecutive(int[] nums) {
        int ans = 0;
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }
        for(int num : set){
            // 如果set中存在num之前的一个数，说明当前num不是连续序列的开始
            if(set.contains(num-1)) {
                continue;
            }
            int cur = num;
            // 此时num为一个连续序列的开始，现在才统计其所在连续序列长度
            // 在整个for循环中，此while循环总共走了n次，因为数组中的数只属于一个连续序列
            // 而我们每次只从连续序列的开始往后走
            while(set.contains(cur)) {
                cur++;
            }
            ans = Math.max(cur-num, ans);
        }
        return ans;
    }

    /**
     * 方法3：使用hashMap，参考本包中的demo14（无重复字符的最长子串的长度）
     * @param nums
     * @return
     */
    public int longestConsecutive02(int[] nums) {
        if (nums.length == 0) return 0;

        Map<Integer, Integer> map = new HashMap<>(); // 记录区间 [v, r]
        for (int v : nums) {
            map.put(v, v);
        }

        int max = 1;
        for (int v : nums) {
            int r = v;
            while (map.containsKey(r + 1)) {
                r = map.get(r + 1); // 利用前面已知的右边界，快速找到当前需要的右边界
            }
            map.put(v, r);
            max = Math.max(max, r - v + 1);
        }
        return max;
    }
}



