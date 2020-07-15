package 数据结构与算法.算法.排序;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 九大经典排序算法之（9）堆排序
 * 方法：（1）
 *      （2）
 */

public class 堆排序 {
    //代码实现
    public static void heapSort(int arr[]) {
        int temp = 0;
        System.out.println("堆排序!!");

//		//1.先分步完成，寻找规律
//		adjustHeap(arr, 1, arr.length);
//		System.out.println("第一次" + Arrays.toString(arr)); // 4, 9, 8, 5, 6
//
//		adjustHeap(arr, 0, arr.length);
//		System.out.println("第2次" + Arrays.toString(arr)); // 9,6,8,5,4

        //2.再完成最终代码
        //2.1将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆,这里选择大顶堆（即堆顶元素的值最大）
        for(int i = arr.length / 2 -1; i >=0; i--) {//从第一个非叶子节点（其在数组中的下标即为：arr.length / 2 -1）开始交换
            adjustHeap(arr, i, arr.length);
        }

		//2.2将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
        for(int j = arr.length-1;j >0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            //2.3重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，
            // 反复执行“调整+交换”步骤，直到整个序列有序。
            adjustHeap(arr, 0, j);
        }

        //System.out.println("数组=" + Arrays.toString(arr));

    }

    //adjustHeap（）方法的具体实现
    //功能：将一个数组(二叉树), 调整成一个大顶堆
    /**
     * 功能： 完成 将 以 i 对应的非叶子结点的树调整成大顶堆
     * 举例  int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => 得到 {4, 9, 8, 5, 6}
     * 如果我们再次调用  adjustHeap 传入的是 i = 0 => 得到 {4, 9, 8, 5, 6} => {9,6,8,5, 4}
     * @param arr 待调整的数组
     * @param i 表示非叶子结点在数组中索引
     * @param length 表示对多少个元素继续调整， length 是在逐渐的减少
     */
    public  static void adjustHeap(int arr[], int i, int length) {

        int temp = arr[i];//先取出当前元素的值，保存在临时变量中
        //开始调整
        //说明
        //1. k = i * 2 + 1 k 是 i结点的左子结点
        for(int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if(k+1 < length && arr[k] < arr[k+1]) { //说明左子结点的值小于右子结点的值
                k++; // k 指向右子结点
            }
            if(arr[k] > temp) { //如果子结点大于父结点
                arr[i] = arr[k]; //把较大的值赋给当前结点
                i = k; //!!! i 指向 k,继续循环比较
            } else {
                break;//!
            }
        }
        //当for 循环结束后，我们已经将以i 为父结点的树的最大值，放在了最顶(局部)
        arr[i] = temp;//将temp值放到调整后的位置
    }
    //2.测试
    public static void main(String[] args) {
        //要求将数组进行升序排序
        //int arr[] = {4, 6, 8, 5, 9};
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

        heapSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
        //System.out.println("排序后=" + Arrays.toString(arr));
    }
}


