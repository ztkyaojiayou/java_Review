package 秋招笔试.欧克云链;

public class test01 {
    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) { //说明继续查找
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;//即目标数target在数组左边，需要向左边查找
            } else {
                left = mid + 1; //即目标数target在数组右边，需要向右边查找
            }
        }
        return -1;
    }
}
