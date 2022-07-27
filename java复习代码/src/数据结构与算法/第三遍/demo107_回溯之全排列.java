package 数据结构与算法.第三遍;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 1）给定数组中无重复元素时
 */
class 回溯之全排列1_无重复元素 {
    //定义结果集list，里面包含的是一个一个的小list，每一个都代表一种排列
    List<List<Integer>> res = new LinkedList<>();

    List<List<Integer>> permute(int[] nums) {
        List<Integer> path = new ArrayList<>();
        method(nums, path, 0);
        return res;
    }

    private void method(int[] nums, List<Integer> curPath, int start) {
        //递归结束的条件（即元素个数需要和原数组的个数相等，但是与子集相关的问题则不需要，切记！！！）
        if (curPath.size() == nums.length) {
            res.add(curPath);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            //剪枝，若路径中存在该元素，就跳过
            if (curPath.contains(nums[i])) {
                continue;
            }
            //做选择,即把当前元素添加至path中
            curPath.add(nums[i]);
            //固定第一个元素，递归添加下一个元素
            //因为元素只可使用一次，因此要从下一个元素递归
            method(nums, curPath, i + 1);
            //撤销（深度遍历都需要做撤销操作）
            curPath.remove(curPath.size() - 1);
        }
    }


    //自写一遍
    //定义结果集list，里面包含的是一个一个的小list，每一个都代表一种排列
    List<List<Integer>> res02 = new LinkedList<>();

    List<List<Integer>> permute02(int[] nums) {
        List<Integer> path = new ArrayList<>();
        method02(nums, path, 0);
        return res02;
    }

    private void method02(int[] nums, List<Integer> curPath, int curIndex) {
        if (curPath.size() == nums.length) {
            res02.add(curPath);
            //可不写
            return;
        }

        for (int i = curIndex; i < nums.length; i++) {
            //去重复
            if (curPath.contains(nums[i])) {
                continue;
            }
            curPath.add(nums[i]);
            method02(nums, curPath, i + 1);
            //回退
            curPath.remove(curPath.size() - 1);
        }
    }
}


/**
 * 2）给定数组中有重复元素时（先放一边）
 * 此时，即[1,1,2]和[1,2,1]是可以的（只是元素重复，但是是两种不同的结果，都符合题目要求），
 * 但是[1,1,2]和[1,1,2]就是重复的（结果重复）。
 */
class 回溯之全排列2_有重复元素 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute02(int[] nums) {
        List<Integer> path = new ArrayList<>();
        //首先给数组排序
        // 这一步保证了preNum/num[i-1]一定是一个等于或者小于nums[i]的数，
        // 这也就是达到了去重的效果
        Arrays.sort(nums);
        //因为有重复元素，因此要对使用过的元素进行标记，防止重复使用
        Boolean[] visited = new Boolean[nums.length];
        method(nums, path, visited);
        return res;
    }

    private void method(int[] nums, List<Integer> curPath, Boolean[] visited) {
        if (curPath.size() == nums.length) {
            res.add(curPath);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //剪枝，若当前元素已经使用过，就跳过
            if (visited[i]) {
                continue;
            }
            //当为重复元素并且已经使用过，就也跳过
            //这个判断条件第二次递归时会用上
            if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1]) {
                continue;
            }
            //做选择，添加至path，同时把当前元素标记为true，表示已经使用过
            curPath.add(nums[i]);
            visited[i] = true;
            //下一步递归
            method(nums, curPath, visited);
            //撤销（还要记得将其重置为false，表示未使用过）
            curPath.remove(curPath.size() - 1);
            visited[i] = false;
        }
    }

    //自写一遍
    List<List<Integer>> res002 = new ArrayList<>();

    public List<List<Integer>> permute002(int[] nums) {
        List<Integer> curPath = new ArrayList<>();
        //记录各元素（记录对应的下标）是否使用过（true为已使用过）
        Boolean[] visited = new Boolean[nums.length];
        //排序（关键）
        Arrays.sort(nums);
        method002(nums, curPath, visited);
        return res002;
    }

    //递归回溯
    private void method002(int[] nums, List<Integer> curPath, Boolean[] visited) {
        if (curPath.size() == nums.length) {
            res002.add(curPath);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //也是判断当前元素是否被使用过，但是和无重复元素时不同，
            // 这里由于已经使用了一个boolean数组来标记是否被使用过，
            // 因此直接用此判断即可，也正因为有重复元素，因此curPath中本来就有重复元素，
            // 因此不能使用curPath.contains(num[i])来判断啦！！！
            if (visited[i]){
                continue;
            }
            //若当前元素没有使用过，但
            if (i>0 && nums[i] == nums[i-1] && visited[i-1]){
                continue;
            }
            //决策
            curPath.add(nums[i]);
            visited[i] = true;

            //下次递归（）
            method002(nums,curPath,visited);

            //撤销(两个步骤）
            curPath.remove(curPath.size()-1);
            //撤销当前元素，而不是curPath.size()-1，
            //因为这个i在递归到最后时就是最后一个元素啦！！！
            visited[i] = false;
        }
    }
}
