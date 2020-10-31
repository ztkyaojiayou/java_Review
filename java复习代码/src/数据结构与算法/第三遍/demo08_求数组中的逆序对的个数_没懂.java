package 数据结构与算法.第三遍;

public class demo08_求数组中的逆序对的个数_没懂 {
    //1.暴力解法，不推荐
    public int reversePairs01(int[] nums) {
        int cnt = 0;
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] > nums[j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }


    //2.使用归并排序,推荐
    public int reversePairs02(int[] nums) {
        return merge(nums, 0, nums.length - 1);
    }

    public int merge(int[] arr, int start, int end) {
        if (start == end) return 0;
        int mid = (start + end) / 2;
        int count = merge(arr, start, mid) + merge(arr, mid + 1, end);

        int[] temp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;
        while (i <= mid && j <= end) {
            count += arr[i] <= arr[j] ? j - (mid + 1) : 0;
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) {
            count += j - (mid + 1);
            temp[k++] = arr[i++];
        }
        while (j <= end){
            temp[k++] = arr[j++];
        }
        System.arraycopy(temp, 0, arr, start, end - start + 1);
        return count;
    }
}


