package 数据结构与算法.第三遍;

import 数据结构与算法.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 入门版
 */
class demo105_1递归之判断是否存在 {
    public boolean hasPathSum(TreeNode root, int sum) {
        //递归出口
        if (root.val == sum && root.left == null && root.right == null) {
            return true;
        }
        //向左右子节点递归
        boolean left = hasPathSum(root.left, sum - root.val);
        boolean right = hasPathSum(root.right, sum - root.val);
        return left || right;//只要有一条路径即可
    }
}


/**
 * 进阶版:求这些路径（回溯）
 */
class demo105_2回溯之求这些路径 {
    //结果集
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        //当前路径
        List<Integer> curPath = new LinkedList<>();
        method(root, sum, curPath);
        return res;
    }

    //递归
    private void method(TreeNode root, int sum, List<Integer> path) {
        //递归出口
        if (root == null) {
            return;
        }
        if (root.val == sum && root.left == null && root.right == null) {
            res.add(path);//上一题是返回true，这里则是添加至路径path
        }
        //做选择
        path.add(root.val);

        //下一步递归（递归左右子节点）
        method(root.left, sum - root.val, path);
        method(root.right, sum - root.val, path);

        //撤销
        path.remove(path.size() - 1);
    }
}
