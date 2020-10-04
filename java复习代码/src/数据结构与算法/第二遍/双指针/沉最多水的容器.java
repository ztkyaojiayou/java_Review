package 数据结构与算法.第二遍.双指针;

public class 沉最多水的容器 {
    public int maxArea(int[] nums){
        int res = 0;
        //双指针
        int i= 0;
        int j= nums.length-1;
        while (i<j){
            int area = (j-i)*Math.min(nums[i],nums[j]);
            res = Math.max(res,area);
            if (nums[i] < nums[j]){
                i++;
            }else {
                j--;
            }
        }
        return res;
    }

}
