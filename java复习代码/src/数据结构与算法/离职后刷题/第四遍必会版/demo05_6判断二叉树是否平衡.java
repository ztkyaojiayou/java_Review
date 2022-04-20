package 数据结构与算法.离职后刷题.第四遍必会版;
import 数据结构与算法.TreeNode;


public class demo05_6判断二叉树是否平衡 {
    private boolean IsBalanced(TreeNode root) {
        //先求当前根节点的左右子树的高度，判断其差是否小于等于1
        //调用了求二叉树深度的方法
        int left_depth = TreeDepth(root.left);
        int right_depth = TreeDepth(root.right);
        int diff = left_depth - right_depth;
        //递归出口
        if (diff > 1 || diff < -1) {
            return false;
        }
        //再判断每一个节点的左右子树的高度是否也符合平衡树，这一点很重要--递归即可
        boolean res = IsBalanced(root.left) && IsBalanced(root.right);
        return res;
    }

    //求二叉树深度
    public int TreeDepth(TreeNode root) {
        //使用递归
        if (root == null) {
            return 0;
        }
        //左右子树的高度/层数
        int left = TreeDepth(root.left);//左子树的高度/层数
        int right = TreeDepth(root.right);//左子树的高度/层数
        //深度
        int res = Math.max(left, right) + 1;
        return res;
    }


    //自写一遍
    private boolean IsBalanced02(TreeNode root) {
        int left_height = TreeDepth(root.left);
        int right_height = TreeDepth(root.right);
        //高度差
        int diff = left_height - right_height;
        //若高度差大于1，则不是平衡树
        if (diff > 1 || diff < -1){
            return false;
        }
        //否则还需要看其左右结点所构成的子树是否也符合平衡树
        boolean res = IsBalanced02(root.left) && IsBalanced02(root.right);
        return res;
    }
}
