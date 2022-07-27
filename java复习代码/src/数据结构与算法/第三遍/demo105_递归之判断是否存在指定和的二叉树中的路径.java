package 数据结构与算法.第三遍;

import 数据结构与算法.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 入门版
 */
class demo105_1递归之判断是否存在 {
    public boolean hasPathSum(TreeNode root, int sum) {
        //递归出口
        //叶子节点（最开始是根节点，递归可变）是否等于目标和（递归可变）
        if (root.val == sum && root.left == null && root.right == null) {
            return true;
        }
        //向左右子节点递归
        //再递归判断以根节点的左节点的二叉树中是否存在指定和（易知此时要刨去根节点的值啦）
        boolean left = hasPathSum(root.left, sum - root.val);
        //同理，再递归判断以根节点的右节点的二叉树中是否存在指定和（此时也要刨去根节点的值啦）
        boolean right = hasPathSum(root.right, sum - root.val);
        //返回：只要有一条路径即可
        return left || right;
    }

    //自写一遍
    public boolean hasPathSum02(TreeNode root, int sum) {
        //递归出口
        if (root.val == sum && root.left == null && root.right == null) {
            return true;
        }

        //再判断只有子节点，注意sum要刨去根节点
        return hasPathSum02(root.left, sum - root.val) || hasPathSum02(root.right, root.val);

    }

}


/**
 * 进阶版:求这些路径（回溯）
 * 注意：是求和为sum的所有路径，而不是每一条路径
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
            //上一题是返回true，这里则是添加至路径path
            res.add(path);

        }
        //添加--做选择（先添加，再做判断）
        path.add(root.val);

        //下一步递归（递归左右子节点）
        method(root.left, sum - root.val, path);
        method(root.right, sum - root.val, path);

        //撤销
        path.remove(path.size() - 1);
    }


    //自写一遍
    //结果集
    List<List<Integer>> res02 = new LinkedList<>();

    public List<List<Integer>> pathSum02(TreeNode root, int sum) {
        List<Integer> curPath = new ArrayList<>();
        //递归求解
        method02(root, sum, curPath);
        return res02;
    }


    //递归
    private void method02(TreeNode root, int sum, List<Integer> path) {
        if (root == null) {
            return;
        }
        if (root.val == sum && root.left == null && root.right == null) {
            res02.add(path);
        }
        path.add(root.val);
        method02(root.left, sum - root.val, path);
        method02(root.right, sum - root.val, path);
        //撤销最后一个结点--回溯的最大特点
        path.remove(path.size() - 1);

    }
}
