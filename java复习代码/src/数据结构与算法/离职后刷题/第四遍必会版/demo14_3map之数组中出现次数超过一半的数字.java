package 数据结构与算法.离职后刷题.第四遍必会版;

import java.util.HashMap;

public class demo14_3map之数组中出现次数超过一半的数字 {
    public int MoreThanHalfNum_Solution(int[] nums) {
        int len = nums.length;
        //key:当前元素的值，value：该值出现的频次
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            //若重复，则累加
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {//一定要加else啊啊啊啊啊啊，否则又会被覆盖呀
                // 因为这是两种不同情况！！！！
                map.put(nums[i], 1);
            }
            //开始查找/随时查找（节省一次循环开销呀）
            if (map.get(nums[i]) > len / 2) {
                return nums[i];
            }
        }
        return 0;
    }
}
