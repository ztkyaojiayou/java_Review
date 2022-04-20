package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo71_二分查找之某一数字在排序数组中出现的次数 {
    //1.暴力（主要是因为数组中的元素可能无限多）
    public int GetNumberOfK01(int[] arr, int k) {
        int count = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            if (arr[i] == k) {
                count++;
            }
        }
        return count;
    }

    //2.使用二分查找
    public int GetNumberOfK02(int[] arr, int k) {
        int index = binarySearch(arr, k);
        //此时已经找到了k第一次出现的位置，因此只需往右寻找最后出现的位置即可
        int count = 0;
        for (int i = index; i < arr.length; i++) {//此时，就不用考虑元素还有很多了
            if (arr[i] == k) {
                count++;
            }
        }
        return count;
    }

    /**
     * 注意：对于使用二分查找来求目标元素的第一次或最后一次出现的位置时，有如下结论：
     * 1、当我们要找到目标元素出现的第一个位置时候：当中间值大于等于目标元素的时候，
     * 我们要保留当前中间值的位置，并且在左边继续查找。
     * 这句话用条件语句表述就是：
     * if（a【mid】 >= K)
     * right = mid;
     * <p>
     * 2、当我们要找目标元素出现的最后一个位置的时候：当中间值小于等于目标元素的时候，
     * 我们要保留中间值的位置，并且在右边继续查找。
     * if(a[mid] <= K)
     * left = mid;
     */
    private int binarySearch(int[] num, int k) {
        int len = num.length;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            //这里不判断num[mid]与目标值的大小
            if (num[mid] >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        //返回的是最左边那个下标（切记）
        return left;
    }


    //自写一遍：利用二分查找目标元素的第一次或最后一次出现的位置
    //二分查找的前提是数组要有序
    private int binarySearch02(int[] num, int k) {
        int len = num.length;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (num[mid] >= k) {
                right = mid;
            } else {
                //为什么是加1，因为num[mid]<k而不是等于k呀，因此mid处的值肯定不符合条件呀，因此要跳过
                left = mid + 1;
            }
        }
        //返回最左边那个下标
        return left;
    }
}
