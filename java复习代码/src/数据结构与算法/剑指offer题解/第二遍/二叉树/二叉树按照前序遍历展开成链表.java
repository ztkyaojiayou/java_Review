package 数据结构与算法.剑指offer题解.第二遍.二叉树;

public class 二叉树按照前序遍历展开成链表 {
    public void flatten(TreeNode root){
        if (root == null){
            return;
        }
        flatten(root.left);
        flatten(root.right);
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null){
            root = root.right;
        }
        root.right = temp;
    }
}
