package 数据结构与算法.第三遍;

import java.util.HashMap;
import java.util.Set;

//其他元素重复2次
public class demo07_找出数组中只出现一次的数字 {
    public int singleNumber(int[] nums) {
        int res = 0;
        //做亦或运算，因为相同为0，且0与任何数亦或均为原值
        for (int i = 0;i<nums.length;i++){
            res ^= nums[i];
        }
        return res;
    }
}


//其他元素重复三次（多少次都可以），此时就用常规的map计数即可
class Solution137_2 {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();//存的是该值及该值出现的次数
        for (int i = 0;i<nums.length;i++){
            if (map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i]+1));
            }
            map.put(nums[i],1);
        }
        //先获取到key（是一个set），再遍历key，找对应的value
        Set<Integer> keys = map.keySet();
        int size = keys.size();
        for (int i = 0;i<size;i++){
            if (map.get(i) == 1){
                return i;
            }
        }
        return -1;
    }
}