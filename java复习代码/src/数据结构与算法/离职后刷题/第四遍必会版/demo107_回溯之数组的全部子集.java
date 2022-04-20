package 数据结构与算法.离职后刷题.第四遍必会版;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 对比全排列
 * 1.入门版：数组中没有重复元素时（不用排序）
 */
class 数组中无重复元素时 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> path = new ArrayList<>();
        method(nums, path, 0);
        return res;
    }

    //回溯方法
    private void method(int[] nums, List<Integer> path, int start) {
        //递归结束的条件？此题非常特殊，所有路径都应该加入结果集，所以不存在结束条件（切记）
        //为什么要使用new ArrayList来存储path：因为要求的是所有子集，因此每一条path都符合要求，
        //因此每一条path都应该是res中的一个元素，即都应当是一个list，因此都需要构造成list存入res中
        res.add(new ArrayList<>(path));
        //一般情况，递归
        for (int i = start; i < nums.length; i++) {
            //不用剪枝，直接做选择
            path.add(nums[i]);
            //下一步递归
            method(nums, path, i + 1);//当前元素已经使用过了，因此要跳过，因此为i+1
            //撤销
            path.remove(path.size() - 1);
        }
    }

    //自写一遍
    List<List<Integer>> res02 = new ArrayList<>();

    public List<List<Integer>> subsets02(int[] nums) {
        List<Integer> curPath = new ArrayList<>();
        method02(nums, curPath, 0);
        return res02;
    }

    private void method02(int[] nums, List<Integer> curPath, int curIndex) {
        //直接添加，不用递归条件，因为空集也算
        res02.add(new ArrayList<>(curPath));

        //决策
        for (int i = curIndex; i < nums.length; i++) {
            //不用判断是否path中是否包含
            curPath.add(nums[i]);
            //递归下一个
            method02(nums, curPath, i + 1);
            //撤回
            curPath.remove(curPath.size() - 1);
        }
    }
}


/**
 * 2.进阶版：数组中有重复元素时，只需先排序，再剪枝即可，也不难
 */
class 数组中有重复元素时 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> path = new ArrayList<>();
        //改变1：排序,很重要，目的是方便剪枝
        Arrays.sort(nums);
        method(nums, 0, path);
        return res;
    }

    private void method(int[] nums, int start, List<Integer> path) {

        //同理，也没有递归出口，因为全都要，因此直接添加：使用new ArrayList构造成list
        res.add(new ArrayList<>(path));

        //再看一般情况
        for (int i = start; i < nums.length; i++) {
            //改变2：剪枝，即跳过重复元素（核心），不需要使用visied数组标记，
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //再做选择
            path.add(nums[i]);
            //下一次递归
            //同理，也是i+1
            method(nums, i + 1, path);
            //撤销
            path.remove(path.size() - 1);
        }
    }

    //自写一遍
    List<List<Integer>> res02 = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup02(int[] nums) {
        List<Integer> curPath = new ArrayList<>();
        //先排序
        Arrays.sort(nums);
        //递归获取
        method02(nums,0,curPath);
        return res02;
    }

    private void method02(int[] nums, int curIndex, List<Integer> curPath) {
        //也不需要特点条件的递归出口，直接添加，但也要使用new ArrayList(path)构造后添加
        res02.add(new ArrayList<>(curPath));
        //一般情况，递归
        for (int i = curIndex;i<nums.length;i++){
            //改变2：去重
            if (i>0 && nums[i] ==nums[i-1]){
                continue;
            }
            //决策
            curPath.add(nums[i]);
            //递归下一个元素
            method02(nums,i+1,curPath);
            //撤销
            curPath.remove(curPath.size()-1);
        }
    }
}
