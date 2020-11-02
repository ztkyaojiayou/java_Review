package 数据结构与算法.LeetCode题解.数组;

/**
 * 33. 搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 *
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */

/**
 * 解题思路：二分查找
 * 题目要求 O(logN) 的时间复杂度，基本可以断定本题是需要使用二分查找，怎么分是关键。
 * 由于题目说了数字无重复，举个例子：
 * 1 2 3 4 5 6 7 可以大致分为两类，
 * 第一类 2 3 4 5 6 7 1 这种，也就是 nums[start] <= nums[mid]。此例子中就是 2 <= 5。
 * 这种情况下，前半部分有序。因此如果 nums[start] <=target<nums[mid]，则在前半部分找，否则去后半部分找。
 * 第二类 6 7 1 2 3 4 5 这种，也就是 nums[start] > nums[mid]。此例子中就是 6 > 2。
 * 这种情况下，后半部分有序。因此如果 nums[mid] <target<=nums[end]，则在后半部分找，否则去前半部分找。
 */
public class 搜索旋转排序数组的某个值33 {
    public int search(int[] nums, int target) {
        //0.特判
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;  //二分查找的起始位置
        int end = nums.length - 1;//二分查找的末尾位置
        int mid;//二分查找的中间值

        while (start <= end) {//1.开始二分查找
            mid = start + (end - start) / 2;//二分查找的中间值的计算公式
            //1.0二分查找结束的条件，即当中间值即为目标值时，则直接返回该结果(下标）即可
            if (nums[mid] == target) {
                return mid;
            }

            //1.1当前半部分有序时（注意此处用小于等于），此时再考虑目标值target在哪一段（优先考虑在有序的那一段）
            if (nums[start] <= nums[mid]) {
                //1）若target在前半部分，则调整二分查找的end为mid - 1
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {//2）若target在后半部分，则调整二分查找的start为mid + 1
                    start = mid + 1;
                }
            } else {//1.2当后半部分有序,同理
                ////1）若target在后半部分，则调整二分查找的start为mid + 1
                if (target <= nums[end] && target > nums[mid]) {
                    start = mid + 1;
                } else {//2）若target在前半部分，则调整二分查找的end为mid - 1
                    end = mid - 1;
                }
            }
        }
        //1.3若前后部分都没有找到，则返回-1即可
        return -1;

    }
}

/**
 * 81. 搜索旋转排序数组 II(进阶版，有重复元素）
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 * 编写一个函数来判断给定的目标值是否存在于数组中。
 * 若存在返回 true，否则返回 false。
 *
 * 示例 1:
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 *
 * 示例 2:
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 *
 * 进阶:
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums 可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 */
class solution81{
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            }
            //这就是与上一题相区别的地方(后面的都相同），即有可能有重复的元素,
            //此种情况下 nums[start] == nums[mid]，分不清到底是前面有序还是后面有序，
            //此时 start++ 即可。相当于去掉一个重复的干扰项。
            if (nums[start] == nums[mid]) {
                start++;
                continue;
            }
            //前半部分有序
            if (nums[start] < nums[mid]) {
                //target在前半部分
                if (nums[mid] > target && nums[start] <= target) {
                    end = mid - 1;
                } else {  //否则，去后半部分找
                    start = mid + 1;
                }
            } else {
                //后半部分有序
                //target在后半部分
                if (nums[mid] < target && nums[end] >= target) {
                    start = mid + 1;
                } else {  //否则，去后半部分找
                    end = mid - 1;
                }
            }
        }
        //一直没找到，返回false
        return false;
    }
}