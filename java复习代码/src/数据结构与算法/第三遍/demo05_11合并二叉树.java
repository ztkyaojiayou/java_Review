package 数据结构与算法.第三遍;

import 数据结构与算法.TreeNode;

public class demo05_11合并二叉树 {
    //就在第一颗树上进行合并即可，递归合并
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        //递归结束的条件（出口）
        if (t1 == null){
            return t2;
        }
        if (t2 == null){
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
}
