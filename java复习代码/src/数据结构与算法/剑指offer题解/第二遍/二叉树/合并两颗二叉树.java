package 数据结构与算法.剑指offer题解.第二遍.二叉树;

public class 合并两颗二叉树 {
    public TreeNode mergeTrees(TreeNode root1,TreeNode root2){
        //递归结束的条件
        if (root1 == null){
            return root2;
        }
        if (root2 == null){
            return root1;
        }
        //一般情况，即若两棵树上的当前节点都不为空时
        //注意：这里我们是把第二棵树合并在第一棵树上
        //合并当前值
root1.val += root2.val;
        //并把当前节点的左右节点也通过递归的方式安排好
        root1.left = mergeTrees(root1.left,root2.left);
        root1.right = mergeTrees(root1.right,root2.right);
        //最后，返回合并后的树的根节点即可
        return root1;
    }
}
