package 数据结构与算法.第三遍;


import 数据结构与算法.TreeNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 常考
 */
public class demo22_回溯之二叉树中和为某一值的所有路径 {
    List<List<Integer>> res = new ArrayList<>();//结果集
    List<Integer> path = new ArrayList<>();//记录每一条路径

    public List<List<Integer>> FindPath(TreeNode root, int target) {//target为目标和值
        int cur_sum = 0;
        path(root, cur_sum, path, target);
        return res;
    }

    private void path(TreeNode root, int cur_sum, List<Integer> path, int target) {
        //递归结束的条件
        if (root == null) {
            return;
        }
        if (cur_sum == target && root.left == null && root.right == null) {
            res.add(new ArrayList<>(path));
        }
        //做选择
        path.add(root.val);
        cur_sum += root.val;
        //递归左右节点
        path(root.left, cur_sum, path, target);
        path(root.right, cur_sum, path, target);

        //撤销最后一步
        path.remove(path.size() - 1);
    }
}

