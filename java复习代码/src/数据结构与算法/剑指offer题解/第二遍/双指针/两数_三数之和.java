package 数据结构与算法.剑指offer题解.第二遍.双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//使用hashMap即可，简单
class 两数之和 {
    public int[] twoSum(int[] nums,int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (map.containsKey(target-nums[i])){
                return new int[]{map.get(target-nums[i]),i};
            }
                map.put(nums[i],i);
            }
        //若没找到，就返回null
        return null;
        }
}

class 三数之和为0{
public List<List<Integer>> threeSum(int[] nums){
    //结果集
    List<List<Integer>> res = new ArrayList<>();
    int len = nums.length;
    //排序
    Arrays.sort(nums);
    for (int i=0;i<len;i++){
        if (nums[i] > 0){
            break;//即直接跳出本次循环，因为数组已经进行了升序排列，所以若第一个数大于0，则后面的数肯定也大于0，则不可能等于0。
        }
        if (i>0 && nums[i] == nums[i-1]){
            continue;//即通过当前i
        }
        //首尾双指针
        int left = i+1;
        int right = len-1;
        while (left < right){
            int sum = nums[i] + nums[left] + nums[right];
            if (sum == 0){
                res.add(Arrays.asList(nums[i],nums[left],nums[right]));//把这三个数存入list中，然后再存入结果集list中
                while (left < right && nums[left] == nums[left+1]){
                    left++;
                }
                while (left < right && nums[right] == nums[right-1]){
                    right--;
                }
                left++;
                right--;
            }else if (sum < 0){
                left++;
            }else if (sum > 0){
                right--;
            }
        }
    }
    return res;
}
}

