package 数据结构与算法.剑指offer题解.第二遍.二叉树;

import java.util.LinkedList;

public class 翻转二叉树 {
    //使用递归
    public TreeNode invertTree01(TreeNode root){
        if (root == null){
            return null;
        }
        //交换当前节点的左右节点
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
      invertTree01(root.left);
      invertTree01(root.right);
      return root;
    }

    //使用非递归，借用队列
    public TreeNode invertTree02(TreeNode root){
        if (root == null){
            return null;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;
            if (cur.left != null){
                queue.add(cur.left);
            }
            if (cur.right != null){
                queue.add(cur.right);
            }
        }
        return root;
    }
}
