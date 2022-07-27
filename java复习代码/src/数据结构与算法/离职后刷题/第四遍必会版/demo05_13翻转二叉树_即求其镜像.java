package 数据结构与算法.离职后刷题.第四遍必会版;

import 数据结构与算法.TreeNode;

/**
 * 即求该树的镜像
 */
public class demo05_13翻转二叉树_即求其镜像 {
    public TreeNode invertTree(TreeNode root) {
        //递归结束的条件
        if (root == null) {
            return null;
        }
        //翻转当前节点的左右节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        //再递归反转当前节点的左右节点的左右节点
        invertTree(root.left);
        invertTree(root.right);

        //最后返回根节点即可（因为其左右节点以及其他节点都已经通过递归的方式反转啦）
        return root;
    }

    //自写一遍
    public TreeNode invertTree02(TreeNode root) {
        //递归出口
        if (root == null) {
            return null;
        }
        //反转其左右结点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        //再递归反转当前节点的左右节点的左右节点
        invertTree02(root.left);
        invertTree02(root.right);

        //最后返回根节点即可
        return root;
    }
}
