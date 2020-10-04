package 数据结构与算法.LeetCode题解.数组;
/**
 *581. 最短无序连续子数组
 * 给定一个整数数组，你需要寻找一个连续的子数组，
 * 如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 你找到的子数组应是最短的，请输出它的长度。
 *
 * 示例 1:
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 *
 * 说明 :
 * 输入的数组长度范围在 [1, 10,000]。
 * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
 */

/**
 * 同时从前往后和从后往前遍历，分别得到要排序数组的右边界和左边界；
 *（1)先寻找右边界：
 * 从前往后遍历的过程中，用max记录遍历过的最大值，此时分两种情况:
 *   1）若当前值nums[i]小于之前遍历过的元素中的max，则说明nums[i]的位置不正确，属于需要排序的数组，因此将右边界更新为i；
 *   2）而若当前值nums[i]大于之前遍历过的元素中的max，则说明nums[i]的位置正确，只需更新max为当前值nums[i]即可；
 * 这样，最终就可以找到需要排序的数组的右边界，右边界之后的元素都大于max；
 *
 *（2)再寻找左边界：
 * 从后往前遍历的过程中，用min记录遍历过的最小值，此时分两种情况：
 *   1）若当前值nums[j]大于之前遍历过的元素中的min（注意，此时是从后往前遍历），
 *      则说明nums[j]的位置不正确，应该属于需要排序的数组，因此将左边界更新为j；
 *   2）而若当前值nums[j]小于min，则说明nums[j]的位置正确，只需更新min为当前值nums[j]即可；
 * 这样，最终就可以找到需要排序的数组的左边界，左边界之前的元素都小于min；
 *
 *（3）小技巧：从前往后遍历和从后往前遍历两个过程可以分两次循环完成，也可以放一起完成，这样的话就有：j=len-i-1
 */
public class 最短无序连续子数组581 {
        public int findUnsortedSubarray(int[] nums) {
            int len = nums.length;
            int max = nums[0];
            int min = nums[len-1];
            int left = 0, right = -1;
            //寻找右边界
            for(int i=0;i<len;i++){
                if(max > nums[i]){
                    right = i;
                }else{
                    max = nums[i];
                }
                //寻找左边界
                if(min < nums[len-i-1]){
                    left = len-i-1;
                }else{
                    min = nums[len-i-1];
                }
            }
            //返回结果
            return right-left+1;
        }
}

/**
 * 优化了一点的暴力破解算法：（不推荐)
 * 在这种方法中，我们基于选择排序使用如下想法：我们遍历 nums 数组中的每一个元素 nums[i] 。
 * 对于每一个元素，我们尝试找到它在正确顺序数组中的位置，即将它与每一个满足 i < j < n 的 nums[j] 做比较，这里 n 是 nums 数组的长度。
 * 如果存在 nums[j] 比 nums[i] 小，这意味着 nums[i] 和 nums[j]都不在排序后数组中的正确位置。
 * 因此我们需要交换这两个元素使它们到正确的位置上。
 * 但这里我们并不需要真的交换两个元素，我们只需要标记两个元素在原数组中的位置 i 和 j 。这两个元素标记着目前无序数组的边界。
 * 因此，在所有的 nums[i]中，我们找到最左边不在正确位置的 nums[i] ，这标记了最短无序子数组的左边界（l）。
 * 类似地，我们找到最右边不在正确位置的边界 nums[j] ，它标记了最短无序子数组的右边界 (r) 。
 */
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int left = nums.length, right = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    right = Math.max(right, j);
                    left = Math.min(left, i);
                }
            }
        }
        return right - left < 0 ? 0 : right - left + 1;
    }
}