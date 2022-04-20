package 数据结构与算法.离职后刷题.第四遍必会版;

import java.util.HashMap;
import java.util.Map;

//其他元素重复2次
class demo07_找出数组中只出现一次的数字 {
    public int singleNumber(int[] nums) {
        int res = 0;
        //做亦或运算，因为相同时为0，且0与任何数亦或均为原值
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }

    //自写一遍
    public int singleNumber01(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}


//其他元素重复三次（多少次都可以），此时就用常规的map计数即可
class Solution137_2 {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();//存的是该值及该值出现的次数
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i] + 1));
            }
            map.put(nums[i], 1);
        }
        //然后再遍历查找即可
//        //先获取到key（是一个set），再遍历key，找对应的value
//        Set<Integer> keys = map.keySet();
//        int size = keys.size();
//        for (int i = 0; i < size; i++) {
//            if (map.get(nums[i]) == 1) {
//                return nums[i];
//            }
//        }
        //推荐使用这种遍历方式，更简洁
        for (Map.Entry<Integer, Integer> entrySet: map.entrySet()) {
            if (entrySet.getValue() == 1){
                return entrySet.getKey();
            }
        }
        return -1;
    }
}