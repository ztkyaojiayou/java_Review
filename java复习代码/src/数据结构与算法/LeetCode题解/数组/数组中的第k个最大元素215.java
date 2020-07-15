package 数据结构与算法.LeetCode题解.数组;

import java.util.PriorityQueue;

/**
 *215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */

/**
 * 思路解析：
 * 1、基于堆
 * 小顶堆（min-heap）有个重要的性质——每个结点的值均不大于其左右孩子结点的值，则堆顶元素即为整个堆的最小值，
 * 因此若建立一个只包含k个元素的小顶堆，则弹出的堆顶元素即为第k大元素（其实也是该堆中的最小元素），即为所求。
 * Java中的PriorityQueue实现了数据结构堆，通过指定comparator字段来表示小顶堆或大顶堆，默认为null，表示自然序，即升序。
 *
 * 堆解决这类Top K问题的思路：堆中维护当前扫描到的最大K个数，其后每一次的扫描到的元素，
 * 若大于堆顶，则入堆，然后删除堆顶；依此往复，直至扫描完所有元素。
 */
public class 数组中的第k个最大元素215 {
        public int findKthLargest(int[] nums, int k) {
            //使用一个堆（一般就要java已经实现了的优先队列 PriorityQueue<>）
            PriorityQueue<Integer> minQueue = new PriorityQueue<>(k);
            for (int num : nums) {//遍历数组
                //若还没有存满或当前值比最小值（该值就为第k大的值（其实也是该堆中的最小元素））大，就把当前值存入，
                //此时堆顶的元素依然是第k大元素，只是可能不是之前那个元素啦（它内部会自动实现，咱们不用管）
                if (minQueue.size() < k || num > minQueue.peek()) {
                    minQueue.offer(num);//存入
                }
                if (minQueue.size() > k) {//若大于了k个数，则把堆顶元素删除
                    minQueue.poll();
                }
            }
            return minQueue.peek();//返回栈顶元素即可，即为第k大的数
        }
    }


