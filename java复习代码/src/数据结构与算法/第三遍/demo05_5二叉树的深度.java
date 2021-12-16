package 数据结构与算法.第三遍;


import 数据结构与算法.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

class 二叉树的深度 {
    /**
     * 方法1：递归
     * @param root
     * @return
     */
    public int TreeDepth(TreeNode root) {
        //递归结束的出口
        if (root == null) {
            return 0;
        }
        int left = TreeDepth(root.left);//左子树的高度
        int right = TreeDepth(root.right);//左子树的高度
        int res = Math.max(left, right) + 1;
        return res;
    }

    /**
     * 方法2：非递归/迭代（面试推荐）
     * 其实和层序遍历思路相同
     */
        public int TreeDepth02(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int depth = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();//直接弹出，不用存储
                    //再放下一层节点
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
                //遍历完一层，深度就加1
                depth++;
            }
            return depth;
        }
}

/**
 * 思路一模一样
 */
class 最小深度 {
    public int Min_Depth(TreeNode root) {
        //递归结束的条件
        if (root == null) {
            return 0;
        }
        //左右深度
        int left = Min_Depth(root.left);
        int right = Min_Depth(root.right);

        //注意特殊情况
        if (root.left == null || root.right == null) {
            return left + right + 1;
        }
        return Math.min(left, right) + 1;
    }

}

