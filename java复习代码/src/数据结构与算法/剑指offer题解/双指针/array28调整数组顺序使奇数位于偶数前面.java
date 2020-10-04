package 数据结构与算法.剑指offer题解.双指针;


/**
 * (没太懂）题目：输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 *
 *  方法1：O(N^2)的解法：
 *    插入排序的变形：将交换的判断条件改为（arr[j-1]为偶数 && arr[j]为奇数）
 *  方法2：O(N)的解法：
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
//方法1：推荐，双指针
    public int[] exchange(int[] nums) {
        //双指针
        int i = 0, j = nums.length - 1;
        while(i < j) {
            while (i<j && nums[i] % 2 == 1){//是奇数就跳过
                i++;
            }
            while (i<j && nums[j] % 2 == 0){//是奇数就跳过
                j--;
            }
            //若奇偶在异侧，则交换
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        return nums;
    }

    //方法2：使用了额外数组，不推荐
    public  void reOrderArray(int[] nums) {
        // 先统计奇数个数
        int oddCnt = 0;
        for (int x : nums)
            if (!isEven(x)){
                oddCnt++;//统计奇数的个数
            }
        int[] copy = nums.clone();
        int i = 0, j = oddCnt;
        for (int num : copy) {
            if (num % 2 == 1){
                //奇数，则放在前面
                nums[i] = num;
                i++;
            } else{
                //偶数，放在后面
                nums[j] = num;
                j++;
            }
        }
    }

    //判断是否是偶数
    private static boolean isEven(int x) {
        return x % 2 == 0;
    }
}