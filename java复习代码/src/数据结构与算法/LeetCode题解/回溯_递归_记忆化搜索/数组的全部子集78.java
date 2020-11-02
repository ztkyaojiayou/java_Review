package 数据结构与算法.LeetCode题解.回溯_递归_记忆化搜索;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 78. 子集
 * 给定一组不含重复元素的整数数组 nums，
 * 返回该数组所有可能的子集（幂集，即总共有2的n次幂个子集，包含空集和该数组本身）。
 *
 * 说明：子集只是不能包含重复的子集，但顺序是可以不同的，
 * 如[1,2,3]和[1,3,2]只是顺序不同，应当算作一种。
 *
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */

/**
 * 解析：使用递归
 * 易知，对 results 的更新本质上就是对一颗数的前序遍历,也就是说,results就是树上的所有节点
 */
public class 数组的全部子集78 {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        ArrayList<Integer> path = new ArrayList<>();
        backtrack(nums, path,0);
        return res;
    }

    /**
     *
     * @param nums 题给的数字字符串
     * @param start 索引，从零开始，不过他会随着每一个递归而加一，因而在下一次递归时就不是从0开始啦
     * @param path 每一次递归后的结果/路径（也即一个子集）
     */
    public void backtrack(int[] nums, ArrayList<Integer> path,int start){
        //递归结束的条件？此题非常特殊，所有路径都应该加入结果集，所以不存在结束条件（切记）
        //每次的子集(即每一次递归的结果）都放到结果当中
        //易知，第一次添加的是“空子集”，最后一次添加的是本身
        res.add(new ArrayList<Integer>(path));

        //一般情况
        for(int i = start; i<nums.length; i++){//横向，如，若给定的数组为[1,2,3]，
            // 则先固定的是第一个元素，即nums[0],也就是1，先把1添加到list（因为1本身就是一个子集），
            // 再调用递归函数，此时就又回到line47，于是执行line50，把该结果添加进结果集，
            // （重点）注意，此时元素1还存在于list中，它会继续去拼接后面的元素2或3，这也是为什么能得到[1,2],[1,2,3]的原因
            //此时，index加1，变为 1，再同样地，把nums[1]，也就是2，添加到结果集，
            //此时index = 2,把nums[2]，也就是3添加到结果集，同理，最后会依次把[1],[1,2],[1,2,3]这三个结果存入结果集中；
            //此时才进行for循环中的i++，变成1，于是开始按照同样地流程把[2],[2,3]存入结果集中，然后i++，变成2，把[3]也存入结果集中

            //做出选择
            path.add(nums[i]);
            //递归，进入下一层(这一步会一直递归到最后一层直至退出递归，才执行下面的撤销/回溯语句，
            //也即先最终会求出[1,2,3]这个结果（但在这个过程中的所有结果也会被记入结果集中，即[1],[1,2]，因为它也符合题意)）
            backtrack(nums,path,i+1);//纵向
            //撤销选择，回溯
            path.remove(path.size() - 1);
        }
    }
}

/**
 * 进阶版：数组中有重复元素时
 */
class solution02{
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums); //排序
        getAns(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void getAns(int[] nums, int start, ArrayList<Integer> temp, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            //和上个数字相等就跳过
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            getAns(nums, i + 1, temp, ans);
            temp.remove(temp.size() - 1);
        }
    }
}





