package 数据结构与算法.算法.排序;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 九大经典排序算法之（6）归并排序（采用的是分治思想，需要一个额外的空间（即一个临时数组temp））
 * 基本思路：先把待排数组拆分成若干个只包含一个元素的子数组，
 * 再对每相邻的两个子数组进行排序再合并，直到排序结束
 * 这里的排序方法很重要。
 */
public class 归并排序 {

    //1.代码实现（包含拆分（并未做实质性的工作）和合并两个部分）
    /**
     * arr：待排序数组
     * left：数组的左索引
     * right：数组的右索引
     * temp：临时数组，用于“合并”
     */

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        //1.1先拆分（简单）
        if(left < right) {
            int mid = (left + right) / 2; //中间索引，以中间索引拆分，数组越拆越小
            //向左递归进行拆分
            mergeSort(arr, left, mid, temp);
            //向右递归进行拆分
            mergeSort(arr, mid + 1, right, temp);

            //1.2再合并（关键）
            merge(arr, left, mid, right, temp);

        }
    }

    //1.2合并的具体实现（掌握）
    /**
     *
     * @param arr 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 做中转的临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {

        int i = left; //左边有序序列的初始索引
        int j = mid + 1; //右边有序序列的初始索引
        int t = 0; // 指向临时数组temp的当前索引

        //(一)
        //先把左右两边(有序)的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {//继续
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
            //则把左边的当前元素，填充到 temp数组
            //然后 t++, i++
            if(arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else { //反之,将右边有序序列的当前元素，填充到temp数组
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        //(二)
        //若某一边还有剩余数据，则把它依次全部填充到temp
        while( i <= mid) { //若左边的有序序列还有剩余的元素，则全部填充到temp中
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while( j <= right) { //若右边的有序序列还有剩余的元素，也全部填充到temp中
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }


        //(三)
        //最后再将temp临时数组(已经排好了序）的元素又全部拷贝到arr（思路简单，但代码实现较难理解）
        //注意，并不是每次都拷贝所有
        t = 0;//从临时数组的第一个元素开始拷贝
        // 第一次合并 tempLeft = 0 , right = 1
        // 第二次：tempLeft = 2  right = 3
        // 第三次：tempLeft=0 tempLeft=3
        // ......
        // 最后一次 tempLeft = 0  right = 7
        // 易知，tempLeft 应为每次递归的起点，即left
        int tempLeft = left; //

        while(tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }

    //3.测试
    public static void main(String[] args) {
        //int arr[] = { 8, 4, 5, 7, 1, 3, 6, 2 }; //

        //测试快排的执行速度
        // 创建要给80000个的随机的数组
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }
        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        int temp[] = new int[arr.length]; //归并排序需要一个额外空间
        mergeSort(arr, 0, arr.length - 1, temp);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是=" + date2Str);

        //System.out.println("归并排序后=" + Arrays.toString(arr));
    }
}
