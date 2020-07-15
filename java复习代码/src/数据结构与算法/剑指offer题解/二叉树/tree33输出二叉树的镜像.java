package 数据结构与算法.剑指offer题解.二叉树;

/**
 * 题目：请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 *
 *
 * 【思路】对比题13：即对称的二叉树
 *  1.首先明确：镜像就是将“根”节点的左右两个“子”结点互换，类似于数组的元素交换（运用临时结点temp）
 *  2.具体方法：递归
 *    (1)先序遍历这棵树的每个结点，若遍历到的结点有子结点，就交换它的两个子结点。
 *    (2)当交换完所有非叶子结点的左右子结点之后，就得到了树的镜像。
 */
class TreeNode33 {
    int val = 0;
    TreeNode33 left = null;
    TreeNode33 right = null;

    public TreeNode33(int val) {
        this.val = val;

    }

}

public class tree33输出二叉树的镜像 {
    public void Mirror(TreeNode33 root) {
        if (root == null)
            return;

        //1.自己写一个交换方法swap，用于交换“根”节点(根节点是相对的）的左右子节点
        swap(root);
        //2.再使用递归思想，逐个交换其左右子节点的子节点
        Mirror(root.left);
        Mirror(root.right);
    }
//这是交换方法的具体实现，类似交换两个普通元素的值，也要使用一个临时结点temp
    private void swap(TreeNode33 root) {
        TreeNode33 temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
}
