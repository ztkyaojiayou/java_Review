package 数据结构与算法.第三遍;


import 数据结构与算法.TreeNode;

//没太懂
//逻辑和求二叉树的直径思路类似，可参考
public class demo05_16二叉树中的最大路径和 {
    //置为最小值，因为和可能为负数，因为要随时刷新，因此需要定义为全局变量
    private int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        //递归
        maxSum(root);
        //返回最终结果
        return res;
    }

    //经过当前节点的最大路径和
    private int maxSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        /**
         Q：左右孩子贡献为什么要大于等于0？
         A: 因为计算从某一节点出发的路径和的时候，计算公式为： 当前节点值 + 左孩子贡献 + 右孩子贡献，
         而左右孩子贡献是「可选的」，也就是说当某一边贡献小于0的时候，我可以在计算路径和时不算这一边
         这种情况也就相当于其贡献为 0，但是注意路径和至少包含「当前节点的值」。
         **/
        int left_max = Math.max(maxSum(root.left), 0);//左孩子贡献，需要和0比较，小于0的话，就不要它
        int right_max = Math.max(maxSum(root.right), 0);//右孩子贡献，同理
        res = Math.max(res, left_max + right_max + root.val);// 更新res
        return Math.max(left_max, right_max) + root.val;// 返回当前节点的总贡献（只能选一边，选较大者）
    }


    //自写一遍
    public int maxPathSum02(TreeNode root) {
        //递归
        maxSum02(root);
        //返回最终结果
        //随时更新，刷新
        return res;
    }

    //经过当前节点的最大路径和
    private int maxSum02(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left_max = Math.max(maxSum02(root.left), 0);
        int right_max = Math.max(maxSum02(root.right), 0);
        res = Math.max(res, left_max + right_max + root.val);
        return Math.max(left_max, right_max) + root.val;
    }
}

