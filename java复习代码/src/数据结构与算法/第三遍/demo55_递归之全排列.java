package 数据结构与算法.第三遍;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//1.无重复元素时
public class demo55_递归之全排列 {
    //定义结果集list，里面包含的是一个一个的小list，每一个都代表一种排列
    List<List<Integer>> res = new LinkedList<>();
    List<List<Integer>> permute(int[] nums) {
        ArrayList<Integer> path = new ArrayList<>();
        method(nums,path,0);
        return res;
    }

    private void method(int[] nums, ArrayList<Integer> path, int start) {
        //递归结束的条件
        if (path.size() == nums.length){
            res.add(path);
            return;
        }
        //开始递归
        for (int i = 0;i<nums.length;i++){
            //剪枝，若路径中存在该元素，就跳过
            if (path.contains(nums[i])){
                continue;
            }

            path.add(nums[i]);
            method(nums,path,i+1);
            path.remove(path.size()-1);
        }
    }
}

//有重复元素时
class 递归之全排列2{
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute02(int[] nums){
        ArrayList<Integer> path = new ArrayList<>();
        Boolean[] visited = new Boolean[nums.length];
        method(nums,path,visited);
        return res;
    }

    private void method(int[] nums, ArrayList<Integer> path, Boolean[] visited) {
        if (path.size() == nums.length){
            res.add(path);
            return;
        }
        for (int i = 0;i<nums.length;i++){
            if (visited[i]){
                continue;
            }
            if (i>0 && nums[i] == nums[i-1] && visited[i-1]){
                break;
            }
            path.add(nums[i]);
            visited[i] = true;
            //下一步递归
            method(nums,path,visited);
            //撤销
            path.remove(path.size()-1);
            visited[i] = false;
        }
    }
}
