package 数据结构与算法.离职后刷题.第四遍必会版;

import 数据结构与算法.TreeNode;

//没太懂
public class demo15_18二叉搜索树转化为双向链表_没懂 {
    //一个指向前一个节点，一个指向头结点
    TreeNode pre, head;

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }
        inorder(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void inorder(TreeNode cur) {
        if (cur == null) {
            return;
        }
        inorder(cur.left);
        if (pre != null) {
            pre.right = cur;
        } else {
            head = cur;
        }
        cur.left = pre;
        //移动pre节点，进行下一个节点的处理
        pre = cur;
        inorder(cur.right);
    }
}
