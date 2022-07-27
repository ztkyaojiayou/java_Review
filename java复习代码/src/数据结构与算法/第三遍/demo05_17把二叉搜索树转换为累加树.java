package 数据结构与算法.第三遍;

import 数据结构与算法.TreeNode;

public class demo05_17把二叉搜索树转换为累加树 {
    public int sum;

    public TreeNode convertBST(TreeNode root) {
        sum = 0;
        method(root);
        return root;
    }

    //递归，反向中序遍历，同时累加即可（非常清晰）
    public void method(TreeNode root) {
        //出口
        if (root == null) {
            return;
        }
        method(root.right);//右
        //中
        sum += root.val;
        root.val = sum;
        method(root.left);//左
    }

    //自写一遍
    public int sum01;

    public TreeNode convertBST02(TreeNode root) {
        if (root == null) {
            return root;
        }
        //反向中序遍历
        convertBST02(root.right);
        //累加
        sum01 += root.val;
        //再赋给root结点
        root.val = sum01;
        convertBST02(root.left);
        return root;
    }
}
