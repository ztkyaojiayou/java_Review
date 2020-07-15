package 数据结构与算法.算法.搜索;

import java.util.ArrayList;
import java.util.List;

/**
 * 四大经典查找算法之（2）二分查找递归版（还有一个非递归版，重点，掌握）
 * 注意：使用二分查找的前提是 该数组是有序的
 * 运行时间为：O(log2 N)
 * 方法：1. 首先确定该数组的中间的下标 mid = (left + right) / 2
 * 2. 然后让需要查找的数 findVal 和 arr[mid] 比较
 * 2.1 findVal > arr[mid] , 说明你要查找的数在mid 的右边, 因此需要递归的向右查找
 * 2.2 findVal < arr[mid],  说明你要查找的数在mid 的左边, 因此需要递归的向左查找
 * 2.3 findVal == arr[mid]  说明找到，就返回
 * 什么时候我们需要结束递归.
 * 1) 找到就结束递归
 * 2) 递归完整个数组，仍然没有找到findVal
 *
 */
public class 二分查找_递归版 {
    // 1.代码实现
    /**
     * @param arr 待查数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，如果没有找到，就返回 -1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {

        // 1.1当 left > right 时，说明递归了整个数组也没有找到
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;//中间值的索引
        int midVal = arr[mid];//即代表中间值

        if (findVal > midVal) { // 向右递归查找
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 向左递归查找
            return binarySearch(arr, left, mid - 1, findVal);
        } else {

            return mid;
        }

    }

    //2.完成一个课后思考题:
    /*
     * 课后思考题： {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，
     * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000
     *
     * 思路分析
     * 1. 在找到mid 索引值，不要马上返回
     * 2. 再向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 3. 再向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 4. 最后将Arraylist返回
     */

    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {

        System.out.println("hello~");
        // 当 left > right 时，说明递归整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) { // 向 右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
//			 * 思路分析
//			 * 1. 在找到mid 索引值，不要马上返回
//			 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 4. 将Arraylist返回

            //1. 此时已经找到了与目标数相等的值所对应的索引值mid，但先不马上返回，因为要找到与之相等的所有值
            List<Integer> resIndexlist = new ArrayList<Integer>();
            //2.先向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            int temp = mid - 1;
            while(true) {
                if (temp < 0 || arr[temp] != findVal) {//若temp<0或arr[temp] != findVal，则退出
                    break;
                }
                //否则，就将temp 放入到 resIndexlist
                resIndexlist.add(temp);
                temp -= 1; //temp左移
            }
            resIndexlist.add(mid);  //

            //3.再向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            temp = mid + 1;
            while(true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {//同理，退出
                    break;
                }
                //否则，就temp 放入到 resIndexlist
                resIndexlist.add(temp);
                temp += 1; //temp右移
            }

            return resIndexlist;
        }

    }

    //3.测试
    public static void main(String[] args) {
        //int arr[] = { 1, 8, 10, 89,1000,1000, 1234 };
        int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 , 11, 12, 13,14,15,16,17,18,19,20 };


        //
//		int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
//		System.out.println("resIndex=" + resIndex);

        List<Integer> resIndexList = binarySearch2(arr, 0, arr.length - 1, 1);
        System.out.println("resIndexList=" + resIndexList);
    }

}

