package 数据结构与算法.第二遍.二叉树;

import java.util.Stack;

public class 判断是否为二叉搜索树 {
    //思路：中序遍历，边遍历边比较
    public boolean isValidBST(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        int inorder = - Integer.MAX_VALUE;
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            TreeNode temp = stack.pop();
            if (temp.val <= inorder){//核心代码
                return false;
            }
            inorder = temp.val;
            root = temp.right;
        }
        return true;
    }
}
