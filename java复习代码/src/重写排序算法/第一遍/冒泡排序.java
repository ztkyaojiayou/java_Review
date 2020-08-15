package 重写排序算法.第一遍;

import java.util.Arrays;

/**
 * 1、冒泡排序
 * 1.1 排序思路：
 * 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
 * 针对所有的元素重复以上的步骤，除了最后一个；
 * 重复步骤1~3，直到排序完成。
 *
 * 1.2 复杂度分析
 * 最佳情况：T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
 */
public class 冒泡排序 {
    public int[] bubbleSort(int[] arr){
        if (arr == null||arr.length == 0){
            return arr;
        }
        int temp = 0;//用于交换的中间值
        for (int i = 0;i < arr.length;i++){//交换次数比长度少1（每一次交换就称为一次冒泡）
            for (int j = 0;j < arr.length -1-i;j++){//每一趟排序都是把剩下的数组中的最大值放到最后，
                // 易知一趟的最后一次冒泡是arr[j]和arr[arr.length - 1 - i]进行比较
                //（即倒数第二个数，因为倒数第一个数已经是上一次冒泡之后的最大值，无需再比较）
                if (arr[j+1] < arr[j]){
                    temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    //测试
    public static void main(String[] args) {
        int[] arr = {1,4,2,3,7,5,9};
        冒泡排序 test = new 冒泡排序();
        int[] res = test.bubbleSort(arr);
        //注意：若bubbleSort()方法为静态方法，则可以不用创建实例对象，直接调用即可,如下所示：
        //int[] res = bubbleSort(arr);
        System.out.println(Arrays.toString(res));
    }
    //测试结果为：[1, 2, 3, 4, 5, 7, 9]
}
