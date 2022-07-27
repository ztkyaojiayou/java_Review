package 数据结构与算法.第三遍;

import java.util.HashMap;

public class demo14_3map之数组中出现次数超过一半的数字 {
    public int MoreThanHalfNum_Solution(int [] nums) {
        int len = nums.length;
        //key:当前元素的值，value：该值出现的频次
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0;i<len;i++){
            if (map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i])+1);
            }
            map.put(nums[i],1);
            //开始查找/随时查找
            if (map.get(nums[i]) > len/2){
                return nums[i];
            }
        }
        return 0;
    }
}
