package 数据结构与算法.第三遍;

import 数据结构与算法.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class demo05_8二叉搜索树的第k小的结点 {
    List<TreeNode> list = new ArrayList<>();//全局变量

    public TreeNode method(TreeNode root, int k) {
        //先中序遍历，把节点保存到list中，此时为递增序列
        inorder(root);
        //再去list中找第k-1个节点即为所求
        if (k > 0 && list.size() >= k) {
            return list.get(k - 1);
        }
        return null;
    }

    //中序遍历，结果存入list中
    private void inorder(TreeNode root) {
        //递归结束的条件
        if (root == null) {
            return;
        }
        inorder(root.left);
        list.add(root);
        inorder(root.right);
    }


    //自写一遍
    //存的是结点（而不是值val）
    List<TreeNode> list02 = new ArrayList<>();//全局变量

    public TreeNode method02(TreeNode root, int k) {
        //中序遍历后就成了升序序列，此时就可以直接取目标值了
        inorder02(root);
        if (k > 0 && list02.size() > k) {
            return list02.get(k - 1);
        }
        return null;
    }

    //中序遍历，结果存入list中
    private void inorder02(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder02(root.left);
        list.add(root);
        inorder02(root.right);
    }
}
