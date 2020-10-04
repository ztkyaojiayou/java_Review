package 数据结构与算法.LeetCode题解.堆;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 *
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 * 提示：
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 */

/**
 * 解题思路：有两种方法，但思路都类似
 * （1）基于字典表/哈希映射+最小堆
 * 题目最终需要返回的是前 kk 个频率最大的元素，可以想到借助堆这种数据结构，
 * 对于 kk 频率之后的元素不用再去处理，进一步优化时间复杂度。
 *
 * 1)具体操作为：
 * 借助 哈希表 来建立数字和其出现次数的映射，遍历一遍数组统计元素的频率
 * 维护一个元素数目为 k 的最小堆
 * 每次都将新的元素与堆顶元素（堆中频率最小的元素）进行比较
 * 如果新的元素的频率比堆顶端的元素大，则弹出堆顶端的元素，将新的元素添加进堆中
 * 最终，堆中的 k 个元素即为前 k 个高频元素
 *
 * 2)复杂度分析
 * 时间复杂度：O(nlogk)，n 表示数组的长度。
 * 首先，遍历一遍数组统计元素的频率，这一系列操作的时间复杂度是 O(n)；
 * 接着，遍历用于存储元素频率的 map，如果元素的频率大于最小堆中顶部的元素，则将顶部的元素删除并将该元素加入堆中，这里维护堆的数目是 k，
 * 所以这一系列操作的时间复杂度是 O(nlogk) 的；因此，总的时间复杂度是 O(nlog⁡k)。
 * 空间复杂度：O(n)，最坏情况下（每个元素都不同），map 需要存储 n 个键值对，
 * 优先队列需要存储 k 个元素，因此，空间复杂度是 O(n)。
 *
 * （2）基于字典表/哈希映射+桶排序
 * 1)思路分析：
 * 首先依旧使用哈希表统计频率，统计完成后，创建一个数组，将频率作为数组下标，
 * 对于出现频率不同的数字集合，存入对应的数组下标即可。
 *
 * 2）复杂度分析
 * 时间复杂度：O(n)，n 表示数组的长度。首先，遍历一遍数组统计元素的频率，这一系列操作的时间复杂度是 O(n)；
 * 桶的数量为 n+1，所以桶排序的时间复杂度为 O(n)；因此，总的时间复杂度是 O(n)。
 * 空间复杂度：很明显为 O(n)。
 *
 * 参考链接：https://leetcode-cn.com/problems/top-k-frequent-elements/solution/leetcode-di-347-hao-wen-ti-qian-k-ge-gao-pin-yuan-/
 */

//(1)基于字典表/哈希映射+最小堆
class Solution347 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        //1.使用hashMap，统计每个元素出现的次数，元素作为key，元素出现的次数为value（标准代码，务必记住）
        HashMap<Integer,Integer> map = new HashMap();
        for(int num : nums){
            if (map.containsKey(num)) {//若map中已有该元素，则使其value加1，即统计其频率
                map.put(num, map.get(num) + 1);
            } else {//若是第一次出现，则令其value为1
                map.put(num, 1);
            }
        }
        //2.遍历map，用最小堆保存频率最大的k个元素(此时map中为各个元素的出现次数），堆顶为最小元素（但也刚好是第k大的元素）
        //java中一般是要优先队列PriorityQueue来实现最小/大堆（默认为最小堆，即堆顶为最小元素）
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            //既然默认已经是升序，为什么还要重写compare方法自定义比较规则？
            //因为虽然是升序，但默认比较的是key，而我们这里是要比较value，
            //即各元素出现的次数/频率，而不是key。
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });
        //2.1遍历map的key（即为数组中的各不同元素），把其添加至堆中（标准代码，题215也有用到）
        for (Integer key : map.keySet()) {
            if (pq.size() < k) {//2.1.1若堆中元素还没满，则先直接加入
                pq.add(key);
                //2.1.2而若当前要加入的元素大于堆顶元素（堆中的最小元素），则说明要把现在堆顶的元素删除，而用当前值代替它
            } else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();//先删除堆顶元素
                pq.add(key);//再用当前元素代替（即添加进去，但此时该元素并不一定就在堆顶，但堆顶元素肯定是堆中元素的最小值，这是由最小堆内部实现的，咱们不用管）
            }
        }
        // 3.最后，取出（遍历即可）最小堆中的k个元素存入list中，该list即为所求
        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.remove());//remove：既删除同时又返回该元素，一举两得。
        }
        //4.返回该结果集即可
        return res;
    }
}

//(2)基于字典表/哈希映射+桶排序(思路完全相同，只是把堆换成了桶排序而已）
class 前k个高频元素347 {
    //基于桶排序求解「前 K 个高频元素」
        public List<Integer> topKFrequent(int[] nums, int k) {
            List<Integer> res = new ArrayList();
            //2.1（同上）使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
            HashMap<Integer,Integer> map = new HashMap();
            for(int num : nums){
                if (map.containsKey(num)) {
                    map.put(num, map.get(num) + 1);
                } else {
                    map.put(num, 1);
                }
            }
           /**
             * 2.2使用桶排序（而非最小堆）
             * 要使用一个存放list的数组
             * 将各数字出现的频率作为数组下标，对于出现频率不同的数字集合，存入对应的数组下标，
             * 即该数组比较特殊，它的每一个下标代表对应的数字所出现的频率/次数，而其对应的元素都是一个list，
             * 则易知，前k个高频元素即为该数组中后面k个。（核心）
             */
            List<Integer>[] list = new List[nums.length+1];
            for(int key : map.keySet()){
                // 获取出现的次数/频率（即value）作为下标
                int i = map.get(key);
                if(list[i] == null){//若为空，则存入一个空list
                    list[i] = new ArrayList();
                }
                //否则，就把其key（即为数组中的各不同元素）存入list中
                list[i].add(key);
            }

            //2.3再倒序遍历数组，以获取前k个高频元素的排列，存入结果集list中，该结果集即为所求
            for(int i = list.length - 1;i >= 0 && res.size() < k;i--){
                if(list[i] == null) continue;
                res.addAll(list[i]);
            }
            //2.4最后，返回该结果集即可
            return res;
        }
    }
