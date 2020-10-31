package 数据结构与算法.第三遍;

import 数据结构与算法.TreeNode;

class demo24_二叉搜索树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //递归结束的条件
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //再判断，分三种情况即可
        //1.若这两个节点都在左边时，则其最近公共祖先也肯定在左边
        if (root.val > p.val && root.val > q.val) {
            return left;
        }
        //2.同理，若这两个节点都在右边时，则其最近公共祖先也肯定在右边
        if (root.val < p.val && root.val < q.val) {
            return right;
        }
        //3.而若处于一左一右时，则其最近公共祖先即为root节点
        return root;
    }
}

/**
 * 常考
 */
public class demo24_二叉树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //递归结束的条件
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
//递归找其左右子树中该两个节点的最近公共祖先
        TreeNode left_Ancestor = lowestCommonAncestor(root.left, p, q);
        TreeNode right_Ancestor = lowestCommonAncestor(root.right, p, q);
        //四种情况
        //1.若都没有，则说明不存在
        if (left_Ancestor == null && right_Ancestor == null) {
            return null;
        }
        //2.若左边没有，则肯定在右边
        if (left_Ancestor == null) {
            return right_Ancestor;
        }
        //3.若右边没有，则肯定在左边
        if (right_Ancestor == null) {
            return left_Ancestor;
        }
        //4.若左右都有，则即为root节点
        return root;
    }
}
