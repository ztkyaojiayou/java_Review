package 数据结构与算法.第二遍.双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//使用hashMap即可，简单
//双指针也行，而且不需要额外空间
class 两数之和 {
    //方法1：hashMap，结构为：（当前元素，当前元素对应的下标）
    public int[] twoSum01(int[] nums,int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (map.containsKey(target-nums[i])){
                return new int[]{map.get(target-nums[i]),i};
            }
                map.put(nums[i],i);//（当前元素，当前元素对应的下标）
            }
        //若没找到，就返回null
        return null;
    }

    //方法2：排序+双指针，更推荐
    public ArrayList<Integer> twoSum02(int[] nums, int sum){
        Arrays.sort(nums);
        ArrayList<Integer> res = new ArrayList<>();
        int i = 0,j = nums.length-1;
        while (i< j){
            int cur = nums[i] + nums[j];
            if (cur == sum){
                res.add(nums[i]);
                res.add(nums[j]);
                return res;
            }else if (cur < sum){
                i++;
            }else {
                j--;
            }
        }
        //若没有找到，则返回一个空list
        return new ArrayList<>();
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
            break;//即直接跳出本次循环，因为数组已经进行了升序排列，所以若第一个数大于0，
            //则后面的数肯定也大于0，则不可能等于0。
        }
        if (i>0 && nums[i] == nums[i-1]){
            continue;//即pass当前i
        }
        //首尾双指针
        int left = i+1;
        int right = len-1;
        while (left < right){
            int sum = nums[i] + nums[left] + nums[right];
            if (sum == 0){
                //把这三个数存入list中，然后再存入结果集list中
                res.add(Arrays.asList(nums[i],nums[left],nums[right]));
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

