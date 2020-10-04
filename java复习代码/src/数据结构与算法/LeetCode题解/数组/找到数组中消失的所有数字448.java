package 数据结构与算法.LeetCode题解.数组;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 448. 找到数组中所有消失的数字
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，
 * 数组中的元素一些出现了两次，另一些只出现一次。
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 *
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗?
 * 你可以假定返回的数组不算在额外空间内。
 *
 * 示例:
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * 输出:
 * [5,6]
 */

/**
 * 思路解析：注意题目要求不能使用额外空间，这也是这道题的难点所在。
 * 这道题里面其实包含了隐藏的条件，1 ≤ a[i] ≤ n，即每个数字本身都对应一个i-1的数组下标。
 * 我们可以利用数组内容本身跟数字下标的关联找出缺失的数字。
 * 扫描两遍数组，第一次将所有数字做标记，第二次根据标记信息找出缺失的数字。下面来看详细分析。
 * 假设有数组[1,2,3,4,5,6]
 * 数组上方的是数组的下标，通过这张图可以发现，数组中的每个值都有一个对应的数组下标，
 * 比如值为4的对应下标3。即arr[i]对应i-1
 * 如果数组是乱序的呢？
 * 乱序的数组并不影响，比如值为3的对应的是下标2，值为6的对应的是下标5。
 *
 * 我们可以利用下标这个隐藏条件，再假设有下面数组，数组缺少5
 * 因为每个arr[i]都对应下标i-1，我们将arr[i]对应的下标中的数组值置为负，
 * 比如值是3的对应下标2，我们将arr[2]中的值设置为arr[2]*-1。
 * 对[1,2,3,4,6,6]这个数组我们做如下操作：
 * 1=>arr[0]，将arr[0]设置成-1
 * 2=>arr[1]，将arr[1]设置成-2
 * 3=>arr[2]，将arr[2]设置成-3
 * 4=>arr[3]，将arr[3]设置成-4
 * 6=>arr[5]，将arr[5]设置成-6
 * 6=>arr[5]，将arr[5]设置成-6
 * 接着，再扫描一遍数组，找到大于0的数，这里是6，它的数组下标是4，但现在arr[4]大于0，说明缺少数字5，因为5对应数组下标4。
 * 如果数组是乱序的，也不会影响其结果。
 */
public class 找到数组中消失的所有数字448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //0.结果集
        List<Integer> result = new ArrayList<>();
        //1.先进行第一遍扫描，根据数组的值找到对应的下标（差一个值，比如3对应下标2），
        //再将arr[2]设置成负数
            for(int i = 0; i<nums.length; i++){
            int index = Math.abs(nums[i])-1;//因为有可能nums[i]为负数，因此为了保险起见，利用绝对值统一把其强转为正数
            nums[index] = -Math.abs(nums[index]);//再把该下标所对应的值设置为其负数
        }
            //2.再进行第二遍扫描，找到所有非负数，非负数所在的下标+1，即为缺失的数字
        for(int i = 0; i<nums.length; i++){
            if(nums[i] > 0) {
                result.add(i+1);
            }
        }
        //3.最后，返回结果集即可
        return result;
    }
}

class Solution111 {
    public List<Integer> findDisappearedNumbers(int[] nums) {

        HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
//先全部标记为true
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], true);
        }

        List<Integer> result = new LinkedList<Integer>();

        for (int i = 1; i <= nums.length; i++) {
            if (!map.containsKey(i)) {//再去找下标在map中是否存在，若不存在，则说明该i即为所求。同样利用的是下标和元素值相差1的思路
                result.add(i);
            }
        }
        return result;
    }
}