package 数据结构与算法.第三遍;


//https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/solution/si-lu-qing-xi-ming-liao-kan-bu-dong-bu-cun-zai-de-/
public class demo09_1双指针之最短无序连续子数组 {
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int left_max = nums[0];//即表示从左边遍历时的最大元素,此时是寻找右边界（即最后一个小于该最大值的元素的下标）
        int right_min = nums[len - 1];//即表示从右边遍历时的最小元素，,此时是寻找左边界（即最后一个大于该最小值的元素的下标）
        //表示无序数组的左右边界
        int left_index = 0;
        int right_index = -1;
        //从左向右遍历，寻找右边界
        for (int i = 0; i < len; i++) {
            if (nums[i] < left_max) {
                right_index = i;
            } else {
                left_max = nums[i];
            }
            //再寻找左边界（注意，是在同一循环里，节省了开销，巧妙~）
            if (nums[len - i - 1] > right_min) {
                left_index = len - i - 1;
            } else {
                right_min = nums[len - i - 1];
            }
        }
        //最后返回该无序区间的长度即可
        return right_index - left_index + 1;
    }

    //自写一遍
    public int findUnsortedSubarray02(int[] nums) {
        int len = nums.length;
        int left_index = 0;
        int right_index = -1;
        int left_max = nums[0];
        int right_min = nums[len - 1];
        for (int i = 0; i < len; i++) {
            //找右边界，同时维护最大值
            if (nums[i] > left_max) {
                left_max = nums[i];
            } else {
                right_index = i;
            }

            if (nums[len - i - 1] < right_min) {
                //还有小的，就将该值设为最小值
                right_min = nums[len - i - 1];
            } else {
                //若大于，就更新其左边界，目标是找到最左边那个还大于这个最小值的元素的下标
                left_index = len - i - 1;
            }
        }
        //返回长度即可
        return right_index - left_index + 1;
    }
}
