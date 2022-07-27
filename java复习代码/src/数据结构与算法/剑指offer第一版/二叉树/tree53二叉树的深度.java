package 数据结构与算法.剑指offer第一版.二叉树;

/**
 * 题目一：输入一棵二叉树的根结点，求该树的深度。
 * 树的深度：二叉树的深度为根节点到最远叶子节点的最长路径上的节点数（易知，要比层数多1）。
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
        //递归结束的条件
        if (root==null){
            return 0;
        }
        int left = TreeDepth(root.left);//左子树的深度
        int right = TreeDepth(root.right);//右子树的深度
        return Math.max(left,right)+1;//树的深度=路径最长的子树深度 + 1（因为根节点也占一个高度）
    }
}
