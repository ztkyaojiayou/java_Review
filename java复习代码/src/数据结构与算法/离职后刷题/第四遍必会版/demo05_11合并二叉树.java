package 数据结构与算法.离职后刷题.第四遍必会版;

import 数据结构与算法.TreeNode;

public class demo05_11合并二叉树 {
    //就在第一颗树上进行合并即可，递归合并
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        //递归结束的条件（出口）
        //若有一棵树对应的结点为空，则就取另一棵树对应的结点即可
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }

        //若均不为空，则二者对应的值相加，即为合并
        t1.val += t2.val;

        //再同样地，通过递归来连接左右节点（左和左合并，右和右合并）
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);

        //返回t1节点即可
        return t1;
    }


    //自写一遍
    public TreeNode mergeTrees02(TreeNode t1, TreeNode t2) {
        //递归出口--也是特例
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }

        //若均不为空，则先把对应的值加起来
        t1.val += t2.val;
        //再去递归连接其左右子节点
        t1.left = mergeTrees02(t1.left, t2.left);
        t1.right = mergeTrees02(t1.right, t2.right);
        //最后返回t1即可，即为合并后的新树的根节点
        return t1;
    }
}
