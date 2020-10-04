package 数据结构与算法.LeetCode题解.数组;

import java.util.HashMap;

/**
 * 560. 和为K的连续子数组
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 *
 * 说明 :
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 */

/**
 * 思路解析：利用“前缀和+哈希表”
 * 首先计算前缀和数组preSum，
 * 因为 sum(nums[i]...nums[j])=preSum[j]-preSum[i-1].....（j>=i）
 * 而 sum(nums[i]...nums[j])==K.....（j>=i）
 * 所以，preSum[j]-preSum[i-1]==K.....（j>=i）
 * 即，preSum[j]-K=preSum[i-1].....（j>=i）
 *
 * 所以对每个前缀和元素preSum[j]，想办法判断它的值减去K的值后的前缀和preSum[j]-K是否存在于前缀和数组中即可。
 * 又因为j>=i,所以可以用哈希表hashMap记录j之前的所有前缀和元素，查看该哈希表里是否存在preSum[j]-K，
 * 若存在，它的个数就是满足等式sum(nums[i]...nums[j])==K的个数，直接加到返回值上
 */
public class 和为K的连续子数组的个数560 {
    public int subarraySum(int[] nums, int k) {
        // 1.记录区间和为 k 的个数result和数组前缀元素和curSum。
        int result = 0, curSum = 0;
        // 2.创建 hashMap 来记录数组前缀和以及当前和对应的前缀个数。（即哈希字典映射，老生常谈啦）
        HashMap<Integer, Integer> map = new HashMap<>();
        // 2.1遍历前初始化前缀和为 0 的个数为 1 。
        map.put(0, 1);
        //3.开始依次遍历并累加数组元素，求前缀和curSum（包括当前元素）。
        for (int num : nums) {
            //3.1 求前缀和curSum（包括当前元素）
            //curSum表示当前元素（包括当前元素）的累加和，而preSum则表示某个前缀和（不包括当前元素，
            //因为只有这样定义才符合该关系式：preSum = curSum - k）
            curSum += num;

            // 遍历完每个元素后，记录当前前缀和以及其个数（即出现的频率/次数）。
            //（疑惑：不应该是先添加再查找吗？？？为什么调换1）和2）的位置之后就通不过了？？？）
            if (map.containsKey(curSum)) {
                map.put(curSum, map.get(curSum) + 1);
            } else {
                map.put(curSum, 1);
            }

            // 3.2判断该前缀和减去K的值是否存在于前缀和数组中，若存在，则说明找到了一个符合要求的连续序列
            // 该关系式即解析中提到的：preSum[j]-K=preSum[i-1].....（j>=i），
            // 这里的preSum就相当于preSum[i-1]，而curSum则相当于preSum[j]
            int preSum = curSum - k;
            // 1）如果在map中有等于 curSum - k的前缀和preSum ，则表示从该前缀之后到当前元素区间和为 k，
            // 即表示找到了该区间，于是只需加上该前缀和的频率（即value）即可 。
            if (map.containsKey(preSum)) {
                // 累加 和为 k 的区间个数。
                result += map.get(preSum);
            }
        }

        //4.最后，返回结果即可
        return result;
    }
}