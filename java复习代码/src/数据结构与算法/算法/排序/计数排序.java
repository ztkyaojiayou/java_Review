package 数据结构与算法.算法.排序;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 九大经典排序算法之（8）计数排序（也是来自于桶排序的思想，属于分配式排序）
 * 方法：* 首先，需要三个数组:
 *      * 待排序数组 int[] arr = new int[]{4,3,6,3,5,1};
 *      * 辅助计数数组 int[] temp = new int[max - min + 1]; //该数组大小为待排序数组中的最大值减最小值+1
 *      * 输出数组 int[] res = new int[arr.length];
 *      * 1.求出待排序数组的最大值max=6， 最小值min=1
 *      * 2.实例化辅助计数数组temp，temp数组中每个下标对应arr中的一个元素， temp用来记录每个元素出现的次数
 *      * 3.计算 arr 中每个元素在temp中的位置 position = arr[i] - min，此时 temp = [1,0,2,1,1,1]; （3出现了两次，2未出现）
 *      * 4.根据 temp 数组求得排序后的数组，此时 res = [1,3,3,4,5,6]
 */

public class 计数排序 {

    //1.代码实现
    public static int[] countSort(int[] arr){
        if (arr == null || arr.length == 0) {
            return null;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        //找出数组中的最大最小值
        for(int i = 0; i < arr.length; i++){
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        int temp[] = new int[max];//不是说好的“最大值-最小值+1”嘛？？？

        //找出每个数字出现的次数
        for(int i = 0; i < arr.length; i++){
            int position = arr[i] - min;
            temp[position]++;
        }

        int index = 0;
        for(int i = 0; i < temp.length; i++){
            while(temp[i]-- > 0){
                arr[index++] = i+min;
            }
        }

        return arr;
    }

    //2.测试
    public static void main(String[] args) {
        int arr[] = {4,3,6,3,5,1};

        // 80000000 * 11 * 4 / 1024 / 1024 / 1024 =3.3G
//		int[] arr = new int[8000000];
//		for (int i = 0; i < 8000000; i++) {
//			arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
//		}
        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        countSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);

        System.out.println("计数排序后 " + Arrays.toString(arr));

    }
}
