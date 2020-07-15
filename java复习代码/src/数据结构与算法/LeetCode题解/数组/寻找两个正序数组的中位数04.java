package 数据结构与算法.LeetCode题解.数组;

/**
 * 给定两个大小为 m 和 n 的正序（从小到大，但可能有重复数字）数组 nums1 和 nums2。
 * 请你找出这两个正序数组（合并之后）的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 *
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 */
//方法1：普通方法：先合并成一个数组，再直接找（达不到题目要求的时间复杂度，不推荐）
public class 寻找两个正序数组的中位数04 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
int[] merged = new int[nums1.length + nums2.length];//创建一个能容纳这两个数组的新数组
int x = 0,y = 0;//分别表示两个数组的索引
for (int i= 0;i < merged.length; i++){//这里的逻辑其实就相当于使用两个for循环对两个数组所对应的两个索引分别往后遍历，谁对应的值小就先把谁添加进新数组
if(y == nums2.length || (x < nums1.length && nums1[x] < nums2[y])){
    //当第二个数组已经添加完毕或者第一个数组还没添加完毕且比第二个数组小时，就把第一个数组的该值添加进新数组，同时向后遍历
    merged[i] = nums1[x];
    x++;
}else {//反之，添加第二个数组中的该值，同时向后遍历
    merged[i] = nums2[y];
    y++;
}
}
//此时两个数组已经合并成了一个数组，且是有序的，现在直接找即可
        if(merged.length % 2 == 0){//数组长度为偶数时
            int r = merged.length/2;//最中间两个数的右边那个数（因为这里使用的是长度/2，而索引是从0开始的）
            return (double) (merged[r-1] + merged[r])/2;
        }else {//数组长为奇数时
            return merged[merged.length/2];
        }
    }
}

//推荐（但没懂）
//因为是2个有序数组，获取中位数，
//其实根本不需要得到一个完整的有序数组，只需要拿到处于中间位置的2个数值即可，
//只需循环(m+n)/2次，即可比对出中间数值。
class Solution03 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 特殊情况判断，但可要可不要
        // if (nums1 == null || nums1.length == 0) {
        //    int length = nums2.length;
        //    int middle = length / 2;
        //    if (length % 2 == 0) {
        //        return (nums2[middle] + nums2[middle - 1]) / 2.0;
        //    } else {
        //        return nums2[middle];
        //    }
        //}
        //if (nums2 == null || nums2.length == 0) {
        //    int length = nums1.length;
        //    int middle = length / 2;
        //    if (length % 2 == 0) {
        //        return (nums1[middle] + nums1[middle - 1]) / 2.0;
        //    } else {
        //        return nums1[middle];
        //    }
        //}

        int l1 = nums1.length, i1 = 0;
        int l2 = nums2.length,i2 = 0;
        int totalLength = l1 + l2;
        int middle = totalLength / 2;
        int currentIndex = 0;

        int last = 0;
        int current = 0;
        while (currentIndex <= middle) {
            currentIndex++;
            last = current;
            if (i2 == l2) {
                current = nums1[i1];
                i1++;
                continue;
            }
            if (i1 == l1) {
                current = nums2[i2];
                i2++;
                continue;
            }
            if (nums1[i1] <= nums2[i2]) {
                current = nums1[i1];
                i1++;
            } else {
                current = nums2[i2];
                i2++;
            }

        }
        //找中位数
        if (totalLength % 2 == 0) {
            return (last + current) / 2.0;
        } else {
            return current;
        }
    }
}