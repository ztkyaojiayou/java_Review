package 数据结构与算法.离职后刷题.第四遍必会版;

import 数据结构与算法.TreeNode;

/**
 * @author :zoutongkun
 * @date :2022/4/9 12:37 上午
 * @description :
 * @modyified By:
 */


/**
 * 114. 二叉树展开为链表
 * 给定一个二叉树，原地将它展开为一个单链表,顺序与前序遍历相同。
 * <p>
 * 例如，给定二叉树
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * <p>
 * 将其展开为：
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 */
public class demo05_19二叉树按照前序遍历展开成链表 {

    /**
     * 解析：使用递归
     * 其实是分为三步：
     * (1)首先将根节点的左子树变成链表
     * (2)其次将根节点的右子树变成链表
     * (3)最后将链表的右子树放在变成链表的左子树的最右边
     * <p>
     * 这就是一个递归的过程，递归的一个非常重要的点就是：
     * 不去管函数的内部细节是如何处理的，我们只看其函数作用以及输入与输出。对于函数flatten来说：
     * <p>
     * 函数作用：将一个二叉树，原地将它展开为链表
     * 输入：树的根节点
     * 输出：无
     * 那我们就直接根据三步来写程序就可以了
     * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/114-er-cha-shu-zhan-kai-wei-lian-biao-by-ming-zhi-/
     */
    public void flatten(TreeNode root) {
        //0.递归结束的条件
        if (root == null) {
            return;
        }
        //1.先将根节点的左子树变成链表
        flatten(root.left);

        //2.1再将根节点的右子树变成链表，并先保存/挂在一个临时的节点下，
        //等左边的链表挂在树的右边之后，再把该右链表挂在最后面即可
        flatten(root.right);
        //2.2临时保存，用于挂在左子树所形成的链表后面
        TreeNode temp = root.right;

        //3.开始构造链表（下面两步不能颠倒）
        //3.1先把根节点的左子树所形成的链表挂在当前根节点的右边
        root.right = root.left;
        //3.2同时记得要将当前根节点的左结点置空（以保证为一个链表，切记）
        root.left = null;
        //3.3再找到树的最右边的节点，用于连接根节点的右子树所形成的链表（即temp）
        while (root.right != null) {
            //3.3.1只要不是空节点，就一直向右遍历，直到找到最右边那个节点
            root = root.right;
        }
        //3.3.2把右边的链表接到刚才树的最右边的节点
        root.right = temp;
    }


    //自写一遍
    public void flatten02(TreeNode root) {
        //递归出口
        if (root == null) {
            return;

        }
        //先将左子树构建成链表，先连接到root结点上
        flatten02(root.left);
        //将右子树构建成链表，先待定
        flatten02(root.right);
        TreeNode temp = root.right;
        //开始构建链表
        root.right = root.left;
        root.left = null;
        //找到链表的最后一个结点
        while (root.right != null) {
            root = root.right;
        }
        //此时的root已经是链表的最后一个结点啦，只需连接上原root结点的右子树所构成的链表（即temp）即可
        root.right = temp;
    }
}
