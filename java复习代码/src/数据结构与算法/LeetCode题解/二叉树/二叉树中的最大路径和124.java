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
        //返回最终结果
        return max;
    }
    //具体的递归函数
    private int maxSum(TreeNode124 root) {
        //通过后序遍历的方式，先计算出左右子树的最大路径和，然后再计算当前树的最大路径和
        if(null == root) {
            return 0;
        }
        /**
         Q：左右孩子贡献为什么要大于等于0？
         A: 因为计算从某一节点出发的路径和的时候，计算公式为： 当前节点值 + 左孩子贡献 + 右孩子贡献，
         而左右孩子贡献是「可选的」，也就是说当某一边贡献小于0的时候，我可以在计算路径和时不算这一边
         这种情况也就相当于其贡献为 0，但是注意路径和至少包含「当前节点的值」。
         **/
        int leftMax = Math.max(maxSum(root.left), 0);// 左孩子贡献
        int rightMax = Math.max(maxSum(root.right), 0);// 右孩子贡献
        max = Math.max(max, leftMax + rightMax + root.val);// 更新res
        return root.val + Math.max(leftMax, rightMax);// 返回当前节点的总贡献，要注意的是，在左右孩子的贡献值中只能选择一个（选较大值），因为路径节点不可重复
    }
}
