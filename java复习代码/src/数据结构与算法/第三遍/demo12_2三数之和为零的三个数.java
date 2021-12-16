package 数据结构与算法.第三遍;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class demo12_2三数之和为零的三个数 {
    public List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        //先排序
        Arrays.sort(nums);
        for (int i = 0;i<len;i++){//固定第一个元素
            if (nums[i] > 0){
                break;
            }
            //双指针
            int left = i+1;//从第二个元素开始
            int right = len-1;
            while (left < right){//一直循环
                int cur_sum = nums[i] + nums[left] + nums[right];
                if (cur_sum == 0){
                    res.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    //去重
                    while (left < right && nums[left] == nums[left+1]){
                        left++;
                    }
                    while (left < right && nums[right] == nums[right-1]){
                        right--;
                    }
                    //找到了第一组，还要继续探索是否有下一组，此时易知要一起移动，因为此时已经去重了！
                    left++;
                    right--;
                }else if (cur_sum < 0){//若没有只找到，就只需看情况移动一边即可
                    left++;
                }else {
                    right--;
                }
            }
        }
        //返回结果即可
        return res;
    }
}
