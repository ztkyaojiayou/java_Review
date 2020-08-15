package 重写排序算法.第一遍;

import java.util.Arrays;

/**
 * 5、归并排序（Merge Sort）
 * 和选择排序一样，归并排序的性能不受输入数据的影响，
 * 但表现比选择排序好的多，因为始终都是O(n log n）的时间复杂度。
 * 代价是需要额外的内存空间。
 *
 * 归并排序是建立在归并操作上的一种有效的排序算法。
 * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 归并排序是一种稳定的排序方法。将已有序的子序列合并，得到完全有序的序列；
 * 即先使每个子序列有序，再使子序列段间有序。
 * 若将两个有序表合并成一个有序表，称为2-路归并。
 *
 * 5.1 排序思路
 * 把长度为n的输入序列分成两个长度为n/2的子序列；
 * 对这两个子序列分别采用归并排序；
 * 将两个排序好的子序列合并成一个最终的排序序列。
 */
public class 归并排序 {
    public int[] MergeSort(int[] arr){
        //0.递归结束的条件（不是什么特判）
        if (arr.length < 2){
            return arr;
        }
        //中间下标
        int mid = arr.length / 2;
        //1.先从中间分开，对左右两边的数组进行排序（默认为升序）
        int[] left = Arrays.copyOfRange(arr,0,mid);
        int[] right = Arrays.copyOfRange(arr,mid,arr.length);
        //2.再递归，然后调用归并方法进行归并
        int[] left_sorted = MergeSort(left);
        int[] right_sorted = MergeSort(right);
        return merge(left_sorted,right_sorted);//注意：这里面由于嵌套了排序方法，
        // 因此会通过递归方法一直到最后分成两个两个排好序的数组
    }

    //再归并，将两段排好序的数组结合成一个排序数组
    public int[] merge(int[] left, int[] right) {
        //结果数组（额外的数组）
        int[] res_arr = new int[left.length + right.length];
        for (int index = 0,i = 0,j = 0; index < res_arr.length; index++){
            //2.1先处理特殊情况，即若某一边还有剩余数据而另一边已经全部添加进res_arr数组时，
            //则把它依次全部存入res_arr数组中
            if (i >= left.length){//2.1.1此时即左边走完了，右边还剩了数据
                res_arr[index] = right[j++];
            } else if (j >= right.length){//2.1.2此时即右边走完了，左边还剩了数据
                res_arr[index] = left[i++];
            }
            //2.2再处理一般情况，即当两边还都有数据时，则先把较小值先存入。
            else if (left[i] > right[j]){//2.2.1若左边的当前数据大于右边的当前数据时，则先把右边的数据先存入
                res_arr[index] = right[j++];
            } else{//2.2.2若左边的当前数据小于右边的当前数据时，则先把左边的数据先存入
                res_arr[index] = left[i++];
            }

        }
        //3.最后返回排好序的结果数组
        return res_arr;
    }

    //测试
    public static void main(String[] args) {
        int[] arr = {1,4,2,3,7,5,9,6,8};
        归并排序 test = new 归并排序();
        int[] res = test.MergeSort(arr);
        //注意：若bubbleSort()方法为静态方法，则可以不用创建实例对象，直接调用即可,如下所示：
        //int[] res = bubbleSort(arr);
        System.out.println(Arrays.toString(res));
        //测试结果为：[1, 2, 3, 4, 5, 7, 9]
    }
}
