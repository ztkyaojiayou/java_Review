package 重写排序算法.第一遍;

import java.util.Arrays;

/**
 * 3、插入排序
 * 3.1 算法描述
 * 插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。
 * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 * 插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），
 * 因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
 *
 * 3.2 排序思路
 * 一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：
 *
 * 从第一个元素开始，该元素可以认为已经被排序；
 * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
 * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
 * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 * 将新元素插入到该位置后；
 * 重复步骤2~5。
 *
 * 3.3 复杂度分析
 * 最佳情况：T(n) = O(n)   最坏情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
 */
public class 插入排序_普通版 {
    public int[] insertSort(int[] arr){
      if (arr == null||arr.length == 0){
           return arr;
}
      for (int i = 0;i< arr.length - 1;i++){
          int cur = arr[i+1];//当前位置（从第二个位置开始）
          int pre_Index = i;//当前位置的前一个位置
          // 取出下一个元素，在已经排序的元素序列中从后向前扫描；
          // 如果当前元素小于其前一个元素（从已排序序列中的最后一个元素开始），则将该元素移到下一位置；
          // 重复上述步骤(即使其前一个元素的下标往前移动一位，也即pre_Index--，继续比较），
          // 直到在已排序的序列中找到一个小于或者等于当前元素的元素，将当前元素插入到该位置后即可；
          while (pre_Index >= 0 && cur < arr[pre_Index]){
            arr[pre_Index+1] = arr[pre_Index];
            pre_Index--;//延伸：在希尔排序中，只需把这里的（减）1 改为（减）gap 即可，line42也同理。
          }
          arr[pre_Index +1] = cur;
      }
      return arr;
    }

    //测试
    public static void main(String[] args) {
        int[] arr = {1,4,2,3,7,5,9};
        插入排序_普通版 test = new 插入排序_普通版();
        int[] res = test.insertSort(arr);
        //注意：若bubbleSort()方法为静态方法，则可以不用创建实例对象，直接调用即可,如下所示：
        //int[] res = bubbleSort(arr);
        System.out.println(Arrays.toString(res));
        //测试结果为：[1, 2, 3, 4, 5, 7, 9]
    }
}
