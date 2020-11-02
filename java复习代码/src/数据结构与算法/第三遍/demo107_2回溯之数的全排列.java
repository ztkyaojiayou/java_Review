package 数据结构与算法.第三遍;

import 秋招笔试.其他杂七杂八.dfs;

import java.util.LinkedList;
import java.util.List;

public class demo107_2回溯之数的全排列 {
    List<List<Integer>> res = new LinkedList<>();
    List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> path = new LinkedList<>();
        method(nums,path,0);
        return res;
    }

    private void method(int[] nums, LinkedList<Integer> path, int start) {
        if (path.size() == nums.length){
            res.add(path);
            return;
        }
        for (int i = 0;i<nums.length;i++){
            if (path.contains(nums[i])){
                continue;
            }
            //做选择
            path.add(nums[i]);
            //继续递归
            method(nums,path,i+1);
            //撤销
            path.removeLast();
        }
    }
}
