package 数据结构与算法.第三遍;

import 数据结构与算法.TreeNode;

//没太懂
public class demo20_二叉搜索树转化为双向链表 {
    TreeNode pre, head;//一个指向前一个节点，一个指向头结点
    public TreeNode treeToDoublyList( TreeNode root) {
        if (root == null){
            return null;
        }
        inorder(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void inorder(TreeNode cur) {
if (cur == null){
    return;
}
inorder(cur.left);
if (pre != null){
    pre.right = cur;
}else{
head = cur;
}
cur.left = pre;
pre = cur;//移动pre节点，进行下一个节点的处理
        inorder(cur.right);
    }
}
