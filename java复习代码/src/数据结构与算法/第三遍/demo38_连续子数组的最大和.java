package 数据结构与算法.第三遍;

public class demo38_连续子数组的最大和 {
    private int FindGreatestSumOfSubArray(int[] nums) {
        int cur_sum = 0;
        int res = 0;
        for (int i = 0;i<nums.length;i++){
            cur_sum += nums[i];
            res = Math.max(res,cur_sum);
            //若加出来为负，则重新置零
            if (cur_sum < 0){
                cur_sum = 0;
            }
        }
        return res;
    }
}
