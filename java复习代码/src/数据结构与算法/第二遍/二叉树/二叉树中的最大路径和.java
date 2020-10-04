package 数据结构与算法.第二遍.二叉树;

public class 二叉树中的最大路径和 {
    private int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root){
        maxSum(root);
        return res;
    }

    private int maxSum(TreeNode root) {
        if (root == null){
            return 0;
        }
        int left_max = Math.max(0, maxSum(root.left));
        int right_max = Math.max(0, maxSum(root.right));
        res = Math.max(res,left_max + root.val + right_max);
        return root.val + Math.max(left_max,right_max);
    }
}
