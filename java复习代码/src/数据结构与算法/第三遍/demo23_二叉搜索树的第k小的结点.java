package 数据结构与算法.第三遍;

import 数据结构与算法.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class demo23_二叉搜索树的第k小的结点 {
    List<TreeNode> list = new ArrayList<TreeNode>();
    public TreeNode method(TreeNode root,int k){
        //先中序遍历，把节点保存到list中，此时为递增序列
        inorder(root);
        //再去list中找第k-1个节点即为所求
        if (k>0 && list.size() >= k){
            return list.get(k-1);
        }
        return null;
    }

    private void inorder(TreeNode root) {
        if (root != null){
            inorder(root.left);
            list.add(root);
            inorder(root.right);
        }
    }
}
