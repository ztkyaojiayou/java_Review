package 数据结构与算法.LeetCode题解.数组;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。
 * 多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: 3
 *
 * 示例 2:
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 思路解析：有三种方法，即哈希字典法，排序法，摩尔投票法
 * 都很容易理解
 */

/**
 * 方法1：使用hashMap做字典映射,老生常谈啦
 * 思路
 * 我们知道出现次数最多的元素大于n/2次，所以可以用哈希表来快速统计每个元素出现的次数。
 *
 * 算法
 * 遍历整个数组，对记录每个数值出现的次数(利用HashMap，其中key为数值，value为出现次数)；
 * 接着再遍历刚才建立的HashMap中的每个Entry，寻找value值> nums.length / 2的key即可。
 */
class 寻找数组中超过一半的数169_1 {
    //1.定义一个统计各个数出现的次数的方法（其实可以不用封装成一个方法，以前都没有进行封装）
        private Map<Integer, Integer> countNums(int[] nums) {
            //使用map做字典映射即可（key为数组元素的值，value为其出现的次数/频率
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int num : nums) {//对每一个元素进行遍历，若map中存在该元素（key），则令其频率（value）为1，若存在，则使其频率加一
                if (!map.containsKey(num)) {//不存在时
                    map.put(num, 1);
                }
                else {
                    map.put(num, map.get(num)+1);//存在时
                }
            }
            //统计完后，返回该map
            return map;
        }

        //2.主方法，即寻找数组中超过一半的数
        public int majorityElement(int[] nums) {
            //2.1先获取到刚才建立的字典表map
            Map<Integer, Integer> map = countNums(nums);
            /**
             * keySet():是key的集合，可以通过get()取key；再根据key值取value。
             * entrySet()：是（key，value）键值对的集合，可以e.getKey()，e.getValue()取key和value。
             */
            //2.2再定义一个键值对，用于存放map中具有最大value（即频率）的键值对
            Map.Entry<Integer, Integer> majorityEntry = null;
            // 2.3开始遍历map中的每一个键值对entrySet，
            // 再取出其对应的value（即频率），和majorityEntry中的value相比较，
            // 若大于该value，则更新至majorityEntry中，则易知，当遍历完所有的键值对之后，majorityEntry
            // 中的value即为最大值，于是，其对应的key（即原数组中的元素值）即为所求。
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                    majorityEntry = entry;
                }
            }
            //2.4于是，当遍历完之后，取出majorityEntry中的key（即原数组中的元素值），即为所求。
            return majorityEntry.getKey();
        }
    }

/**
 * 方法2：排序
 * 既然数组中有出现次数 > n/2 的元素，那排好序之后的数组中，相同元素总是相邻的。
 * 即存在长度> ⌊ n/2 ⌋的一长串 由相同元素构成的连续子数组，那么下标为 n/2 的元素（下标从 0 开始）一定是众数。
 * 举个例子：
 * 无论是1 1 1 2 3，0 1 1 1 2还是-1 0 1 1 1，数组中间的元素总是“多数元素”，毕竟它长度> ⌊ n/2 ⌋。
 */
class Solution169_2 {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);//默认为升序
        return nums[nums.length/2];
    }
}
/**
 * 方法3：摩尔投票法，思路如下（务必掌握）：
 * 候选人(cand_num)初始化为nums[0]，票数count初始化为1。
 * 当遇到与cand_num相同的数，则票数count = count + 1，否则票数count = count - 1。
 * 当票数count为0时，更换候选人，并将票数count重置为1。
 * 遍历完数组后，cand_num即为最终答案。
 *
 * 为何这行得通呢？
 * 投票法是遇到相同的则票数 + 1，遇到不同的则票数 - 1。
 * 且由于“多数元素”的个数> n/2 ，其余元素的个数总和<= n/2。
 * 因此“多数元素”的个数 - 其余元素的个数总和 的结果 肯定 >= 1。
 * 这就相当于每个“多数元素”和其他元素 两两相互抵消，抵消到最后肯定还剩余至少1个“多数元素”。
 * 因此无论数组是1 2 1 2 1，亦或是1 2 2 1 1，总能得到正确的候选人。
 */
class Solution169_3 {
    public int majorityElement(int[] nums) {
        //1.先初始化：
        //候选人:设为第一个元素，其票数置为1
        int candi_num = nums[0], vote_count = 1;
        //2.开始遍历数组中的各个元素
        for (int i = 1; i < nums.length; i++) {
            //2.1若当前元素与候选人相等，则使其票数加一
            if (candi_num == nums[i])
                vote_count++;
            //2.2若不相等，则令其票数减1
            else if(vote_count != 0){
                vote_count--;
            }
            //2.3若票数为0了，而数组元素还没遍历完毕，则说明该候选人不是超过一半的那个数，
            //于是，把候选人换为当前值nums[i],同时将它的票数也重置为1，继续进行上述投票动作，
            //直到最后，最后的那种候选人即为所求。
            if(vote_count == 0){
                candi_num = nums[i];
                vote_count = 1;
            }
        }
        //3.最后，返回最后那个候选人即可，即为所求。
        return candi_num;
    }
}