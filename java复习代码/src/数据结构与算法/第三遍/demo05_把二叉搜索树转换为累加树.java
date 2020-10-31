package 数据结构与算法.第三遍;

import 数据结构与算法.TreeNode;

public class demo05_把二叉搜索树转换为累加树 {
    private int sum;

    public TreeNode convertBST(TreeNode root) {
        sum = 0;
        method(root);
        return root;
    }

    //递归
    private void method(TreeNode root) {
        //
        if (root == null) {
            return;
        }
        method(root.right);
        sum += root.val;
        root.val = sum;
        method(root.left);
    }
}
