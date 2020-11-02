package 数据结构与算法.第三遍;

public class demo09_双指针之最短无序连续子数组 {
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int left_max = nums[0];//即表示从左边遍历时的最大元素,此时是寻找右边界
        int right_min = nums[len-1];//即表示从右边遍历时的最小元素，,此时是寻找左边界
        //表示无序数组的左右边界
        int left_index = 0;
        int right_index = -1;
        //从左向右遍历，寻找右边界
        for (int i = 0;i<len;i++){
            if (nums[i] < left_max){
                right_index = i;
            }else {
                left_max = nums[i];
            }
            //再寻找右边界（注意，是在统一循环里，节省了开销，巧妙~）
            if (nums[len-i-1] > right_min){
                left_index = len-i-1;
            }else {
                right_min = nums[len-i-1];
            }

        }
        //最后返回该无序区间的长度即可
        return right_index-left_index+1;
    }
}
