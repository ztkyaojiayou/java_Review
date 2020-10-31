package 数据结构与算法.第三遍;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * 1.无重复元素时
 */
class 回溯之全排列1_无重复元素 {
    //定义结果集list，里面包含的是一个一个的小list，每一个都代表一种排列
    List<List<Integer>> res = new LinkedList<>();

    List<List<Integer>> permute(int[] nums) {
        ArrayList<Integer> path = new ArrayList<>();
        method(nums, path, 0);
        return res;
    }

    private void method(int[] nums, ArrayList<Integer> path, int start) {
        //递归结束的条件
        if (path.size() == nums.length) {
            res.add(path);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //剪枝，若路径中存在该元素，就跳过
            if (path.contains(nums[i])) {
                continue;
            }
            //做选择,即把当前元素添加至path中
            path.add(nums[i]);
            //递归
            method(nums, path, i + 1);
            //撤销
            path.remove(path.size() - 1);
        }
    }
}


/**
 * 有重复元素时
 */
class 回溯之全排列2_有重复元素{
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
        for (int i = 0;i<nums.length;i++) {
            //剪枝，若当前元素已经使用过，就跳过
            if (visited[i]){
                continue;
            }
            //有重复元素并且已经使用过，就也跳过
            if (i>0 && nums[i] == nums[i-1] && visited[i-1]){
                break;
            }
            //做选择，添加至path，同时把当前元素标记为true，表示已经使用过
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
