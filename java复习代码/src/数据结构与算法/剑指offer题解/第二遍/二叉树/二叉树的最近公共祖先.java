package 数据结构与算法.剑指offer题解.第二遍.二叉树;


public class 二叉树的最近公共祖先 {
    //(1)当树为二叉搜索树时（入门版）
    public TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q){
//使用递归
        //递归结束的条件
        if (root == null || root.val == p.val || root.val == q.val){
            return root;
        }
        //使用递归求得左子树中p和q的最近公共祖先
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //因为是二叉搜索树，所以左子树的值都要小于根节点的值，而根节点的值又要小于右节点的值
        if (p.val < root.val && q.val < root.val){
            return left;
        }
        if (p.val > root.val && q.val > root.val){
            return right;
        }
        return root;
    }

    //(2)当为普通二叉树时（进阶版）
    public TreeNode lowestCommonAncestor02(TreeNode root,TreeNode p,TreeNode q){
        //同样是使用递归
        if (root == null || root.val == p.val || root.val == q.val){
            return root;
        }
        //一般情况
        TreeNode left = lowestCommonAncestor02(root.left, p, q);
        TreeNode right = lowestCommonAncestor02(root.right, p, q);
        if (left == null && right == null){
            return null;
        }
        if (left == null){
            return right;
        }
        if (right == null){
            return left;
        }
        return root;
    }

}
