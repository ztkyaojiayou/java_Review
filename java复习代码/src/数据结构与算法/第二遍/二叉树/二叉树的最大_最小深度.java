package 数据结构与算法.第二遍.二叉树;

public class 二叉树的最大_最小深度 {
    //（1）最大深度
    public int maxDepth(TreeNode root){
        if (root == null){
            return 0;
        }
        //求左右子树的最大深度
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        //再从左右子树的最大深度中再找一个较大的，在其基础上加1即为整个二叉树的最大深度
        int res = Math.max(left, right) + 1;
        return res;
    }

    //(2)最小深度
    public int minDepth(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (root.left == null || root.right == null){
            int res = left + right + 1;
            return res;
        }
        int res = Math.min(left, right) + 1;
        return res;
    }

}
