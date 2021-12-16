package 数据结构与算法.剑指offer第一版.排序;

/**
 * 题目描述
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组,求出这个数组中的逆序对的总数P。
 * 并将P对1000000007取模的结果输出。
 * 即输出P%1000000007
 *
 * 输入描述:
 * 题目保证输入的数组中没有的相同的数字
 * 数据范围：
 * 	对于%50的数据,size<=10^4
 * 	对于%75的数据,size<=10^5
 * 	对于%100的数据,size<=2*10^5
 *
 * 示例1
 * 输入
 * 1,2,3,4,5,6,7,0
 * 输出
 * 7
 */

/**
 * 思路解析：归并排序
 * 参考链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/solution/shu-zu-zhong-de-ni-xu-dui-by-leetcode-solution/
 */
class 数组中的逆序对50_1 {
    private long cnt = 0;
    private int[] tmp;  // 在这里声明辅助数组，而不是在 merge() 递归函数中声明

    public int reversePairs(int[] nums) {
        tmp = new int[nums.length];
        //归并排序
        mergeSort(nums, 0, nums.length - 1);
        return (int) (cnt % 1000000007);
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (right - left < 1)
            return;
        int mid = left + (right - left) / 2;
        //先排序
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        //再合并，同时统计逆序数的对数
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid || j <= right) {
            if (i > mid)
                tmp[k] = nums[j++];
            else if (j > right)
                tmp[k] = nums[i++];
            else if (nums[i] <= nums[j])
                tmp[k] = nums[i++];
            else {
                tmp[k] = nums[j++];
                cnt += mid - i + 1;  // nums[i] > nums[j]，说明 nums[i...mid] 都大于 nums[j]
            }
            k++;
        }
        for (k = left; k <= right; k++){
            nums[k] = tmp[k];
        }
    }
}



class Solution50_2 {
    public int reversePairs(int[] nums) {
        int len = nums.length;

        if (len < 2) {
            return 0;
        }

        int[] new_nums = new int[len];
        for (int i = 0; i < len; i++) {
            new_nums[i] = nums[i];
        }

        int[] temp = new int[len];
        return reversePairs(new_nums, 0, len - 1, temp);
    }

    /**
     * nums[left..right] 计算逆序对个数并且排序
     *
     * @param nums
     * @param left
     * @param right
     * @param temp
     * @return
     */
    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int leftPairs = reversePairs(nums, left, mid, temp);
        int rightPairs = reversePairs(nums, mid + 1, right, temp);

        if (nums[mid] <= nums[mid + 1]) {
            return leftPairs + rightPairs;
        }

        int crossPairs = mergeAndCount(nums, left, mid, right, temp);
        return leftPairs + rightPairs + crossPairs;
    }

    /**
     * nums[left..mid] 有序，nums[mid + 1..right] 有序
     *
     * @param nums
     * @param left
     * @param mid
     * @param right
     * @param temp
     * @return
     */
    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;

        int count = 0;
        for (int k = left; k <= right; k++) {

            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i];
                i++;
            } else {
                nums[k] = temp[j];
                j++;
                count += (mid - i + 1);
            }
        }
        return count;
    }
}
