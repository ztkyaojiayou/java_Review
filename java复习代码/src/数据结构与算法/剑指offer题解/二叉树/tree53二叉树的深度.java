package 数据结构与算法.剑指offer题解.二叉树;

/**
 * 题目一：输入一棵二叉树的根结点，求该树的深度。
 * 从根结点到叶子点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 *
 * 【解】：递归左右子结点即可，简单
 */

class TreeNode53 {
    int val = 0;
    TreeNode54 left = null;
    TreeNode54 right = null;

    public TreeNode53(int val) {
        this.val = val;
    }
}
public class tree53二叉树的深度 {
    public static int TreeDepth(TreeNode54 root){
        if (root==null){
            return 0;
        }
        int left = TreeDepth(root.left);//左子树的深度
        int right = TreeDepth(root.right);//右子树的深度
        return Math.max(left,right)+1;//树的深度=路径最长的子树深度 + 1
        //return left>right? left+1: right+1;//不需要这么写
    }
}
