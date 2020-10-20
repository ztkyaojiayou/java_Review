package 数据结构与算法.第三遍;


import 数据结构与算法.TreeNode;

//没太懂
public class demo25_二叉树中的最大路径和 {
    private int res = Integer.MIN_VALUE;//置为最小值

    public int maxPathSum(TreeNode root) {
        //递归
        maxSum(root);
        //返回最终结果
        return res;
    }

    //经过当前节点的最大路径和
    private int maxSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left_max = Math.max(maxSum(root.left), 0);
        int right_max = Math.max(maxSum(root.right), 0);
        res = Math.max(res, left_max + right_max + root.val);
        return Math.max(left_max, right_max) + root.val;
    }
}

