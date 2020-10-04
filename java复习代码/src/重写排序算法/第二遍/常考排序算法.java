package 重写排序算法.第二遍;

import java.util.Arrays;

//07.26日
public class 常考排序算法 {
    /**
     * 1.冒泡排序：适用于数据量量不大，对稳定性有要求，且数据基本有序的情况
     *  * 方法：（1）从首位开始，依次比较相邻元素的值，若发现逆序则交换（较大值放后面），
     *  *           直至最后，最大值就到了末尾（这称为一次冒泡），不再参与接下来的排序
     *  *      （2）再从剩下的数组中继续不断地进行相同操作（比较相邻元素并交换），直至排序结束。
     */

public int[] bubbleSort(int[] arr){
    //特判
    if (arr == null || arr.length == 0){
        return arr;
    }
    for (int i = 0;i < arr.length;i++){//表示要比较多少次，即（长度-1）次
        for (int j = 0;j<arr.length-1-i;j++){//每比较完一次，那么就要少比较一次，
            // 因此这里要减去i，因为j就表示要比较的次数。
    if (arr[j+1] < arr[j]){//真正比较是用j来比较，若后一个数比前一个数还小，则交换
    int temp = arr[j+1];
    arr[j+1] = arr[i];
    arr[j] = temp;
}
        }
    }
    //最终返回比较完毕的数组
    return arr;
}

//优化版1：使用一个标志位来减少比较趟数
public int[] bubbleSort02(int[] arr){
    //特判
    if (arr == null || arr.length == 0){
        return arr;
    }
    boolean flag = false;//本趟排序是否发生交换的标识
    for (int i = 0;i < arr.length;i++){//表示要比较多少次，即（长度-1）次
        for (int j = 0;j<arr.length-1-i;j++){//每比较完一次，那么就要少比较一次，
            // 因此这里要减去i，因为j就表示要比较的次数。
            if (arr[j+1] < arr[j]){//真正比较是用j来比较，若后一个数比前一个数还小，则交换
                int temp = arr[j+1];
                arr[j+1] = arr[i];
                arr[j] = temp;
                flag = true;//若交换了，则将该标识设为true
            }

        }

        if (flag == false){//若这一趟比较下下来都没有进行交换，就可以直接跳出该循环，减少了不必要的比较次数
            break;
        }
    }
    //最终返回比较完毕的数组
    return arr;
}

//优化2：有序部分不用再比较
public int[] bubbleSort03(int[] arr){
    //特判
    if (arr == null || arr.length == 0){
        return arr;
    }
    int lastChangeIndex =0;//最后交换的位置
    int sortBorder = arr.length-1;//无序数列的边界，每次比较只需要比较到这里即可
    boolean flag = false;//本趟排序是否发生交换的标识
    for (int i = 0;i < arr.length;i++){//表示要比较多少次，即（长度-1）次
        for (int j = 0;j<sortBorder;j++){//每比较完一次，那么就要少比较一次，
            // 因此这里要减去i，因为j就表示要比较的次数。
            if (arr[j+1] < arr[j]){//真正比较是用j来比较，若后一个数比前一个数还小，则交换
                int temp = arr[j+1];
                arr[j+1] = arr[i];
                arr[j] = temp;
                flag = true;//若交换了，则将该标识设为true
                lastChangeIndex = j;
            }
        }
        sortBorder = lastChangeIndex;//更新最后一次交换的位置
        if (flag == false){//若这一趟比较下下来都没有进行交换，就可以直接跳出该循环，减少了不必要的比较次数
            break;
        }
    }
    //最终返回比较完毕的数组
    return arr;
}

/**
 *  2.选择排序：适用于当数据量不大，且对稳定性没有要求的时候
 */
    /**
     *  * 方法：（1）在被排序数组中选出最小值，与原首位元素互换，
     *  *           易知此元素即为本数组中的最小元素，不再参与接下来的排序
     *  *      （2）再从剩下数组中继续不断地进行相同操作（选最小值并交换），直至排序结束
     * @param arr
     * @return
     */
    public int[] selectSort(int[] arr){
    //特判
    if (arr == null || arr.length == 0){
        return arr;
    }
    for (int i=0;i< arr.length;i++){
        int min_index = i;//先假定最小元素为每一趟选择排序的第一个元素，
        // 这里为i是因为：每一趟选择排序后，第一个元素是已经排好了的，即不需要再参与排序了
        //这一趟循环是为了找出除了第一个元素外的最小值，这里是记录该最小值的下标.
        for (int j = i+1;j< arr.length;j++){//j是要遍历到尾部的，所以这里为arr.length,而不是arr
            // .length-1，另外，这里的j是可以从i开始的，相当于与第一个元素也比较一下，这完全没啥~
            if (arr[j] < arr[min_index]){
                min_index = j;
            }
        }
        //此时min_index就真是这一趟中的最小值的下标了，
        //此时只需将该最小值与第一个元素交换即可，使第一个元素成为最小值
        int temp = arr[min_index];
        arr[min_index]= arr[i];
        arr[i]= temp;
    }
    //最后，返回排序后的数组即可
    return arr;
}


/**
 *  3.快速排序（常考）
 *  基本思路：先任选一个元素作为划分值（但一般都选第一个值），通过一次遍历和比较将待排序的数组分割成独立的两部分，
 *  其中一部分的所有数据都比另外一部分的所有数据小
 *  然后再按照此方法对这两部分的数据继续进行快排分割（即递归操作），最终变成有序序列。
 *  方法：（1）先任选一个划分值，再通过与最右边的值互换，使此划分值位于最右边
 *       （2）再定义一个初始长度为0的“小于等于”区间（数组），从左至右开始遍历原数组并与划分值进行比较
 *             若大于划分值，则直接跳过。
 *             若小于等于划分值，则把该值与最左边的（易知，都大于划分值）值（也就是区间外的第一个值）互换
 *             再把其放入到区间中。
 *        （3）依此类推，直到遍历到最后一个值（也就是划分值，也要互换），
 *            此时易知，划分值左边的元素都小于划分值，右边的则都
 */

public int[] QuickSort(int[] arr,int left,int right){
    if (left < right){
        int mid = partition(arr,left,right);
        QuickSort(arr,left,mid -1);
        QuickSort(arr,mid+1,right);
    }
    return arr;
}
//找到中轴元素的位置（先任意选一个中轴元素，再根据该元素将数组元素
    private int partition(int[] arr, int left, int right) {
        /**
         * 注意：我们这里只是选了中轴元素是谁，只是将其作为标准，
         * 把数组中剩下的元素中小于它的元素放左边，大于它的元素放右边而已，
         * 但并没有指定中轴元素的位置，我们在放置完数组的其他元素后，
         * 要将该元素出现放到数组中，放在哪儿呢？
         * 答：放在两个指针重合的位置即可，其实就是中间位置嘛~
         */
    int pivot = arr[left];//中轴值随意选，一般就选最左边那个元素，将数组元素中小于该值的元素放左边，大于该值的元素放右边
        //最后，将中轴元素也插入其中，即双指针重合的位置（是要与中轴元素所选的元素的位置互换，而不是强插进来）
    int i = left;
    int j = right;
    while (i<j){
        //开始按照中轴值，将小于该值的所有元素放左边，大于该值的所有元素放右边
        while (i<=j && arr[i]<= pivot){//此时说明位置i处的值本来就比中轴值小，因此直接跳过该值，i++，进行下一个值的比较
            i++;
        }
        while (i<=j && arr[j]>= pivot){//此时说明位置j处的值本来就比中轴值大，因此直接跳过该值，j--，进行下一个值的比较
            j--;
        }
        //此时表示遍历结束，退出程序
        if (i>=j){
            break;
        }
        //此时说明i和j对应的元素应该对调
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
       //再把该中轴元素与第一个元素交换，于是中轴值就插进来了,最后再返回其下标即可
        arr[left] = arr[j];//先把该重合处的元素给第一个元素，将位置腾出来（即中轴元素所指定的值），
        arr[j]= pivot;//再将该位置给中轴元素即可
        return j;//最后返回该元素的位置
    }

    /**
     *  4.插入排序：适用于数据量不大，对算法的稳定性有要求，且数据局部或者整体基本有序的情况
     *  方法：（1）先把待排序数组看成一个有序表和无序表，其中，有序表此时只包含一个元素（且通常就选第一个元素），剩下的元素构成无序表
     *        （2）每次把无序表中的第一个元素与有序表中的元素依次进行比较，把其放在合适的位置（易知，有序表已扩充），此元素不再参与接下来的排序
     *        （3）再从剩下的无序表中继续不断地进行相同操作（选第一个元素放到有序表的合适位置），直至排序结束，有序表即为排序结果
     */
public int[] insertSort(int[] arr){
    //特判
    if (arr == null || arr.length == 0){
        return arr;
    }
    for (int i =0;i<arr.length-1;i++){//每比较一趟，有序表中的元素就会加一
        int cur = arr[i+1];
        int pre_index = i;//即有序表中的最后一个元素的下标，因为每比较一趟，有序表中的元素就会加一，因此这里就直接是i
        while (pre_index >= 0 && cur < arr[pre_index]){
            arr[pre_index+1] = arr[pre_index];
            pre_index--;//因为可能会小于多个值，因此要不断地比较，直至退出该循环
        }
        pre_index++;//因为此时cur已经大于了arr[pre_index]，
        // 因此要把cur放在pre_index的后一个位置，也即索引值要先加1
        arr[pre_index] = cur;//最后，把该值插入进去即可.

    }
    return arr;
}

    /**
     *  5.归并排序：适用于数据量较大时，要注意考虑内存空间的开销
     *   * 基本思路：先把待排数组拆分成若干个只包含一个元素的子数组，
     *  * 再对每相邻的两个子数组进行排序再合并，直到排序结束
     *  * 这里的排序方法很重要。
     */
    public int[] MergeSort(int[] arr){
        //0.递归结束的条件
        if (arr.length < 2){
            return arr;
        }

        int mid = arr.length/2;//中间值
        //1.先把原数组分成左右两边
        int[] left = Arrays.copyOfRange(arr,0,mid);//左边数组
        int[] right = Arrays.copyOfRange(arr,mid,arr.length);//右边数组
        //2.再对左右两边的数组进行排序(使用递归）
        int[] left_sorted = MergeSort(left);
        int[] right_sorted = MergeSort(right);
        //3.最后再对排好序的数组进行归并即可
        return merge(left_sorted,right_sorted);
    }

    //3.0归并的具体实现
    private int[] merge(int[] sorted_arr1, int[] sorted_arr2) {
        //先定义一个额外的数组,长度为传入的两个已经排好序了的要进行归并的数组的长度和，用于存储结果
    int[] res_arr = new int[sorted_arr1.length + sorted_arr2.length];
        //定义两个指针遍历，用于遍历传入的两个数组
        int i= 0;//用于遍历sorted_arr1
        int j= 0;//用于遍历sorted_arr2
    for (int index = 0;index<res_arr.length;index++){
        //3.1先处理特殊情况，即若某一边还有剩余数据而另一边已经全部添加进res_arr数组时，
        //则把它依次全部存入res_arr数组中
        if (i >= sorted_arr1.length){//3.1.1此时即左边走完了，右边还剩了数据(务必加上等于号）
            res_arr[index] = sorted_arr2[j];
            j++;
        }else if (j >= sorted_arr2.length){//3.1.2此时即右边走完了，左边还剩了数据(务必加上等于号）
            res_arr[index] = sorted_arr1[i];
            i++;
            //3.2再处理一般情况，即当两边还都有数据时，则先把较小值先存入。
        }else if (sorted_arr1[i] > sorted_arr2[j]){ //3.2.1若左边的当前数据大于右边的当前数据时，则先把右边的数据先存入
            res_arr[index] = sorted_arr2[j];
            j++;
        }else {//3.2.2若左边的当前数据小于右边的当前数据时，则先把左边的数据先存入
            res_arr[index] = sorted_arr1[i];
            i++;
        }
    }//3.3最后返回排好序的结果数组
    return res_arr;
    }
}
