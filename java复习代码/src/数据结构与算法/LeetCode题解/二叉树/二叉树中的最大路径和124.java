package 数据结构与算法.LeetCode题解.二叉树;

/**
 * 124. 二叉树中的最大路径和
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。
 * 该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1:
 * 输入: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 * 输出: 6
 *
 * 示例 2:
 * 输入: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出: 42
 */
class TreeNode124 {
    int val;
    TreeNode124 left;
    TreeNode124 right;
    TreeNode124(int x) { val = x; }
}

/**
 * （没太懂）
 * 该题说明的是，能够找到1个节点到另外一个节点的最大路径和，因此思路如下
 * 定义一个递归方法，该方法的功能是：得到当前树的最大的路径和，
 * 而计算当前的最大路径和是通过递归计算左右子树的最大路径好得到的。
 * 最终当前树的最大路径和为Math.max(current.val, Math.max(左子树最大路径和，右子树最大路径和));
 */
public class 二叉树中的最大路径和124 {
    private int max = Integer.MIN_VALUE;//置为最小值
    public int maxPathSum(TreeNode124 root) {
        //递归
        maxSum(root);
        return max;
    }
    private int maxSum(TreeNode124 root) {
        //通过后序遍历的方式，先计算出左右子树的最大路径和，然后再计算当前树的最大路径和
        if(null == root) {
            return 0;
        }
        int leftMax = Math.max(maxSum(root.left), 0);
        int rightMax = Math.max(maxSum(root.right), 0);
        max = Math.max(max, leftMax + rightMax + root.val);
        return root.val + Math.max(leftMax, rightMax);
    }
}
