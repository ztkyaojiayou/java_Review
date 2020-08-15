package 重写排序算法.第一遍;


import java.util.Arrays;

/**
 * 4、希尔排序（Shell Sort）
 * 希尔排序是希尔（Donald Shell）于1959年提出的一种排序算法。
 * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，
 * 也称为缩小增量排序，同时该算法是冲破O(n^2）的第一批算法之一。
 * 它与插入排序的不同之处在于，它会优先比较距离较远的元素。
 * 希尔排序又叫缩小增量排序。
 *
 * 希尔排序是把记录按下表的一定增量分组，对每组使用直接插入排序算法排序；
 * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
 *
 * 4.1 算法描述
 * 我们来看下希尔排序的基本步骤，在此我们选择增量gap=length/2，缩小增量继续以gap = gap/2的方式，
 * 这种增量选择我们可以用一个序列来表示，{n/2,(n/2)/2...1}，称为增量序列。
 * 希尔排序的增量序列的选择与证明是个数学难题，我们选择的这个增量序列是比较常用的，也是希尔建议的增量，
 * 称为希尔增量，但其实这个增量序列不是最优的。此处我们做示例使用希尔增量。
 *
 * 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，具体算法描述：
 * 1）选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
 * 2）按增量序列个数k，对序列进行k 趟排序；
 * 3）每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
 * 4）仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 *
 * 4.2 算法分析
 * 最佳情况：T(n) = O(nlog2 n)  最坏情况：T(n) = O(nlog2 n)  平均情况：T(n) =O(nlog2n)　
 */
public class 插入排序_希尔版 {
public int[] ShellSort(int[] arr){
    if (arr == null||arr.length == 0){
        return arr;
    }

    int len = arr.length;
    int cur,gap = len / 2;//易知，增量为多少，则会有多少组，则每一组的元素就为len/gap个。

    while (gap > 0){
    for (int i = gap;i < len;i++){//从gap开始遍历，一直到末尾。
        // 接下来就是普通的插入排序了，只不过这里的间隙不是1，而是gap了。
        cur = arr[i];//令当前值为每一组的第二个值，即用该值和该组的之前的其他值进行比较，
        // 若小于其之前的值，则把该较大值后移，接着继续往前比较
    int pre_Index = i - gap;
    while (pre_Index >= 0 && cur < arr[pre_Index]) {//若当前值小于其之前的值，
        // 则（一直）替换 arr[pre_Index + gap]和arr[pre_Index]（此时与当前值cur无关），
        // 即把该较大值后移gap位，接着继续用当前值cur往前比较，
        // 一直到cur > arr[pre_Index],此时就可以把当前值cur插入到该组的合适位置啦~
        arr[pre_Index + gap] = arr[pre_Index];
        pre_Index -= gap;//接着继续往前比较(每一个值都差gap个位置）
    }
    arr[pre_Index + gap] = cur;//当cur > arr[pre_Index],此时就可以把当前值cur插入到该组的合适位置啦~
}
gap /= 2;//接着，再计算下一次希尔排序的gap（间隙），以此类推，直到间隙 gap < 0 为止。
    }
    return arr;
}

    //测试
    public static void main(String[] args) {
        int[] arr = {1,4,2,3,7,5,9};
        插入排序_希尔版 test = new 插入排序_希尔版();
        int[] res = test.ShellSort(arr);
        //注意：若bubbleSort()方法为静态方法，则可以不用创建实例对象，直接调用即可,如下所示：
        //int[] res = bubbleSort(arr);
        System.out.println(Arrays.toString(res));
        //测试结果为：[1, 2, 3, 4, 5, 7, 9]
    }
}
