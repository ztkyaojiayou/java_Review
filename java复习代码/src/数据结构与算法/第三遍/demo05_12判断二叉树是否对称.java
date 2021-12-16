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
        if (root1.val == root2.val){
            return true;
        }
        boolean outside = method(root1.left, root2.right);
        boolean inside = method(root1.right, root2.left);
        return outside && inside;
    }
}
