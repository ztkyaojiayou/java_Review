package 数据结构与算法.剑指offer题解.第二遍.回溯与递归;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 全排列_入门版 {
    //(1)入门版
    // 题干：给定一个 没有重复 数字的序列，返回其所有可能的全排列。
    //1.结果集
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums){
        //2.记录path，即每一个排列
        ArrayList<Integer> path = new ArrayList<>();
        //3.调用回溯方法进行求解
        backtrack(nums,path,0);
        //4.最后，返回结果即可
        return res;
    }

    private void backtrack(int[] nums, ArrayList<Integer> path, int start) {
        //递归结束的条件
        if (path.size() == nums.length){
            res.add(new ArrayList<>(path));
            return;
        }
        //开始回溯式地添加
        for (int i=0;i<nums.length;i++){
            //排除重复元素
            if (path.contains(nums[i])){
                continue;
            }
            //添加当前元素到path中
            path.add(nums[i]);
            //开始下一轮的回溯
            backtrack(nums,path,i+1);
            //撤销最后添加的那个元素
            path.remove(path.size()-1);//移除path中的最后一个元素，这是回溯的精髓所在，务必理解。
        }
    }

}

class 全排列_进阶版{
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute02(int[] nums){
        int len = nums.length;
        //先给数组排序
        Arrays.sort(nums);
        ArrayList<Integer> path = new ArrayList<>();
        //定义visited数组
        boolean[] visited = new boolean[len];
        backtrace(nums,path,visited);
        return res;
    }

    private void backtrace(int[] nums, ArrayList<Integer> path, boolean[] visited) {
        //递归结束的条件
        if (path.size() == nums.length){
            res.add(new ArrayList(path));
            return;
        }
        //排除不合法选择
        for (int i=0;i<nums.length;i++){
            if (visited[i]){
                continue;
            }
            if (i>0 && nums[i] == nums[i-1] && visited[i-1]){
                break;
            }
            //做选择
            path.add(nums[i]);
            visited[i] = true;
            //进行下一轮递归
            backtrace(nums,path,visited);//注意，此时的visited数组已经有变化了哦~
            //撤销添加进path的最后一个元素(两个步骤，即删除和重置为"未访问过")
            path.remove(path.size()-1);
            visited[i] = false;
        }
    }
}
