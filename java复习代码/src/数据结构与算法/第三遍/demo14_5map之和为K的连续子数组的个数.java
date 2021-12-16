package 数据结构与算法.第三遍;

import java.util.HashMap;

//前缀和+map
public class demo14_5map之和为K的连续子数组的个数 {
    public int subarraySum(int[] nums, int k) {
        int res = 0;
        int cur_sum = 0;
        //存的是前缀和及其个数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0;i<nums.length;i++){
            //先把每个前缀和存入
            cur_sum+= nums[i];
            if (map.containsKey(cur_sum)){
                map.put(cur_sum,map.get(cur_sum)+1);
            }else{
                map.put(cur_sum,1);
            }
            //再找与k相减后的前缀和的个数即可
            int pre_sum = cur_sum-k;
            if (map.containsKey(pre_sum)){
                res += map.get(pre_sum);
            }
        }
        return res;
    }
}
