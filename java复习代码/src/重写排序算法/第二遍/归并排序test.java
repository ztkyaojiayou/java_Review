package 重写排序算法.第二遍;

import java.util.Arrays;

/**
 * 归并排序（需要一个额外的数组）
 * * 基本思路：先把待排数组拆分成若干个只包含一个元素的子数组，
 * * 再对每相邻的两个子数组进行排序再合并，直到排序结束
 * * 这里的排序方法很重要。
 */
public class 归并排序test {
    public static int[] MergeSort(int[] arr) {
        //递归结束的条件
        if (arr.length < 2) {
            return arr;
        }
        //先找中间值
        int mid = (arr.length) / 2;
        //再对数组进行拆分，拆分成左右两个数组
        int[] left = Arrays.copyOfRange(arr, 0, mid);//注意：该方法所生成的新数组中，包括下标from，不包括上标to。
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        //再对该数组进行排序（这是归并排序的前提），使用递归即可
        int[] left_sorted = MergeSort(left);
        int[] right_sorted = MergeSort(right);
        //接着再使用归并，对每一对最小的且已经排好序了的数组进行合并（即归并方法要传入两个（已经排好序了的）数组）
        return merge(left_sorted, right_sorted);
    }

    //归并的具体实现（非常简单）
    public static int[] merge(int[] arr1, int[] arr2) {
        //先定义一个额外的数组,长度为传入的两个已经排好序了的要进行归并的数组的长度和，用于存储结果
        int[] arr_res = new int[arr1.length + arr2.length];
        //定义两个指针遍历，用于遍历传入的两个数组
        int i = 0;//用于遍历arr1
        int j = 0;//用于遍历arr2
        for (int index = 0; index < arr_res.length; index++) {
            if (i >= arr1.length) {
                //此时说明arr1已经遍历完毕，则只需把还没有遍历完的arr2的元素一次添加进行即可
                arr_res[index] = arr2[j];
                j++;
            } else if (j >= arr2.length) {//同理，此时说明arr2已经遍历完毕
                arr_res[index] = arr1[i];
                i++;
                //此时说明都有元素，于是要先把较小元素添加进去，分两种情况即可
            } else if (arr1[i] > arr2[j]) {
                arr_res[index] = arr2[j];
                j++;
            } else {
                arr_res[index] = arr1[i];
                i++;
            }
        }
        //填充完arr_res数组后，返回即可
        return arr_res;
    }

    //测试
    public static void main(String[] args) {
        int[] test_arr = {1, 5, 2, 3, 7, 6, 9, 8};
        int[] res = 归并排序test.MergeSort(test_arr);
        System.out.println(Arrays.toString(res));
    }
}
