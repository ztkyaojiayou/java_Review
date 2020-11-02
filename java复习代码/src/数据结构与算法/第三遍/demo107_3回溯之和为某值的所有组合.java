package 数据结构与算法.第三遍;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 情况1：元素可以重复使用时（简单）
 */
public class demo107_3回溯之和为某值的所有组合 {
    //定义一个结果集res
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        ArrayList<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        method(nums,target,0,path);
        return res;
    }

    //回溯方法
    private void method(int[] nums, int target, int start, ArrayList<Integer> path) {
        //递归结束的出口(有2个）
        if (target < 0){
            return;
        }
        if (target == 0){
            res.add(new ArrayList<>(path));
            return;
        }
        //一般情况
        for (int i = start;i<nums.length;i++){
            //
            path.add(nums[i]);
            method(nums,target-nums[i],i,path);//因为元素可以重复使用，因此还从i开始，切记！
            path.remove(path.size()-1);
        }
    }
}


/**
 * 情况2：当元素只可使用一次时（进阶版）
 */
class solution02{
    //定义一个结果集list
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum02(int[] nums, int target) {
        ArrayList<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        method(nums,target,0,path);
        return res;
    }

    //回溯方法，区别就在于要剪枝以及下一次递归的起点（是start+1，而不再是start了）
    private void method(int[] nums, int target, int start, ArrayList<Integer> path) {
        //递归出口（同理，有两个）
        if (target < 0){
            return;
        }
        if (target == 0){
            res.add(new ArrayList<>(path));
            return;
        }
        //一般情况
        for (int i = start;i<nums.length;i++){
            //先剪枝,即去掉重复元素
            if (i > start && nums[i] == nums[i-1]){
                continue;
            }
            //再做选择
            path.add(nums[i]);
            //下一次递归
            method(nums,target-nums[i],start+1,path);
            //撤销
            path.remove(path.size()-1);
        }
    }
}
