package 数据结构与算法.第三遍;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1.入门版：数组中没有重复元素时（不用排序）
 */
public class demo107_4回溯之数组的全部子集 {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        ArrayList<Integer> path = new ArrayList<>();
        method(nums, path,0);
        return res;
    }

    //回溯方法
    private void method(int[] nums, ArrayList<Integer> path, int start) {
        //递归结束的条件？此题非常特殊，所有路径都应该加入结果集，所以不存在结束条件（切记）
        res.add(new ArrayList<>(path));
        //一般情况
        for (int i =start;i<nums.length;i++){
            //不用剪枝，直接做选择
            path.add(nums[i]);
            //下一步递归
            method(nums,path,i+1);//当前元素已经使用过了，因此要跳过，因此为i+1
            //撤销
            path.remove(path.size()-1);
        }
    }
}


/**
 * 2.进阶版：数组中有重复元素时，只需先排序，再剪枝即可，也不难
 */
class 数组中有重复元素时 {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        ArrayList<Integer> path = new ArrayList<>();
        Arrays.sort(nums); //排序,很重要，目的是方便剪枝
        method(nums, 0, path);
        return res;
    }

    private void method(int[] nums, int start, ArrayList<Integer> path) {

        //同理，也没有递归出口，因为全都要，因此直接添加
        res.add(new ArrayList<>(path));

        //再看一般情况
        for (int i = 0;i<nums.length;i++){
            //先剪枝，即跳过重复元素（核心）
            if (i > start && nums[i] == nums[i-1]){
                continue;
            }
            //再做选择
            path.add(nums[i]);
            //下一次递归
            method(nums,i+1,path);//同理，也是i+1
            //撤销
            path.remove(path.size()-1);
        }
    }
}
