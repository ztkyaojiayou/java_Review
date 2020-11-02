package 数据结构与算法.LeetCode题解.回溯_递归_记忆化搜索;

import java.util.*;

/**
 * 39. 组合总和（入门版）
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，
 * 找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 *
 * 示例 1:
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],//我们在代码中，把每一个结果都看成了一个list，再把每一个list再存入一个大的list中
 *   [2,3,3],
 *   [3,5]
 * ]
 */

/**
 * 思路：递归
 *
 * 根据示例 1：输入: candidates = [2,3,6,7]，target = 7。
 * 因为候选数组里有 2 ，如果找到了 7 - 2 = 5 的所有组合，再在之前加上 2 ，就是 7 的所有组合；
 * 同理考虑 3，如果找到了 7 - 3 = 4 的所有组合，再在之前加上 3 ，就是 7 的所有组合，依次这样找下去，易知，同样又可以画出一颗二叉树啦；
 *
 * 去重复
 * 在搜索的时候，需要设置搜索起点的下标 begin ，由于一个数可以使用多次，所以下一层的结点还要从这个搜索起点开始搜索；
 * 在搜索起点 begin 之前的数因为以前的分支搜索过了，所以一定会产生重复，我们可以通过先对候选数组排一下序即可。
 *
 * 剪枝提速
 * 如果一个数位搜索起点都不能搜索到结果，那么比它还大的数肯定搜索不到结果，基于这个想法，我们可以对输入数组进行排序，以减少搜索的分支；
 * 排序是为了提高搜索速度，非必要；
 * 搜索问题一般复杂度较高，能剪枝就尽量需要剪枝。把候选数组排个序，遇到一个较大的数，如果以这个数为起点都搜索不到结果，后面的数就更搜索不到结果了。
 *
 * 重点就是如何进行递归!
 * 递归的第一步，当然是写递归的终止条件啦，没有终止条件的递归会进入死循环。
 * 那么有 哪些终止条件呢？由于条件中说了都是正整数。
 * 因此，如果 target<0,当然是要终止了，如果 target==0，说明此时找到了一组数的和为 target，将其加进去。
 * 我们是要求组成 target 的组合。因此需要一个循环来进行遍历。每遍历一次，将此数加入 list，然后进行下一轮递归。
 *
 */
public class 组合总和39 {
    //定义一个结果集res
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //特判
        if (candidates == null || candidates.length == 0 || target < 0) {
            return res;
        }
        //定义一个保存/记录每次递归后产生的一个结果的list，
        //若把该问题转化为一棵树来理解的话，那么每一个list其实就相当于一条符合要求的路径
        List<Integer> path = new ArrayList<>();//有些题解用的linkedList，都行，只是此时在删除最后一个节点时可以使用removeLast方法
        Arrays.sort(candidates);//排序是为了去重（如[2,3,2]和[2,2,3]其实是同一种结果）
        //开始递归，下标从候选数组的0开始
        process(0, candidates, target, path);
        //最终，返回一个包含多个list（每一个list都是一种结果，如[2,3,2]）的lists结果集
        return res;
    }

    /**
     *
     * @param start 候选数组中可选数字的下标
     * @param nums 候选数组
     * @param target 要被组合的目标数（一开始是题给的那个数，但随着递归的进行，它会变化）
     * @param path 用于存储每一次递归后产生的一个结果，如【2,3,2】
     */
    //具体的递归算法
    //我们这里使用做减法的方式递归，即让target依次减去候选数组中的一个数，再用剩余的数递归。
    private void process(int start, int[] nums, int target, List<Integer> path) {
        //（1）终止递归的条件（重点）
        //1.target不能小于0
        if (target < 0) {
            return;
        }
        //2.等于0时说明已经找到了一个结果集
        if (target == 0) {//此时说明已经找到了一个结果组合，如【2,3,2】，
            // 把该结果中的每一个元素先存入一个list中（直接每次临时new一个list即可），再把该list存入到结果集lists中
            res.add(new ArrayList<>(path));
            return;
        }
             //考虑一般情况，使用递归算法，分为四部曲；
            //在开始递归前要先做选择，即遍历一下候选数组，并把已经选择的数记录到list中即可
            for (int i = start; i < nums.length; i++) {
                //（2）做选择
                path.add(nums[i]);
                //（3）开始递归；因为每个数字都可以使用无数次，所以递归还可以从当前元素开始
                process(i, nums, target - nums[i], path);
                //（4）在递归调用之后要 撤销刚才的选择，这也是递归或回溯算法的核心所在。
                //因此，在每次递归完成后要把最新加的那个数删除/撤销。
                path.remove(path.size() - 1);
            }
    }
}

/**
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，
 * 找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次（区别）。
 *
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 *
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */

//也不难，只是每一个元素只可用一次，只需要保证同一层中只有1个相同的元素，
// 不同层可以有重复元素，同样也要先对数组排序
class solution40{
    //定义一个结果集lists
    List<List<Integer>> results = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        //特判
        if (nums == null || nums.length == 0 || target < 0) {
            return results;
        }
        //定义一个保存/记录每次递归后产生的一个结果的list，
        //若把该问题转化为一棵树来理解的话，那么每一个list其实就相当于一条符合要求的路径
        List<Integer> list = new ArrayList<>();//有些题解用的linkedList，都行，只是此时在删除最后一个节点时可以使用removeLast方法
        Arrays.sort(nums);//排序是为了去重（如[2,3,2]和[2,2,3]其实是同一种结果）
        //开始递归，下标从候选数组的0开始
        process(0, nums, target, list);
        //最终，返回一个包含多个list（每一个list都是一种结果，如[2,3,2]）的lists结果集
        return results;
    }

    /**
     *
     * @param start 候选数组中可选数字的下标
     * @param candidates 候选数组
     * @param target 要被组合的目标数（一开始是题给的那个数，但随着递归的进行，它会变化）
     * @param list 用于存储每一次递归后产生的一个结果，如【2,3,2】
     */
    //具体的递归算法
    //我们这里使用做减法的方式递归，即让target依次减去候选数组中的一个数，再用剩余的数递归。
    private void process(int start, int[] candidates, int target, List<Integer> list) {
        //（0）终止递归的条件（重点）
        //1.target不能小于0
        if (target < 0) {
            return;
        }
        //2.此时说明已经找到了一个结果组合，如【2,3,2】
        if (target == 0) {
            //把该结果中的每一个元素先存入一个list中（直接每次临时new一个list即可），
            // 再把该list存入到结果集lists中
            results.add(new ArrayList<>(list));
            return;
        }
        //考虑一般情况，使用递归算法，分为四部曲；
        //在开始递归前要先做选择，即遍历一下候选数组，并把已经选择的数记录到list中即可
        for (int i = start; i < candidates.length; i++) {
            // (1)先排除不合法选择,即剪枝
            //剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
            if (i>start && candidates[i] == candidates[i-1]){
                continue;
            }
            //（2）做选择
            list.add(candidates[i]);
            //（3）开始递归；// 因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i，务必注意！
            process(i+1, candidates, target - candidates[i], list);
            //（4）在递归调用之后要 撤销刚才的选择，这也是递归或回溯算法的核心所在。
            //因此，在每次递归完成后要把最新加的那个数删除/撤销。
            list.remove(list.size() - 1);
        }
    }
}


