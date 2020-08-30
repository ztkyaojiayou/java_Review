package 数据结构与算法.剑指offer题解.第二遍.回溯与递归;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 组合_入门版 {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> unionSum(int[] nums,int target){
        ArrayList<Integer> path = new ArrayList<>();
        //排序，去重
        Arrays.sort(nums);
        backtrace(nums,target,path,0);
        return res;
    }

    private void backtrace(int[] nums, int target, ArrayList<Integer> path, int start) {
        if (target == 0){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i= start;i<nums.length;i++){
           path.add(nums[i]);
           backtrace(nums,target-nums[i],path,i);//因为每个数字都可以使用无数次，所以递归还可以从当前元素开始
           path.remove(path.size()-1);
        }
    }
}

class 组合_进阶版{
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> unionSum(int[] nums,int target){
        ArrayList<Integer> path = new ArrayList<>();
        //排序，去重
        Arrays.sort(nums);
        backtrace(nums,target,path,0);
        return res;
    }

    private void backtrace(int[] nums, int target, ArrayList<Integer> path, int start) {
        //递归结束的条件
        if (target == 0){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i= start;i<nums.length;i++){
            //先排除不合法选择（与上一题的区别1）,保证同一层中只有1个相同的元素，不同层可以有重复元素
            if (i>start && nums[i] == nums[i-1]){
                continue;
            }
            path.add(nums[i]);
            //（区别2）因为每个数字都只可以使用一次，所以递归要从下一个元素开始
            backtrace(nums,target-nums[i],path,i+1);
            path.remove(path.size()-1);
        }
    }
}
