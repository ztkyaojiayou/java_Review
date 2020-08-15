package 数据结构与算法.剑指offer题解.第二遍.二叉树;

import java.util.ArrayList;

public class 二叉搜索树的第k小的节点 {
    ArrayList<TreeNode> list = new ArrayList<>();
    public TreeNode kNode(TreeNode root,int k){
        //中序遍历，并把结果存入list中
        inOrder(root);
        //再取值即可
        if (k>=0 && list.size()>=k){
            TreeNode res = list.get(k - 1);
            return res;
        }
        return null;
    }
//中序遍历的具体实现
    private void inOrder(TreeNode root) {
        if (root.left != null){
            inOrder(root.left);
            list.add(root);
            if (root.right != null){
                inOrder(root.right);
            }
        }
    }
}
