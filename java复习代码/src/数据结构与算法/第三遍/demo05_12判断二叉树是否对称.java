package 数据结构与算法.第三遍;

import 数据结构与算法.TreeNode;

public class demo05_12判断二叉树是否对称 {
    public boolean isSymmetrical(TreeNode root) {//输入一个结点，把其看成是根节点
        //直接调用递归即可
        return method(root, root);
    }

    //递归函数
    private boolean method(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        //如果值相等，则需要再比较其左右子节点是否也对称相等
        boolean outside = method(root1.left, root2.right);
        boolean inside = method(root1.right, root2.left);
        return outside && inside;
    }


    //自写一遍
    public boolean isSymmetrical02(TreeNode root) {//输入一个结点，把其看成是根节点
        //直接调用递归即可，root结点就当传入两个即可
        return method02(root, root);
    }

    //递归函数
    private boolean method02(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        //如果值相等，则需要再比较其左右子节点是否也对称相等
        return method02(root1.left, root2.right) && method02(root1.right, root2.left);
    }
}
