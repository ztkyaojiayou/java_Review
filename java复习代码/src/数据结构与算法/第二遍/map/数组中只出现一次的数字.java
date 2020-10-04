package 数据结构与算法.第二遍.map;

import java.util.HashMap;

/**
 * 136. 只出现一次的数字(入门级）
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。
 * 找出那个只出现了一次的元素。
 *
 * 说明：
 * 你的算法应该具有线性时间复杂度。
 * 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 *
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
//可以用hashMap，但这个题即便是用位运算也很好理解，于是就用位运算吧！
public class 数组中只出现一次的数字 {
        public int singleNumber(int[] nums) {
            int single_num = 0;//结果变量，且根据性质1，任何数和0亦或不改变结果，因此初始值为0也不影响最终结果
            for (int num : nums) {//遍历数组，做亦或运算
                single_num ^= num;
            }
            //最终亦或的结果即为只出现一次的数字，完美~
            return single_num;
        }
    }



/**
 * 137. 只出现一次的数字 II（进阶版）
 * 给定一个非空整数数组，除了某个元素只出现一次以外，
 * （区别）其余每个元素均出现了三次。
 * 找出那个只出现了一次的元素。
 *
 * 说明：
 * 你的算法应该具有线性时间复杂度。
 * 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 * 输入: [2,2,3,2]
 * 输出: 3
 *
 * 示例 2:
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 */

/**
 * 思路解析：和上面一题类似，只不过其他元素均出现了三次而已，思路也相同
 * 可以使用hash表，排序再遍历，hashSet存值再做加减法，但是时间复杂度和空间复杂度不符合题意；
 * 因此也需要使用到位运算。
 *
 */
/**
 * 方法2：使用hashMap做字典映射（现在只挑自己会的方法了，MD！）
 *
 * 复杂度分析
 * 时间复杂度：O(N)，遍历输入数组。
 * 空间复杂度：O(N)，存储 N/3 个元素的 Set。
 */
class Solution137_2 {
    public int singleNumber(int[] nums) {
        //1.使用一个map来做哈希映射，key为数组元素，value为其出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums){
            //map.put(num, map.getOrDefault(num, 0) + 1);//骚操作，一句话就搞定，
            // 即：如果map中含有指定的key（即num），就返回该key对应的value，否则使用该方法的第二个参数0作为value返回
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        //2.再遍历map中的key（即数组中的元素）
        //2.1找到其value（即各元素出现的次数）为1的key即为所求
        for (int k : map.keySet()){
            if (map.get(k) == 1)
                return k;
        }
        //2.2若没有找到，则返回-1即可
        return -1;
    }
}
