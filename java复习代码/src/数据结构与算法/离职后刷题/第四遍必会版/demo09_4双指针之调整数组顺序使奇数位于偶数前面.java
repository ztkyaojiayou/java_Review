package 数据结构与算法.离职后刷题.第四遍必会版;

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
        int left = 0;
        int right = nums.length - 1;
        //开始循环
        while (left < right) {
            //1.若左边已经是奇数，右边已经是偶数了，则不动它，直接跳过
            while (left < right && nums[left] % 2 == 1) {
                left++;
            }
            while (left < right && nums[right] % 2 == 0) {
                right--;
            }
            //2.而若左边是偶数，右边是奇数时，则交换二者
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
        }
        return nums;
    }

    //自写一遍
    public int[] exchange02(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            //左边本来就是奇数时，不动
            while (left < right && nums[left] % 2 == 1) {
                left++;
            }
            //右边本来就是偶数时，不动
            while (left < right && nums[right] % 2 == 0) {
                right--;
            }
            //否则，交换
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
        }
return nums;
    }
}
