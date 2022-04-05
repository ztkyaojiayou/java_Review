package 重写排序算法.第二遍;

import java.util.Arrays;

public class 插入排序test {
    public static int[] insertSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int pre_index = i;
            int cur = arr[i + 1];
            while (cur < arr[pre_index]) {
                arr[pre_index + 1] = arr[pre_index];
                pre_index--;
            }
            arr[pre_index + 1] = cur;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 2, 4, 3, 7, 6, 8};
        int[] res = 插入排序test.insertSort(arr);
        System.out.println(Arrays.toString(res));
    }
}

class insertSort {
    public static int[] insertSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int cur = arr[i + 1];
            int pre_index = i;
            while (cur < arr[pre_index]) {
                arr[pre_index + 1] = arr[pre_index];
                pre_index--;
            }
            pre_index++;//因为此时cur已经大于了arr[pre_index]，
            // 因此要把cur放在pre_index的后一个位置，也即索引值要先加1
            arr[pre_index] = cur;
        }
        return arr;
    }

    //测试
    public static void main(String[] args) {
        int[] arr = {1, 5, 2, 4, 3, 7, 6, 8};
        int[] res = insertSort.insertSort(arr);
        System.out.println(Arrays.toString(res));
    }
}
