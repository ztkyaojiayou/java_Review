package 数据结构与算法.第三遍;

import 数据结构与算法.TreeNode;

public class demo05_把二叉搜索树转换为累加树 {
    public int sum;
    public TreeNode convertBST(TreeNode root) {
        sum = 0;
        method(root);
        return root;
    }

    //递归，反向中序遍历，同时累加即可
    public void method(TreeNode root) {
        //出口
        if (root == null) {
            return;
        }
        method(root.right);
        sum += root.val;
        root.val = sum;
        method(root.left);
    }
}
