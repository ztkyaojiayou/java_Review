package 数据结构与算法.第三遍;

public class demo00_3标准的二分查找 {
    public static int binarySearch(int[] arr, int target) {//注意：二分查找的前提是数组有序
        int left = 0;
        int right = arr.length - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if (arr[mid] == target){
                return mid;
            }else if (arr[mid] < target){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        return -1;
    }


    /**
     * 写第四遍，后面都一样（2021.08.27）
     */
    public static int binarySearch02(int[] arr,int target){
        int left = 0;
        int right = arr.length - 1;
        while (left <= right){
            int mid = (left + right) /2;
            if (arr[mid] == target){
                return mid;
            }else if(arr[mid] < target){
                left = mid + 1;
            }else {
                right = mid + 1;
            }
        }
        //若没找到
        return -1;
    }
}
