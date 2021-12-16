package 数据结构与算法.剑指offer第一版.数组;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * （懂了）题目：在一个长度为n的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。要求：时间复杂度：O（N），空间复杂度：O（1）
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，
 * 那么对应的输出是重复的数字 2 或者 3。
 *
 * 【法1，排序再判断，推荐】：一言不合先排序，再判断相邻位置是否存在相同数字，如果存在，对 duplication 赋值返回
 *        否则继续比较，直到找到相同的数字，return
 *      时间复杂度：O（N*logN）+ O（1），满足要求

 * 【法2，归位再判断，推荐】：对于这种数组元素在[0,n-1]范围的问题，可以将值为i的元素调整到第i个位置上进行求解，即归位
 *             比如：以 (2, 3, 1, 0, 2, 5) 为例，遍历到位置 4 时，该位置上的数为 2，
 *             但是第 2 个位置上已经有一个 2 的值了，因此可以知道 2 重复。
 *
 *       时间复杂度：O（N），数组中的每个元素只需遍历或交换一遍，即可找到那个重复的数字
 *       空间复杂度：O（1）
 *       满足要求
 *
 *
 * 归位：
 * 遍历数组，判断当前位的值和下标是否相等：
 * 2.1. 若相等，则已经归位，遍历下一位；
 * 2.2. 若不等，则要归位，将当前位置i上的元素和a[i]位置上的元素比较：
 * 若它们相等，则成功！
 * 若不等，则将它们两交换。换完之后a[i]位置上的值和它的下标是对应的，但i位置上的元素和下标并不一定对应；
 * 重复2.2的操作，直到当前位置i的值也为i，将i向后移一位，再重复2.
 *
 *
 *将每次遇到的数进行"归位"，当某个数发现自己的"位置"被相同的数占了，则出现重复。
 * 1.扫描这个数组，当扫描到下标为 i 的数字时，首先比较其对应的值（nums[i],假设为m）是否等于 其下标i:
 *   1.1若是，则说明已经归位，接着扫描下一个数字；
 *   1.2若不是，则拿 m 与第 m 个数比较，把m这个数归位；也即每扫描到一个值，就想立马把这个值归位（易知，也有可能与第 m 个数刚好相等，则说明出现重复了，完美收官）。
 *       1.2.1若 m 与第 m 个数相等，则说明出现重复了，完美；
 *       1.2.2若 m 与第 m 个数不相等，则将 m 与第 m 个数交换，将 m "归位"。
 * 2.再重复比较和交换这个两个过程，直到当前位置i的值也为i，将i向后移一位，直到发现重复的数为止
 * 举个栗子：
 * 以数组 {2,3,1,0,2,5,3} 为例
 * 当 i = 0 时，nums[i] = 2 != i，判断 nums[i] 不等于 nums[nums[i]]，交换 nums[i] 和 nums[nums[i]]，交换后数组为：{1,3,2,0,2,5,3}
 * 此时 i = 0，nums[i] = 1 != i，判断 nums[i] 不等于 nums[nums[i]]，交换 nums[i] 和 nums[nums[i]]，交换后数组为：{3,1,2,0,2,5,3}
 * 此时 i = 0，nums[i] = 3 != i，判断 nums[i] 不等于 nums[nums[i]]，交换 nums[i] 和 nums[nums[i]]，交换后数组为：{0,1,2,3,2,5,3}
 * 此时 i = 0，nums[i] = 0 = i，继续下一组
 * 当 i = 1，nums[i] = 1 = i，继续下一组
 * 当 i = 2，nums[i] = 2 = i，继续下一组
 * 当 i = 3，nums[i] = 3 = i，继续下一组
 * 当 i = 4，nums[i] = 2 != i，判断 nums[i] 等于 nums[nums[i]]，出现重复，赋值返回
 *
 * 【法3】：用HashMap，遍历一下数组即可
 *      时间复杂度：O（N）+ N*O（1）
 *      空间复杂度：O（N）
 */

public class array02找出数组中的任一重复数字 {
//方法一：先排序，再比较相邻元素是否相等即可（简单）
public int findRepeatNumber(int[] nums) {
    Arrays.sort(nums);
    for (int i = 1; i < nums.length; i++) {
        if (nums[i] == nums[i - 1])
            return nums[i];
    }
    return -1;
}

//方法2：使用set
// 最简单的方式就是把数组中的元素一个个加入到集合set中，加入的时候如果有重复的，则直接返回
    public int findRepeatNumber02(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num))
                return num;
        }
        return -1;
    }


    //方法三：使用一个临时数组，类似于map计数
    public int findRepeatNumber03(int[] nums) {
        int length = nums.length;
        int[] temp = new int[length];
        for (int i = 0; i < length; i++) {
            temp[nums[i]]++;//即把该元素作为在map中对应的位置，让其加一，即对该元素计数。
            if (temp[nums[i]] > 1)
                return nums[i];
        }
        return -1;
    }


    //方法四：“归位”法：（搞清楚原理之后也简单）
    public int findRepeatNumber04(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            //位置正确，先不用管
            if (i == nums[i])
                continue;
            //出现了重复，直接返回
            if (nums[i] == nums[nums[i]]) {
                return nums[i];
            }
            //交换
            int temp = nums[nums[i]];
            nums[nums[i]] = nums[i];
            nums[i] = temp;
            //这里的i--是为了抵消掉上面的i++，
            //交换之后需要原地再比较
            i--;
        }
        return -1;
    }

}
