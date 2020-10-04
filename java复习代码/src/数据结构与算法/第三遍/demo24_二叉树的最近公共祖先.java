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
        if (root.val > p.val && root.val > q.val) {
            return left;
        }
        if (root.val < p.val && root.val < q.val) {
            return right;
        }
        return root;
    }
}


public class demo24_二叉树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //递归结束的条件
if (root == null || root.val == p.val || root.val == q.val){
    return root;
}
//递归找其左右子树中该两个节点的最近公共祖先
        TreeNode left_Ancestor = lowestCommonAncestor(root.left, p, q);
        TreeNode right_Ancestor = lowestCommonAncestor(root.right, p, q);
        if (left_Ancestor == null &&right_Ancestor == null){
            return null;
        }
        if (left_Ancestor == null){
            return right_Ancestor;
        }
        if (right_Ancestor == null){
            return left_Ancestor;
        }
        return root;
    }
}
