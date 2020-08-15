package 数据结构与算法.剑指offer题解.堆;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 题目： 输入n个整数，找出其中最小的k个数。
 * 例如输入 4 、5 、1、6、2、7、3 、8 这 8 个数字，
 *    则最小的 4 个数字是 1 、2、3 、4
 *
 * 【解】
 * 对于n个整数中最小的K个数的查找，可以使用各种排序算法：冒泡/堆排/快排/希尔排序等等。
 * 将此n个整数从小到大排序之后，前k个数就是所求的结果。
 * 但是当原数组中的数据顺序不可修改，并且n的值过于大的时候，各种排序算法要将n个数加载到内存中
 * 即：如果是海量数据中查找出最小的k个数，那么这种办法是效率很低的。
 *
 * 【法1】：O（N）(改变了数组原有的顺序)
 *     使用快速排序：
 *     以第k个数为基准，从头开始遍历，
 *      使得比第k个数字小放在arr[k]的左边，比第k个数字大的放在右边
 *
 * 【法2】：O（N*logK) 基于堆排的思想
 *      先建立一个含有k个元素的大顶堆，
 *      每次新来一个数 和堆顶比较，如果大于堆顶，跳过
 *      如果小于堆顶，交换，然后重新调整二叉堆
 *      直到arr结束
 *
 */
public class heap44最小的k个数 {

    //方法一：堆排序（推荐，常用于解决topK问题）
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] nums, int k) {
        if (k > nums.length || k <= 0){
            return new ArrayList<>();
        }
        PriorityQueue<Integer> maxheap = new PriorityQueue<>((o1, o2) -> o2 - o1);  //降序，实现小顶堆
        for (int i= 0;i < nums.length; i++){
            maxheap.add(nums[i]);//直接往里面加即可，优先队列会自动排序（降序排列，队首元素为最大元素）
            if (maxheap.size() > k){
                maxheap.poll();//弹出堆顶元素，即最大的元素，即保持该队列中的元素始终为k个，且为最小的k个，符合题意
            }
        }
        //最后，使用构造函数的方式创建一个list用于返回，且就把该优先队列作为构造函数的参数传入，妙哉~
        return new ArrayList<>(maxheap);
    }

    /**
     * 以下方法参考一下即可，不推荐
     * @param arr
     * @param k
     * @return
     */

    // 方法2：采用快排
    public int[] getLeastNumbers1(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k == 0) {
            return new int[0];
        }
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi) {
            int index = partition(arr, lo, hi);
            if (index == k - 1) {
                // find the kth smallest element.
                break;
            } else if (index < k - 1) {
                lo = index + 1;
            } else {
                hi = index - 1;
            }
        }

        // partition function will change the array,
        // let k elements in the front is the smallest k elements.
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) {
            ret[i] = arr[i];
        }
        return ret;
    }

    // divide target range array to smaller hi value part and other part.
    private int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int smaller = start - 1;
        while (start < end) {
            if (arr[start] < pivot) {
                swap(arr, start++, ++smaller);
            } else {
                start++;
            }
        }
        swap(arr, end, ++smaller);
        return smaller;
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }

    //方法三： 也是排序，不过使用的是冒泡排序，大同小异
    public ArrayList<Integer> GetLeastNumbers_Solution02(int [] input, int k) {
        ArrayList<Integer> list=new ArrayList<Integer>();
        if(input==null||input.length==0||k>input.length)
            return list;
        int len=input.length;
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                if(input[i]>input[j]){
                    int temp=input[i];
                    input[i]=input[j];
                    input[j]=temp;
                }
            }
            if(i>=k)
                break;
        }
        for(int i=0;i<k;i++)
            list.add(input[i]);
        return list;
    }

}
