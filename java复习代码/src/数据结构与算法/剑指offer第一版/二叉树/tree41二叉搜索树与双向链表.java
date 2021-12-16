package 数据结构与算法.剑指offer第一版.二叉树;

import 数据结构与算法.TreeNode;

/**
 * （没懂）题目：输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *
 * 【解】：
 *  利用中序遍历和二叉排序树的特点，使用递归完成
 * （1）二叉搜索树（也即二叉排序树）的特点为：左节点的值<根节点的值<右节点的值
 * （2）则不难发现，根据中序遍历的特点，对二叉搜索树进行中序遍历之后，其序列即为顺序序列，满足题意
 *
 */

 class TreeNode41 {
    int val = 0;
    TreeNode41 left = null;
    TreeNode41 right = null;

    public TreeNode41(int val) {
        this.val = val;

    }

}

public class tree41二叉搜索树与双向链表 {
    TreeNode pre, head;//一个指向前一个节点，一个指向头结点
    public  TreeNode treeToDoublyList( TreeNode root) {
        if(root == null) return null;
        inOrder(root);
        //构造循环链表，首尾相连即可
        head.left = pre;
        pre.right = head;
        return head;
    }
    void inOrder(TreeNode cur) {//中序遍历
        if(cur == null) return;
        inOrder(cur.left);//向左递归
        if(pre != null) {//说明此时不是头结点
            pre.right = cur;//关键代码1
        } else {//说明此时是头结点
            head = cur;
        }
        cur.left = pre;//关键代码2
        pre = cur;//保存当前节点，//关键代码3
        inOrder(cur.right);//向右递归
    }
}

