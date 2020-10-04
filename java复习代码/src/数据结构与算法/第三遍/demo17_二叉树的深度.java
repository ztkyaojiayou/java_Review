package 数据结构与算法.第三遍;


import 数据结构与算法.TreeNode;

public class demo17_二叉树的深度 {
    public int TreeDepth(TreeNode root){
        //使用递归
        if (root == null){
            return 0;
        }
        int left = TreeDepth(root.left);//左子树的高度
        int right = TreeDepth(root.right);//左子树的高度
        int res = Math.max(left, right) + 1;
        return res;
    }
}

class 最小深度{
    public int Min_Depth(TreeNode root){
        //递归结束的条件
        if (root == null){
            return 0;
        }
        //左右深度
        int left = Min_Depth(root.left);
        int right = Min_Depth(root.right);
        if (root.left == null || root.right == null){
            return left + right + 1;
        }
        return Math.min(left,right) + 1;
    }

}

