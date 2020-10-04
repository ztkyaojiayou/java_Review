package 数据结构与算法.第二遍.二叉树;

public class 求一棵树的深度 {
    public int depth(TreeNode root){
        if (root == null){
            return 0;
        }
        //树的深度=路径最长的子树深度 + 1
        int left = depth(root.left);
        int right = depth(root.right);
        int depth = Math.max(left,right)+1;
        return depth;
    }
}
