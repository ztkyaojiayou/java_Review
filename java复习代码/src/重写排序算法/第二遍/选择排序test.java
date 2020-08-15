package 重写排序算法.第二遍;

import java.util.Arrays;

/**
 *  * 方法：（1）在被排序数组中选出最小值，与原首位元素互换，
 *  *           易知此元素即为本数组中的最小元素，不再参与接下来的排序,
 *              则接下来的数组是从第二个元素开始的，即首部元素为原数组的第二个元素
 *  *      （2）再从剩下数组中继续不断地进行相同操作（选最小值并交换），直至排序结束
 */
//练习1：
public class 选择排序test {
public static int[] selectSort(int[] arr){
    //特判
    if (arr == null || arr.length ==0){
        return null;
        //return arr;
    }
    //找到数组中比第一个元素还小的元素的下标，并与第一个元素互换
    for (int i = 0;i < arr.length;i++){
        int min_index = i;
        for (int j = i+1;j < arr.length;j++){//循环一遍后就可以找到该最小元素的下标了,注意：其实这里的j可以就从i开始。
            if (arr[j] < arr[min_index]){
                //于是互换，说明还有比假想值更小的
                min_index = j;
            }
        }
        //此时min_index就是真最小值的下标了，
        // 于是只需将其与第一个元素arr[i](每比较一趟后，这个首元素都会往前一位，所以是一个变量i，而不是0,）互换即可
        int temp = arr[i];
        arr[i] = arr[min_index];
        arr[min_index] = temp;
    }
    return arr;
}

//测试
    public static void main(String[] args) {
        int[] test_arr = {0,1,3,2,6,4,8,7,12,16,13,11};
        int[] res = 选择排序test.selectSort(test_arr);
        System.out.println(Arrays.toString(res));
    }
}

//练习2：
class selectSort03{
    public static int[] selectSort(int[] arr){
        if (arr == null || arr.length == 0){
            return null;
        }
        for (int i = 0;i < arr.length; i++){
            int min_index = i;//表示假定最小元素的下标为第一个元素，而使用i则是表示每一次选择时的第一个元素
            for (int j = i;j < arr.length;j++){//j就从i开始也是可以的。
                if (arr[j] < arr[min_index]){
                    min_index = j;
                }
            }
            //此时已经找到了这一趟的选择排序的最小值，
            //于是与这一趟的第一个元素互换,使得第一个元素就为这个最小值即可
            int temp = arr[min_index];
            arr[min_index] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    //测试
    public static void main(String[] args) {
        int[] test_arr = {0,1,3,2,6,4,7,8,12,16,19,11};
        int[] res = selectSort03.selectSort(test_arr);
        System.out.println(Arrays.toString(res));
    }
}