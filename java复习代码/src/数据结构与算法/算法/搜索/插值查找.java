package 数据结构与算法.算法.搜索;

/**
 * 四大经典查找算法之（3）插值查找（类似二分查找，只是mid不同，它是自适应的，其他相同）
 * 此时的mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left])
 * 而不是之前的mid = (left + right) / 2
 */

public class 插值查找 {
    //1.插值查找算法代码
    //说明：插值查找算法，也要求数组是有序的
    /**
     * @param arr 数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 查找值
     * @return 如果找到，就返回对应的下标，如果没有找到，返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        System.out.println("插值查找次数~~");
        //注意：findVal < arr[0]  和  findVal > arr[arr.length - 1] （区别之一）必须判断，否则这个mid可能越界，但在二分查找中则不需要
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        // 1.先求出自适应的mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);//（区别之二）不再是(left + right) / 2
        int midVal = arr[mid];//代表数组的中间值
        if (findVal > midVal) { // 说明应该向右边递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 说明向左递归查找
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }

    }

    //2.回顾/对比二分查找算法
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        System.out.println("二分查找被调用~");
        // 当 left > right 时，说明递归整个数组，但是没有找到
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) { // 向 右递归
            return binarySearch(arr, mid + 1, right, findVal);//注意：递归时还是使用的原数组，只是left和right已经改变
        } else if (findVal < midVal) { // 向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {

            return mid;
        }

    }

    //3.测试
    public static void main(String[] args) {

//		int [] arr = new int[100];
//		for(int i = 0; i < 100; i++) {
//			arr[i] = i + 1;
//		}

        int arr[] = { 1, 8, 10, 89,1000,1000, 1234 };

        int index = insertValueSearch(arr, 0, arr.length - 1, 1234);
        //int index = binarySearch(arr, 0, arr.length, 1);
        System.out.println("index = " + index);

        //System.out.println(Arrays.toString(arr));
    }


}

