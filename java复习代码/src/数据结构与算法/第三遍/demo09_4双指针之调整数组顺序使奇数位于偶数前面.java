package 数据结构与算法.第三遍;

/**
 * 初始化： i , j 双指针，分别指向数组 nums 左右两端；
 * 循环交换： 当 i = j时跳出；
 * 指针 i 遇到奇数则执行 i = i + 1跳过，直到找到偶数；
 * 指针 j 遇到偶数则执行 j = j - 1跳过，直到找到奇数；
 * 交换 nums[i]和 nums[j]值；
 * 返回值： 返回已修改的 nums数组。
 */
public class demo09_4双指针之调整数组顺序使奇数位于偶数前面 {
    public int[] exchange(int[] nums) {
        //使用双指针即可，很简单
        int i = 0;
        int j = nums.length - 1;
        //开始循环
        while (i < j) {
            //1.若左边已经是奇数，右边已经是偶数了，则不动它，直接跳过
            while (i < j && nums[i] % 2 == 1) {
                i++;
            }
            while (i < j && nums[j] % 2 == 0) {
                j--;
            }
            //2.而若左边是偶数，右边是奇数时，则交换二者
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }
        return nums;
    }
}
