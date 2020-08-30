package 数据结构与算法.剑指offer题解.第二遍.二叉树;

public class 二叉树的直径 {
 int res;
 public int zhiJin(TreeNode root){
     res = 0;
     traverse(root);
     return res;
 }

    private int traverse(TreeNode root) {

     if (root == null){
         return 0;
     }
        int left_deep = traverse(root.left);
        int right_deep = traverse(root.right);
        res = Math.max(res,left_deep+right_deep);
        return Math.max(left_deep,right_deep) + 1;
    }
}
