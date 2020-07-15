package 重写排序算法;

import java.util.Arrays;

/**
 * 2、选择排序
 * 2.1 算法描述：
 * 它是表现最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度，
 * 所以用到它的时候，数据规模越小越好。
 * 唯一的好处可能就是不占用额外的内存空间了吧。
 * 理论上讲，选择排序可能也是平时排序一般人想到的最多的排序方法了吧。
 *
 * 选择排序(Selection-sort)是一种简单直观的排序算法。
 * 它的工作原理：首先在未排序的序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 以此类推，直到所有元素均排序完毕。
 *
 * 2.2 排序思路：
 * n个记录的直接选择排序可经过n-1趟直接选择排序得到有序结果。具体算法描述如下：
 * 初始状态：无序区为R[1..n]，有序区为空；
 * 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。
 * 该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，
 * 使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
 * n-1趟结束，数组有序化了。
 *
 * 2.3 复杂度分析
 * 最佳情况：T(n) = O(n2)  最差情况：T(n) = O(n2)  平均情况：T(n) = O(n2)
 */
public class 选择排序 {
public int[] selectSort(int [] arr){
    //特判
    if (arr == null||arr.length == 0){
        return arr;
    }
    int temp = 0;//用于交换
for (int i = 0;i < arr.length -1;i++){//要比较“长度-1”次，因此也要循环“长度-1”次
    int min_Index = i;//最小值的下标，先就假设为第一个元素，后面会随时更新
    for (int j = i +1;j < arr.length;j ++){//每遍历一个j就完成了一趟比较
        if (arr[j] < arr[min_Index]){//只要在遍历的过程中发现有比当前最小值还小的值，就更新该最小值（这是是记录/更新其下标）
            min_Index = j;
        }
    }
    //将这一趟的最小值和第一个元素进行交换
temp = arr[min_Index];
    arr[min_Index] = arr[i];
    arr[i] = temp;
}
//最后返回排序后的数组即可
return arr;
}

    //测试
    public static void main(String[] args) {
        int[] arr = {1,4,2,3,7,5,9};
        选择排序 test = new 选择排序();
        int[] res = test.selectSort(arr);
        //注意：若bubbleSort()方法为静态方法，则可以不用创建实例对象，直接调用即可,如下所示：
        //int[] res = bubbleSort(arr);
        System.out.println(Arrays.toString(res));
        //测试结果为：[1, 2, 3, 4, 5, 7, 9]
    }
}
