package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo46_1寻找两个正序数组的中位数 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int new_len = len1 + len2;
        int[] merged = new int[new_len];
        int i = 0;
        int j = 0;
        //1.先合并成有序（升序）数组（属于暴力）
        for (int index = 0; index < new_len; index++) {
            if (i > len1) {
                merged[index] = nums2[j];
                j++;
            }
            if (j > len2) {
                merged[index] = nums1[i];
                i++;
            }
            if (nums1[i] > nums2[j]) {
                merged[index] = nums2[j];
                j++;
            } else {
                merged[index] = nums1[i];
                i++;
            }
        }
        //2.此时已经变成了一个有序的合并数组了,于是只需直接找中位数即可（分奇偶）
        int mid = new_len / 2;
        if (new_len % 2 == 0) {
            return (double) (merged[mid] + merged[mid - 1]) / 2;
        } else {
            return merged[mid];
        }
    }


    //自写一遍
    public double findMedianSortedArrays02(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int newLen = len1 + len2;
        int i = 0;
        int j = 0;
        int[] merged = new int[newLen];
        for (int index = 0; index < newLen; index++) {
            if (i > len1) {
                merged[index] = nums2[j];
                j++;
            }
            if (j > len2) {
                merged[index] = nums1[i];
                i++;
            }
            if (nums1[i] < nums1[j]) {
                merged[index] = nums1[i];
                i++;
            } else {
                merged[index] = nums2[j];
                j++;
            }
        }
        //再找中位数
        int mid = merged.length / 2;
        if (mid % 2 == 0) {
            return (merged[mid] + merged[mid - 1]) / 2;
        } else {
            return merged[mid];
        }
    }
}
