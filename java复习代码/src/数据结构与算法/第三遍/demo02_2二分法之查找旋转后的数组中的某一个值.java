package 数据结构与算法.第三遍;
//注意：返回索引即可

/**
 * 可类比上一个题（找最小值）
 * 情况1：当给定数组中无重复元素时
 */
public class demo02_2二分法之查找旋转后的数组中的某一个值 {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            // 务必注意：这是向下取整的，即11//2 = 5，而不是6
            int mid = (left + right) / 2;
            if (nums[mid] == target) {//说明直接找到了
                return mid;
            } else if (nums[left] <= nums[mid]) {//说明左边有序，但此时还无法确定和target的关系，易知，此时依旧有两种情况：
                //即：1）刚好target也就在这个有序子序列中，于是令right = mid-1即可
                //2)target并不在这个有序序列里面，于是要到后面的部分有序序列查找，即令left = mid+1即可
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {//此时说明右边有序，但对于target的位置，同样有两种情况
                if (target > nums[mid] && target <= nums[right]) {//target刚好在有序序列中
                    left = mid + 1;
                } else {//target并不在有序序列中，此时就在另一个部分有序序列找即可
                    right = mid - 1;
                }
            }
        }
        //若最终都没有找到，就返回-1即可
        return -1;
    }

    /**
     * 第四遍
     */
    public int search02(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target <= nums[right]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}

/**
 * 情况2：当给定数组中有重复元素时
 */
class solution002 {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            //这就是与上一题相区别的地方(后面的都相同），即有可能有重复的元素,
            //此种情况下 nums[start] == nums[mid]，分不清到底是前面有序还是后面有序，
            //此时 start++ 即可。相当于去掉一个重复的干扰项。
            if (nums[left] == nums[mid]) {
                left++;
                continue;
            }

            //后面的逻辑都相同
            //表示前半部分有序
            if (nums[left] < nums[mid]) {
                //target在前半部分时
                if (nums[mid] > target && nums[left] <= target) {
                    right = mid - 1;
                } else {  //否则，去后半部分找
                    left = mid + 1;
                }
            } else {
                //表示后半部分有序
                //target在后半部分时
                if (nums[mid] < target && nums[right] >= target) {
                    left = mid + 1;
                } else {  //否则，去后半部分找
                    right = mid - 1;
                }
            }
        }
        //一直没找到，返回false
        return false;
    }
}