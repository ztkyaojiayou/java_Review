package 数据结构与算法.算法.排序;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 九大经典排序算法之（5）快速排序（即快排）
 * 基本思路：先任选一个元素作为划分值（但一般都选中间值），通过一次遍历和比较将待排序的数组分割成独立的两部分，
 * 其中一部分的所有数据都比另外一部分的所有数据小
 * 然后再按照此方法对这两部分的数据继续进行快排分割（即递归操作），最终变成有序序列。
 * 方法：（1）先任选一个划分值，再通过与最右边的值互换，使此划分值位于最右边
 *      （2）再定义一个初始长度为0的“小于等于”区间（数组），从左至右开始遍历原数组并与划分值进行比较
 *           若大于划分值，则直接跳过。
 *           若小于等于划分值，则把该值与最左边的（易知，都大于划分值）值（也就是区间外的第一个值）互换
 *           再把其放入到区间中。
 *      （3）依此类推，直到遍历到最后一个值（也就是划分值，也要互换），
 *          此时易知，划分值左边的元素都小于划分值，右边的则都大于它
 *      （4）再对两边的数据分别执行相同操作，直至排序成功。
 */

public class 快速排序 {
    //1.核心代码
    public static void quickSort(int[] arr,int left, int right) {
        //int left = left; //左下标
        //int right = right; //右下标
        //pivot 划分值（选了中点）
        //int pivot = arr[(left + right) / 2];
        int pivot = arr[(arr.length)/2];
        int temp = 0; //临时变量，作为交换时使用
        //while循环的目的是让比pivot 值小放到左边
        //比pivot 值大放到右边
        while( left < right) {
            //在pivot的左边一直找,直到找到大于等于pivot值,才退出
            while( arr[left] < pivot) {
                left ++;
            }
            //在pivot的右边一直找,找到小于等于pivot值,才退出
            while(arr[right] > pivot) {
                right --;
            }
            //如果left >= right,退出整个while循环（即line32的循环）
            if( left >= right) {
                break;
            }

            //当退出上述两个while循环时，易知有此时的 arr[l] > pivot， arr[r] < pivot，因此需要将其进行交换
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            //如果交换完后，若发现这个arr[l] == pivot值 相等 r--， 前移
            if(arr[left] == pivot) {
                right -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值 相等 l++， 后移
            if(arr[right] == pivot) {
                left += 1;
            }
        }
        // 如果 left == right, 必须left++, right--, 否则会出现栈溢出
        if (left == right) {
            left ++;
            right --;
        }
        //再向左右递归即可
        //向左递归
        if(left < right) {
            quickSort(arr, left, right);
        }
        //向右递归
        if(right > left ) {
            quickSort(arr, left, right);
        }

    }

    //2.测试
    public static void main(String[] args) {
        //int[] arr = {-9,78,0,23,-567,70, -1,900, 4561};

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

        quickSort(arr, 0, arr.length-1);

        System.out.println("排序后");
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是=" + date2Str);
        //System.out.println("arr=" + Arrays.toString(arr));
    }


}
