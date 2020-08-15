package 数据结构与算法.剑指offer题解.第二遍.二叉树;

public class 判断二叉树是否对称 {
    public boolean isDuiChShu(Node17 root){
        if (root == null){
            return true;
        }
        return isDuiCh(root,root);
    }

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
