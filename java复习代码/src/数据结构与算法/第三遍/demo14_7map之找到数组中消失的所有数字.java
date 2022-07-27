package 数据结构与算法.第三遍;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 题目1：
 * /**
 * * 448. 找到数组中所有消失的数字
 * * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，
 * * 数组中的元素一些出现了两次，另一些只出现一次。
 * * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * *
 * * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗?
 * * 你可以假定返回的数组不算在额外空间内。
 * *
 * * 示例:
 * * 输入:
 * * [4,3,2,7,8,2,3,1]
 * * 输出:
 * * [5,6]
 */
//易知，很容易想到用hashMap
//滴滴二面（问法变为了：在一个数组中随机选一个数，怎么快速知道这个数是多少？）
class demo44_找到数组中消失的所有数字 {
    //先来简单解法：该方法依然适用于只消失一个元素的情况（即下面的题目2）
    public List<Integer> findDisappearedNumbers01(int[] nums) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        //先把每个元素都标记为true
        for (int i = 0; i < nums.length; i++) {
            //即便有重复元素，也会被覆盖，不冲突
            //因为我们只是要标识哪些元素出现过，而不用管次数
            map.put(nums[i], true);
        }
        List<Integer> res = new ArrayList<>();
        //使用自然顺序序列去检查即可
        //因为虽然元素个数和自然顺序一致，但是有重复元素，
        //因此当我们使用自然顺序去检查时，
        //就会很容易查出是那个元素不存在呀
        for (int i = 1; i < nums.length; i++) {
            //若不存在，则该下标即为所求
            if (!map.containsKey(i)) {
                res.add(i);
            }
        }
        return res;
    }


    //自写一遍
    public List<Integer> findDisappearedNumbers001(int[] nums) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //根本不用判断，直接塞就行，因为当重复时覆盖即可，
            //因为我们只需要标识它存在即可
            map.put(nums[i], true);
        }
        //再使用自然序列去检查即可
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            if (!map.containsKey(i)) {
                list.add(i);
            }
        }
        return list;
    }
}


/**
 * 题目2：找到数组中消失的那一个数字，即只消失一个数
 * 数组nums包含从0到n的所有整数（而不是从1开始），但其中缺了一个。
 * 请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
 * <p>
 * 示例 1：
 * 输入：[3,0,1]
 * 输出：2
 *  
 * 示例 2：
 * 输入：[9,6,4,2,3,5,7,0,1]
 * 输出：8
 * <p>
 * 思路：依然可以使用上述方法，但此题还有更加巧妙的解法：
 * 实际总和减现在总和，就是结果（妙哉！！！）
 */
class demo44_找到数组中消失的那一个数字 {
    public int missingNumber(int[] nums) {
        int sum = nums.length;//记录最终的和，先要从最大值开始（可自行验证 ）
        for (int i = 0; i < nums.length; i++) {
            //必须先减再加，否则最终结果不只是消失的那个数（也可自行验证）
            sum -= nums[i];//减去现在总和（边加边减，妙哉~）
            sum += i;//实际总和的算法易知就是把各下标加起来
        }
        //最终的sum即为结果
        return sum;
    }

    //自写一遍
    public int missingNumber02(int[] nums) {
        int sum = nums.length;
        for (int i = 0; i < nums.length; i++) {
            sum -= nums[i];
            sum += i;
        }
        return sum;
    }
}
