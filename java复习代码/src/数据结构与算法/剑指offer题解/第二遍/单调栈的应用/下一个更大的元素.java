package 数据结构与算法.剑指offer题解.第二遍.单调栈的应用;

import java.util.HashMap;
import java.util.Stack;

/**
 * 给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
 * 找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 * 注意：nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的
 * 右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
 *
 示例 1:
 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 输出: [-1,3,-1]
 解释:
 对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
 对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
 对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。

 示例 2:
 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 输出: [3,-1]
 解释:
     对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
  

 提示：
 nums1和nums2中所有元素是唯一的。
 nums1和nums2 的数组大小都不超过1000。
 */

/**
 * 思路解析：使用单调栈（也简单，思路一模一样，只是处理时不一样而已）
 * 我们可以忽略数组 nums1，先对将 nums2 中的每一个元素，求出其下一个更大的元素。
 * 随后对于将这些答案放入哈希映射（HashMap）中，再遍历数组 nums1，并直接找出答案。
 * 对于 nums2，我们可以使用单调栈来解决这个问题。
 *
 * 我们首先把第一个元素 nums2[1] 放入栈，随后对于第二个元素 nums2[2]，如果 nums2[2] > nums2[1]，
 * 那么我们就找到了 nums2[1] 的下一个更大元素 nums2[2]，此时就可以把 nums2[1] 出栈并把 nums2[2] 入栈；
 * 如果 nums2[2] <= nums2[1]，我们就仅把 nums2[2] 入栈。对于第三个元素 nums2[3]，此时栈中有若干个元素，
 * 那么所有比 nums2[3] 小的元素都找到了下一个更大元素（即 nums2[3]），因此可以出栈，在这之后，我们将 nums2[3] 入栈，以此类推。
 *
 * 可以发现，我们维护了一个单调栈，栈中的元素从栈顶到栈底是单调不降的。
 * 当我们遇到一个新的元素 nums2[i] 时，我们判断栈顶元素是否小于 nums2[i]，
 * 如果是，那么栈顶元素的下一个更大元素即为 nums2[i]，我们将栈顶元素出栈。
 * 重复这一操作，直到栈为空或者栈顶元素大于 nums2[i]。此时我们将 nums2[i] 入栈，
 * 保持栈的单调性，并对接下来的 nums2[i + 1], nums2[i + 2] ... 执行同样的操作。
 *
 */
public class 下一个更大的元素 {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            //定义一个单调栈
            Stack< Integer > stack = new Stack < > ();
            //使用一个map来存储num2中每一个元素的下一个元素，即key-value为（当前元素，其下一个元素）
            HashMap< Integer, Integer > map = new HashMap <> ();
            //存储结果
            int[] res = new int[nums1.length];
            //开始对num2遍历（先不用管num1）
            for (int i = 0; i < nums2.length; i++) {
                while (!stack.empty() && nums2[i] > stack.peek()){
                    map.put(stack.pop(), nums2[i]);
                }
                   //若栈为空或者当前元素小于栈顶元素，则把当前元素入栈
                stack.push(nums2[i]);
            }
            //若遍历所有元素之后，栈还不为空，则说明栈中剩下的这些元素是每一下一个比它更大的元素的，因此设为-1。
            while (!stack.empty()){
                map.put(stack.pop(), -1);
            }
//再去该map中找到num1中对应的元素的下一个元素（value）即可
            for (int i = 0; i < nums1.length; i++) {
                res[i] = map.get(nums1[i]);
            }
//最后返回结果
            return res;
        }
    }
