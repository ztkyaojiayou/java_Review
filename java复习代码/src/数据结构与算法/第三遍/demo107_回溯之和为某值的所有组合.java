package 数据结构与算法.第三遍;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 即组合问题
 * 注意：这题和给定数组中是否有重复元素无关，只管一个元素能否多次使用！！！
 * 情况1：元素可以重复使用时（简单）
 */
class 元素可以重复使用时 {
    //定义一个结果集res，全局变量
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] nums, int sum) {
        List<Integer> path = new ArrayList<>();
        //再调用回溯方法
        method(nums,sum,0,path);
        return res;
    }

    //回溯方法
    private void method(int[] nums, int sum, int start, List<Integer> path) {
        //递归结束的出口(有2个）
        if (sum < 0){
            return;
        }
        if (sum == 0){
            res.add(new ArrayList<>(path));
            return;
        }
        //一般情况
        for (int i = start;i<nums.length;i++){
            //做选择
            path.add(nums[i]);
            //下一次递归
            //因为元素可以重复使用，因此还从i开始，切记！！！
            method(nums,sum-nums[i],i,path);
            //撤销
            path.remove(path.size()-1);
        }
    }
}


/**
 * 情况2：当元素只可使用一次时（进阶版）
 * 易知，肯定需要去重
 */
class 元素只可使用一次时{
    //定义一个结果集list
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum02(int[] nums, int target) {
        List<Integer> path = new ArrayList<>();
        //改变1：排序,目的是为了去重（如[2,3,2]和[2,2,3]其实是同一种结果）
        Arrays.sort(nums);
        method(nums,target,0,path);
        return res;
    }

    //回溯方法，区别就在于要剪枝以及下一次递归的起点（是start+1，而不再是start了）
    private void method(int[] nums, int target, int start, List<Integer> path) {
        //递归出口（同理，有两个）
        //因为target-nums[i]完全有可能小于0呀，
        // 怎么可能刚刚好为0呀，因此需要特判！！！
        if (target < 0){
            return;
        }
        //为0时表示刚好找到了
        if (target == 0){
            res.add(new ArrayList<>(path));
            return;
        }
        //一般情况
        for (int i = start;i<nums.length;i++){
            ////改变2：剪枝,即去掉重复元素（也不需要使用visited数组来进行标记判断）
            if (i > start && nums[i] == nums[i-1]){
                continue;
            }
            //再做选择
            path.add(nums[i]);
            //下一次递归
            //改变3：因为元素不可重用，因此要从下一个元素开始
            method(nums,target-nums[i],i+1,path);
            //撤销
            path.remove(path.size()-1);
        }
    }
}
