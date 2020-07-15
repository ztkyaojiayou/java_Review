package 重写排序算法;


import java.util.Arrays;

/**
 * 6、快速排序（Quick Sort）
 * 快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，
 * 其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 *
 * 6.1 排序思路
 * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
 * 从数列中挑出一个元素，称为 “基准”（pivot）；
 * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
 * 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 *
 * 6.2 快排思路详解
 * 我们从一个数组来逐步逐步说明快速排序的方法和思路。
 * 假设我们对数组{7, 1, 3, 5, 13, 9, 3, 6, 11}进行快速排序。
 * 首先在这个序列中找一个数作为基准数，为了方便可以取第一个数。
 * 遍历数组，将小于基准数的放置于基准数左边，大于基准数的放置于基准数右边。
 * 此时得到类似于这种排序的数组{3, 1, 3, 5, 6, 7, 9, 13, 11}。
 * 在初始状态下7是第一个位置，现在需要把7挪到中间的某个位置k，也即k位置是两边数的分界点。
 * 那如何做到把小于和大于基准数7的值分别放置于两边呢，我们采用双指针法，从数组的两端分别进行比对。
 * 先从最右位置往左开始找直到找到一个小于基准数的值，记录下该值的位置（记作 i）。
 * 再从最左位置往右找直到找到一个大于基准数的值，记录下该值的位置（记作 j）。
 * 如果位置i<j，则交换i和j两个位置上的值，然后继续从(j-1)的位置往前和(i+1)的位置往后重复上面比对基准数然后交换的步骤。
 * 如果执行到i==j，表示本次比对已经结束，将最后i的位置的值与基准数做交换，
 * 此时基准数就找到了临界点的位置k，位置k两边的数组都比当前位置k上的基准值或都更小或都更大。
 * 上一次的基准值7已经把数组分为了两半，基准值7算是已归位（找到排序后的位置）。
 * 通过相同的排序思想，分别对7两边的数组进行快速排序，左边对[left, k-1]子数组排序，右边则是[k+1, right]子数组排序。
 * 利用递归算法，对分治后的子数组进行排序。
 * 快速排序之所以比较快，是因为相比冒泡排序，每次的交换都是跳跃式的，每次设置一个基准值，
 * 将小于基准值的都交换到左边，大于基准值的都交换到右边，这样不会像冒泡一样每次都只交换相邻的两个数，
 * 因此比较和交换的此数都变少了，速度自然更高。当然，也有可能出现最坏的情况，就是仍可能相邻的两个数进行交换。
 *
 * 6.3 算法分析
 * 最佳情况：T(n) = O(nlogn)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(nlogn)　
 * 快速排序基于分治思想，它的时间平均复杂度很容易计算得到为O(NlogN)。
 */
public class 快速排序 {
    /**
     * 写法1：（推荐）
     * 注意：中轴元素就是指：其左边的元素都比它小，右边元素都比它大的元素。
     * 参考链接：https://www.bilibili.com/video/BV1at411T75o?from=search&seid=5926915630491807968
     * https://www.cnblogs.com/captainad/p/10999697.html
     */
    public  int[] QuickSort01(int[] arr, int left, int right) {
        if (left < right) {
            //1.获取中轴元素所处的位置
            int mid = partition(arr, left, right);
            //2.再对中轴元素左右两边的元素进行递归
            arr = QuickSort01(arr, left, mid - 1);
            arr = QuickSort01(arr, mid + 1, right);
        }
        //3.最后，返回排好序的数组返回即可
        return arr;
    }

    //1。找中轴元素所在的位置
    private int partition(int[] arr, int left, int right) {//一开始传入的值为：left = 0，right = arr.length -1
        //1.1选取中轴元素（可任意选，这里选第一个元素）
        int pivot = arr[left];
        //1.2再用i和j来代替左右指针，因为left在后面要用，避免引起混淆
        int i = left;
        int j = right;
        //1.3开始双指针遍历，一头一尾
        while (i < j) {//或while(true)，亦或while(i!=j)都可以
            //1.3.1向右找到第一个大于等于 pivot 的元素位置
            while (i <= j && arr[i] <= pivot) i++;
            //1.3.2向左找到第一个小于等于 pivot 的元素位置
            while(i <= j && arr[j] >= pivot ) j--;
            if(i >= j)//1.3.3此时说明已经全部遍历完毕，退出整个循环（关键）
                break;
            // 1.3.4当退出上面两个while循环时，说明此时的 arr[left] > pivot， arr[right] < pivot，
            // 因此需要交换这两个元素的位置，使得左边的元素不大于pivot,右边的不小于pivot
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        //1.3.5此时已经退出循环，即此时left和right已经重合，该位置的左边的值全比pivot小，右边的则全比它大，
        //于是把中轴元素和该处的元素互换，这样，中轴元素就处在其中了
        //（注意：因为一开始我们只是用中轴元素进行比较，把剩下的元素进行归类，但动的并不是pivot元素本身）
        arr[left] = arr[j];
        arr[j] = pivot;//写arr[left] = pivot;也是一样的，因为此时二者已经重合
        //1.3.6再把该中轴位置返回，用于下一次递归使用。
        return j;
    }

}

class QuickSort02 {
    /**
     * 写法2：选择第一个基准值base，小于base的数放在左边，大于base的数放在右边。
     * 递归的将base左边和右边的数都按照第一步进行，直到不能递归。
     * @param arr
     * @param left
     * @param right
     */
    public void QuickSort02(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = arr[left]; // 选定的基准值（第一个数值作为基准值）
            int temp; // 记录临时中间值
            do {
                //通过两个while循环，当左边的元素大于右边的时，就替换
                while ((arr[left] < pivot) && (left < right))
                    left++;
                while ((arr[right] > pivot) && (right > left))
                    right--;
                if (left <= right) {//替换
                    temp = arr[left];
                    arr[left] = arr[right];
                    arr[right] = temp;
                    //替换完之后，继续遍历并判断
                    left++;
                    right--;
                }
            } while (left <= right);
            if (left < right)//j是从右边向左边递减的，此时若还大于start，则递归(start,j)这部分即可
                QuickSort02(arr, left, right);
            if (right > left)//j是从左边向右边递增的，此时若还小于start，则递归(i,end)这部分即可
                QuickSort02(arr, left, right);
        }
    }
}

//测试类
class test{
    public static void main(String[] args) {
        //待排序的数组
        int[] arr = {1,7,9,6,8,4,12,1,5,32};
        //写法1的测试：
        快速排序 test1 = new 快速排序();
        int[] res1 = test1.QuickSort01(arr,0,arr.length -1);
        System.out.println(Arrays.toString(res1)); //测试结果为：[1, 1, 4, 5, 6, 7, 8, 9, 12,32]

        //写法2的测试：
        QuickSort02 test2 = new QuickSort02();
        test2.QuickSort02(arr,0,arr.length -1);
    }

}
