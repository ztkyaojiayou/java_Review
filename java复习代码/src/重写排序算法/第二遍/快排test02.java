package 重写排序算法.第二遍;

import java.util.Arrays;

public class 快排test02 {
    public static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = Mid(arr, left, right);//获取中间值
//获取到了mid之后，就可以接着使用递归来对其两边的数组进行排序了
            arr = quickSort(arr, left, mid - 1);
            arr = quickSort(arr, mid + 1, right);
        }
        return arr;
    }

    //找中间值
    private static int Mid(int[] arr, int left, int right) {
        int mid = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (i <= j && arr[i] <= mid) {
                i++;
            }
            while (i <= j && arr[j] >= mid) {
                j--;
            }
            if (i >= j) {
                break;
            }
            //此时说明mid两边的这两个元素应该对调
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
        //此时表示调换完毕，也即mid左边的元素全比mid小，右边的元素全比它大，于是只需将mid元素插到中间即可
        //具体做法则是，将mid从原来所在的位置left掉换到现在的中间位置（即i或j）,并返回现在mid所在的位置即可。
        arr[left] = arr[j];//换成i好像就不行，为啥????是因为此时i>j???
        arr[j] = mid;
        return j;
    }

    //测试
    public static void main(String[] args) {
        int[] test_arr = {1, 3, 2, 6, 4, 8, 7, 5};
        int[] res = 快排test02.quickSort(test_arr, 0, test_arr.length - 1);
        System.out.println(Arrays.toString(res));
    }
}
