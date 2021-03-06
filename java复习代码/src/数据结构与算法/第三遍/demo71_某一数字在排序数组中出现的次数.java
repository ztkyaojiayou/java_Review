package 数据结构与算法.第三遍;

public class demo71_某一数字在排序数组中出现的次数 {
    //1.暴力
    public int GetNumberOfK01(int [] arr , int k) {
        int count = 0;
        int len = arr.length;
        for (int i = 0;i<len;i++){
            if (arr[i] == k){
                count++;
            }
        }
        return count;
    }

    //2.使用二分查找
    public int GetNumberOfK02(int [] arr , int k) {
        int index = binarySearch(arr, k);
        //此时已经找到了k第一次出现的位置，因此只需往右寻找最后出现的位置即可
        int count = 1;
        for (int i = index+1;i<arr.length;i++){
            if (arr[i] == k){
                count++;
            }
        }
        //这一步似乎没必要了，因为左边肯定没有该元素了呀~
        //for (int i = index-1;i>0;i--){
        //    if (arr[i] == k){
        //        count++;
        //    }
        //}
        return count;
    }

    /**
     * 注意：对于使用二分查找来求目标元素的第一次或最后一次出现的元素时，有如下结论：
     * 1、当我们要找到目标元素出现的第一个位置时候：当中间值大于等于目标元素的时候，
     * 我们要保留当前中间值的位置，并且在左边继续查找。
     * 这句话用条件语句表述就是：
     * if（a【mid】 >= K)
     * right = mid;
     *
     * 2、当我们要找目标元素出现的最后一个位置的时候：当中间值小于等于目标元素的时候，
     * 我们要保留中间值的位置，并且在右边继续查找。
     * if(a[mid] <= K)
     * left = mid;
     */
    private int binarySearch(int[] num,int k){
        int len = num.length;
        int left = 0;
        int right = len-1;
        while (left < right){
            int mid = (left + right)/2;
            if (num[mid] >= k){
                right = mid;
            } else {
                left = mid+1;
            }
        }
        return left;
    }
}
