package 数据结构与算法.剑指offer题解.二叉树;

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
    private TreeNode41 pre = null;//指向当前节点的前一个结点
    private TreeNode41 head = null;//头结点

    public TreeNode41 Convert(TreeNode41 root) {
        inOrder(root);
        return head;//对于链表，返回头结点即可
    }

    private void inOrder(TreeNode41 node) {
        if (node == null)
            return;
        inOrder(node.left);//向左递归
        node.left = pre;
        if (pre != null)
            pre.right = node;
        pre = node;
        if (head == null)
            head = node;
        inOrder(node.right);//向右递归
    }
}
