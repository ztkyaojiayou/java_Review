package 数据结构与算法.LeetCode题解.栈;

import java.util.HashMap;
import java.util.Stack;

/**
 * 入门版（掌握）
 * 496. 下一个更大元素 I
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
 */

/**
 * 思路解析：使用单调栈（也简单，思路一模一样，只是处理时不一样而已）
 * 我们可以忽略数组 nums1，先对将 nums2 中的每一个元素，求出其下一个更大的元素。
 * 随后对于将这些答案放入哈希映射（HashMap）中，再遍历数组 nums1，并直接找出答案。
 * 对于 nums2，我们可以使用单调栈来解决这个问题。
 */
public class 下一个更大的元素496 {
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

/**
 * 进阶版：
 * 503. 下一个更大元素 II（尽量掌握吧）
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
 * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，
 * 这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 * 示例 1:
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 */

/**
 * 思路解析：同样是使用单调栈
 * 我们首先把第一个元素 A[1] 放入栈，随后对于第二个元素 A[2]，如果 A[2] > A[1]，
 * 那么我们就找到了 A[1] 的下一个更大元素 A[2]，此时就可以把 A[1] 出栈并把 A[2] 入栈；
 * 如果 A[2] <= A[1]，我们就仅把 A[2] 入栈。对于第三个元素 A[3]，此时栈中有若干个元素，
 * 那么所有比 A[3] 小的元素都找到了下一个更大元素（即 A[3]），因此可以出栈，在这之后，我们将 A[3] 入栈，以此类推。
 *
 * 可以发现，我们维护了一个单调栈，栈中的元素从栈顶到栈底是单调不降的。
 * 当我们遇到一个新的元素 A[i] 时，我们判断栈顶元素是否小于 A[i]，如果是，那么栈顶元素的下一个更大元素即为 A[i]，
 * 我们将栈顶元素出栈。重复这一操作，直到栈为空或者栈顶元素大于 A[i]。
 * 此时我们将 A[i] 入栈，保持栈的单调性，并对接下来的 A[i + 1], A[i + 2] ... 执行同样的操作。
 *
 * 由于这道题的数组是循环数组，因此我们需要将每个元素都入栈两次（关键）。
 * 这样可能会有元素出栈找过一次，即得到了超过一个“下一个更大元素”，我们只需要保留第一个出栈的结果即可。
 *
 * 注意，题解是从右往左加入元素，其原理和上述是类似的。
 *
 */
class Solution503 {
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * nums.length - 1; i >= 0; i--) {
            while (!stack.empty() && nums[stack.peek()] <= nums[i % nums.length]) {
                stack.pop();
            }
            res[i % nums.length] = stack.empty() ? -1 : nums[stack.peek()];
            stack.push(i % nums.length);
        }
        return res;
    }
}

