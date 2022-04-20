package 数据结构与算法.离职后刷题.第四遍必会版;

import 数据结构与算法.TreeNode;

public class demo05_15求二叉树的直径 {
    //定义一个全局的结果变量
    int res;

    public int diameterOfBinaryTree(TreeNode root) {
        res = 0;
        //直接调用递归方法即可
        method(root);
        return res;
    }

    //递归方法
    private int method(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left_depth = method(root.left);
        int right_depth = method(root.right);
        //更新res--核心
        res = Math.max(res, left_depth + right_depth);
        return Math.max(left_depth, right_depth) + 1;
    }

    //自写一遍
    public int diameterOfBinaryTree02(TreeNode root) {
        res = 0;
        method02(root);
        return res;
    }

    private int method02(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left_depth = method02(root.left);
        int right_depth = method02(root.right);
        res = Math.max(this.res, left_depth + right_depth);
        return Math.max(left_depth,right_depth)+1;
    }
}
