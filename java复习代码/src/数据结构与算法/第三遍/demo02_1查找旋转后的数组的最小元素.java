package 数据结构与算法.第三遍;

public class demo02_1查找旋转后的数组的最小元素 {
    public int minNumberInRotateArray(int[] nums) {//这个数组已经是旋转之后的数组了，如{5,6,1,2,3},{3,4,5,1,2}
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            //特殊情况，特殊处理
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                return minNumber(nums, left, right);
            }else if (nums[mid] < nums[right]){//说明最小值在左边
                right = mid;
            }else {//说明最小值在右边
                left = mid+1;
            }
        }
        return nums[left];
    }

    //使用原始方法找最小值即可
    private int minNumber(int[] nums, int left, int right) {
        int min_number = 0;
        for (int i = left; i < right - 1; i++) {
            min_number = Math.min(nums[i], nums[i + 1]);
        }
        return min_number;
    }
}
