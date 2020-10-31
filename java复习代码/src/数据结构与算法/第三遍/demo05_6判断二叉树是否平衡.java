package 数据结构与算法.第三遍;


import 数据结构与算法.TreeNode;


public class demo05_6判断二叉树是否平衡 {
    private boolean IsBalanced(TreeNode root){
        if (root == null){
            return false;
        }
        //调用了求二叉树深度的方法
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        int diff = left - right;
        if (diff > 1 || diff < -1){
            return false;
        }
        //每一个节点的左右子树的高度都要判断是否平衡，这一点很重要
        boolean res = IsBalanced(root.left) && IsBalanced(root.right);
        return res;
    }

    //求二叉树深度
    public int TreeDepth(TreeNode root){
        //使用递归
        if (root == null){
            return 0;
        }
        //左右子树的高度
        int left = TreeDepth(root.left);//左子树的高度
        int right = TreeDepth(root.right);//左子树的高度
        int res = Math.max(left, right) + 1;
        return res;
    }
}
