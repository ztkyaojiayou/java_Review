package 重写排序算法.第二遍;

import java.util.Arrays;

//一点资讯一面，快排：
public class 快排test {
    public int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = Mid(arr, left, right);
            quickSort(arr, left, mid - 1);
            quickSort(arr, mid + 1, right);
        }
        return arr;
    }

    public int Mid(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            /**
             * while语句属于循环语句，在判断时，如果条件为true，则会继续判断，直到false为止，
             * 即会进行多次判断（除非一开始条件就是错的）
             * if语句属于条件判断语句，如果条件是true，则继续执行，为false则跳出语句不执行，
             * 即只会进行单次判断
             *
             * while与if语句的最大的相同点是都有至少一步的判断。
             * 最大的不同点是：IF语句运行完毕后，接着运行下面的语句。而While中的执行语句运行完毕后，还要进行继续判断条件是否符合循环条件，根据判断的条件，返回执行语句或继续运行下面的程序。
             */
            while (i <= j && arr[i] <= pivot) {//因此这里必须写while，而不是if
                i++;
            }
            while (i <= j && arr[j] >= pivot) {//同理，需用while，而不是if
                j--;
            }

            if (i >= j) {//这里则只需要if就行，即只要有一次不满足就退出
                break;
            }
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

        }
        arr[left] = arr[j];
        arr[j] = pivot;
        return j;
    }

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        //System.out.println("Hello World!");
        int[] arr = {1, 6, 2, 7, 5, 4};
        快排test 快排test = new 快排test();
        int[] res = 快排test.quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(res));
    }
}
