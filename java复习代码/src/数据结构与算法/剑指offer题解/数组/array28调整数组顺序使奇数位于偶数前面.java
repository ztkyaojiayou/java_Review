package 数据结构与算法.剑指offer题解.数组;

import java.util.Arrays;

/**
 * (没太懂）题目：输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 *
 *  O(N^2)的解法：
 *    插入排序的变形：将交换的判断条件改为（arr[j-1]为偶数 && arr[j]为奇数）
 *  O(N)的解法：
 *    两个指针：p1 = arr[0], p2 = arr[length-1]
 *      while(p1<=p2){
 *          p1往右走，遇到偶数即停
 *          p2往左走，遇到奇数即停
 *          swap(arr, p1, p2);
 *      }
 *   【注意】
 *      为了增加程序的健壮性：
 *          可将p1、p2的判断条件 写成一个独立的函数，
 *          这样前置的就不仅可以是奇数、也可以将非负数放在前面、将所有能被3整除的数放在前面...
 *
 */

public class array28调整数组顺序使奇数位于偶数前面 {

    public static void reOrderArray(int[] nums) {
        // 奇数个数
        int oddCnt = 0;
        for (int x : nums)
            if (!isEven(x))
                oddCnt++;//统计奇数的个数
        int[] copy = nums.clone();
        int i = 0, j = oddCnt;
        for (int num : copy) {
            if (num % 2 == 1)
                nums[i++] = num;
            else
                nums[j++] = num;
        }
    }

    //判断是否是偶数
    private static boolean isEven(int x) {
        return x % 2 == 0;
    }


    public static void reOrderArray02(int[] array) {
        int len = array.length;
        if (len <= 1) {
            return;
        }
        int i = 0;
        while (i < len) {
            //如果i所指的元素是奇数，则继续前进
            if (array[i] % 2 == 1) {
                i++;
            } else {
                //当i遇到偶数停下时，j从i的后一位开始走
                int j = i + 1;
                //当j所指的元素也是偶数时，则j向后移动
                while (array[j] % 2 == 0) {
                    //当j移到队尾，则说明i到队尾全是偶数，已满足题目的奇偶分离要求
                    if (j == len - 1) {
                        return;
                    }
                    j++;
                }
                //此时j为奇数，i为偶数，用temp保存array[j]的值
                int temp = array[j];
                //把i到j-1的元素往后移一位
                while (j > i) {
                    array[j] = array[j - 1];
                    j--;
                }
                //把保存在temp中的原第j个元素的值赋给i，此时i就变成奇数了，并进入下个循环
                array[i] = temp;
            }

        }
    }
    //测试
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 5};
        reOrderArray02(arr);
        System.out.println(Arrays.toString(arr));//打印数组的方式一（行打印）
//        for(int a:arr)//打印数组的方式二（列打印）
//            System.out.println(a);
    }
}