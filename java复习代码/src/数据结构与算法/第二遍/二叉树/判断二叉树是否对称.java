package 数据结构与算法.第二遍.二叉树;

import java.util.LinkedList;

public class 判断二叉树是否对称 {
    public boolean isDuiChShu(Node17 root){
        if (root == null){
            return true;
        }
        return isDuiCh(root,root);
    }
//递归方法
    private boolean isDuiCh(Node17 root1, Node17 root2) {
        if (root1 == null && root2 == null){
            return true;
        }
        if (root1 == null || root2 == null){
            return false;
        }
        if (root1.val != root2.val){
            return false;
        }
        boolean res = isDuiCh(root1.left,root2.right) && isDuiCh(root1.right,root2.left);
        return res;
    }
}

//非递归方法，使用队列，面试版
class Solution101 {
    public boolean isSymmetric(TreeNode root) {
        if(root==null || (root.left==null && root.right==null)) {
            return true;
        }
        //用队列保存节点（list本来就是一个特殊的队列，不要大惊小怪）
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        //将根节点的左右孩子放到队列中
        queue.add(root.left);
        queue.add(root.right);
        while(queue.size()>0) {
            //从队列中取出两个节点，再比较这两个节点
            TreeNode left = queue.removeFirst();
            TreeNode right = queue.removeFirst();
            //如果两个节点都为空就继续循环，两者有一个为空就返回false
            if(left==null && right==null) {
                continue;
            }
            if(left==null || right==null) {
                return false;
            }
            if(left.val!=right.val) {
                return false;
            }
            //将左节点的左孩子， 右节点的右孩子放入队列,
            //它们两个在队列中是相邻的，因为队列是先进先出的，再拿出来比较
            queue.add(left.left);
            queue.add(right.right);
            //再将左节点的右孩子，右节点的左孩子放入队列，同理。
            queue.add(left.right);
            queue.add(right.left);
        }

        return true;
    }
}
