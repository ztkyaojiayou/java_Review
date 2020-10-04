package 数据结构与算法.第二遍.动态规划;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 最长连续子序列的长度 {
public int longestNum(int[] nums){
    if (nums == null || nums.length == 0){
        return 0;
    }
    //先排序
    Arrays.sort(nums);
    int len = nums.length;
    int res_max = 1,curMax = 1;
    for (int i= 1;i<len;i++){
        if (nums[i] != nums[i-1]){
            if (nums[i] - nums[i-1] == 1){
                curMax++;
            }else {
                res_max = Math.max(res_max,curMax);
                curMax = 1;
            }
        }
    }
    return Math.max(res_max,curMax);
}

//方法2：使用hashMap，也很好，且时间复杂度满足要求（O(n))
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        Map<Integer, Integer> map = new HashMap<>(); // 记录区间 [v, r]
        for (int v : nums) {
            map.put(v, v);
        }
        int max = 1;
        for (int v : nums) {
            int r = v;
            while (map.containsKey(r + 1)){
                r = map.get(r + 1); // 利用前面已知的右边界，快速找到当前需要的右边界
            }
            map.put(v, r);
            max = Math.max(max, r - v + 1);
        }
        return max;
    }
}
