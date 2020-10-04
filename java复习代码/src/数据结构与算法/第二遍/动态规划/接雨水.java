package 数据结构与算法.第二遍.动态规划;

//使用动态规划优化版
public class 接雨水 {
    public int trap(int[] nums){
        int sum = 0;
        int[] max_left = new int[nums.length];
        int[] max_right = new int[nums.length];

        for (int i=1;i<nums.length;i++){
            max_left[i] = Math.max(nums[i-1],max_left[i-1]);
        }
        for (int j=nums.length-2;j>=0;j--){
            max_right[j] = Math.max(nums[j+1],max_right[j+1]);
        }
        //再开始累加计算
        for (int i=0;i<nums.length;i++){
            int min_hight = Math.min(max_left[i],max_right[i]);
            if (min_hight > nums[i]){//即有凹槽时才可以接得住雨水嘛~
                sum = sum + (min_hight - nums[i]);
            }
        }
        return sum;
    }

}
