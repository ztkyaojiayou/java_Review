package 数据结构与算法.剑指offer题解.第二遍.二叉树;

public class 判断二叉树是否平衡{
public boolean isBalanced(TreeNode root){
    if (root == null){
        return true;
    }
    //判断依据：左子树与右子树的深度差值小于1就表示该二叉树是平衡的
    int left = depth(root.left);//左子树的深度
    int right = depth(root.right);//右子树的深度
    int diff = Math.abs(left -right);//差值
    if (diff > 1){//大于1则表示不平衡
        return false;
    }
    //而若当前节点平衡，则还要判断其左右子树是否也是平衡树，
    // 因为二叉平衡树是要求任何两颗子树的高度差都小于1
    return isBalanced(root.left)&&isBalanced(root.right);
}

//求一棵树的深度
    private int depth(TreeNode node) {
    if (node == null){
        return 0;
    }
    int left = depth(node.left);
    int right = depth(node.right);
    int depth = Math.max(left,right) + 1;
        return depth;
    }

}
