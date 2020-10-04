package 数据结构与算法.第三遍;

import 数据结构与算法.TreeNode;

public class demo54_翻转二叉树 {
    public TreeNode invertTree(TreeNode root) {
        //递归结束的条件
        if (root == null){
            return null;
        }

        //反转当前节点的左右节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        //再递归反转当前节点的左右节点的左右节点
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
