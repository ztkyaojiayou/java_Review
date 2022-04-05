package 数据结构与算法.离职后刷题;

import java.util.Arrays;

public class 常考排序算法 {
    //1）冒泡排序
    //优化版本：
    //1）只比较无序部分，有序部分不用再比较
    //2）使用标志位判断是否已有序，若有序直接跳出该循环，减少了不必要的比较次数
    //自写一遍
    public int[] 冒泡排序2(int[] arr) {
        //特判
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int lastIndex = 0;
        int wuXuIndex = arr.length - 1;
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < wuXuIndex; j++) {
                if (arr[j + 1] < arr[j]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    lastIndex = j;
                    flag = true;
                }
                //两件事
                wuXuIndex = lastIndex;
                if (!flag) {
                    break;
                }
            }
        }
        return arr;
    }


    //2）选择排序
    //自写一遍
    public int[] selectSort02(int[] arr) {
        //特判
        if (arr == null || arr.length == 0) {
            return arr;
        }
        for (int i = 0; i < arr.length; i++) {
            //找每一趟的最小值，先假定为第一个元素，但因为后面是要调换而并不是找出该值，因此这里记录下标
            int min_index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min_index]) {
                    min_index = j;
                }
            }
            //找到了就将其与首位元素调换，如此往复即可
            int temp = arr[min_index];
            arr[min_index] = arr[i];
            arr[i] = temp;
        }
        //返回排序后的arr
        return arr;
    }

    //3）快排
    //自写一遍
    public int[] QuickSort02(int[] arr, int left, int right) {
//        if (left < right) {
//            int mid = partition02(arr, left, right);
//            QuickSort02(arr, left, mid - 1);
//            QuickSort02(arr, mid + 1, right);
//        }
        //对所有元素进行快排
        if (left < right) {
            //将当前数组以中轴值为标准分为一大一小的两个小数组（此时其还是无序的，只是整体都比右边小而已），并返回该中轴值所在位置，
            // 以便下次只对其两侧的数组进行递归排序
            int mid = partition02(arr, left, right);
            //对左侧的无序数组进行递归快排
            QuickSort02(arr, left, mid - 1);
            //同理对右侧的无序数组进行递归快排，直到最后的最小数组有序，再返回去递归出整个数组有序
            QuickSort02(arr, mid + 1, right);
        }
        return arr;
    }

    //将原数组以中轴值为标准分为大小两个小数组
    //并找到中轴元素的位置（先任意选一个中轴元素，再根据该元素将数组元素
    //中轴值选哪个呢？哪个都行，一般就选第一个元素即可
    private int partition02(int[] arr, int left, int right) {
        int mid = arr[left];
        int i = left;
        int j = right;
        //开始分拣
        while (i < j) {
            while (i <= j && arr[i] <= mid) {
                i++;
            }

            while (i <= j && arr[j] >= mid) {
                j--;
            }
            //说明分拣完毕，退出循环即可
            if (i >= j) {
                break;
            }
            //将大的放mid右边，小的放mid左边
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
        //此时已经全部分拣完毕，将中轴值放入中间
        //注意别写反了（那就变成了arr[left] =arr[j] = mid了）！！！
        arr[left] = arr[j];
        arr[j] = mid;
        return j;
    }


    //4）插入排序
    //自写一遍
    public int[] 插入排序02(int[] arr) {
        //特判
        if (arr == null || arr.length == 0) {
            return arr;
        }
        for (int i = 0; i < arr.length; i++) {
            int cur = arr[i + 1];
            int last_index = i;
            while (last_index >= 0 && cur < arr[last_index]) {
                arr[last_index + 1] = arr[last_index];
                last_index--;
            }
            //将当前元素插入
            arr[last_index++] = cur;
        }
        return arr;
    }

    //5）归并排序
    //自写一遍
    public int[] MergeSort02(int[] arr) {
        //0.递归结束的条件
        if (arr.length < 2) {
            return arr;
        }
//        int mid = arr.length / 2;//中间值
//        //1.先把原数组分成左右两边
//        int[] left = Arrays.copyOfRange(arr, 0, mid);//左边数组
//        int[] right = Arrays.copyOfRange(arr, mid, arr.length);//右边数组
//        //2.再对左右两边的数组进行排序(使用递归）
//        int[] left_sorted = MergeSort02(left);
//        int[] right_sorted = MergeSort02(right);
//        //3.最后再对排好序的数组进行归并即可
//        return merge02(left_sorted, right_sorted);
        int mid = arr.length / 2;
        int[] left_arr = Arrays.copyOfRange(arr, 0, mid);
        int[] right_arr = Arrays.copyOfRange(arr, mid + 1, arr.length);
        //对每一个小数组排序--使用递归
        int[] left_sorted = MergeSort02(left_arr);
        int[] right_sorted = MergeSort02(right_arr);
        //对这两个各自有序的数组进行归并排序即可
        return merge02(left_sorted, right_sorted);
    }

    ////对这两个各自有序的数组进行归并排序
    private int[] merge02(int[] left_sorted, int[] right_sorted) {
        int len = left_sorted.length + right_sorted.length;
        //用于遍历两个排好序的数组
        int i = 0;
        int j = 0;
        //归并排序后的数组
        int[] res_arr = new int[len];
        //归并
        for (int index = 0; index < len; index++) {
            if (left_sorted[i] > right_sorted[j]) {
                res_arr[index] = right_sorted[j];
                j++;
            } else if (right_sorted[j] > left_sorted[i]) {
                res_arr[index] = left_sorted[i];
                i++;
            } else if (i >= left_sorted.length) {
                res_arr[index] = right_sorted[j];
                j++;
            } else if (j > right_sorted.length) {
                res_arr[index] = left_sorted[i++];
                i++;
            }

        }
        return res_arr;
    }
}
