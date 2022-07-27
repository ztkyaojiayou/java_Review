package 数据结构与算法.离职后刷题.第四遍必会版;

import 数据结构与算法.TreeNode;

//对比“二叉树的最大路径和”，该题其实可以改为“二叉树的最大深度和”
// 即：求先每一个结点的左右子树的深度之和，其最大值即为所求，也即为直径
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
        //不用加 1，是因为根节点的深度是 1，已经在求深度的时候体现了！！！
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
        //递归出口
        if (root == null) {
            return 0;
        }
        //以左节点为根节点的树所能贡献的直径
        int left_depth = method02(root.left);
        //以右节点为根节点的树所能贡献的直径
        int right_depth = method02(root.right);

        //就比求深度多了这一行代码！！！
        res = Math.max(res, left_depth + right_depth);

        return Math.max(left_depth, right_depth) + 1;
    }
}
