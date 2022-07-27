package 数据结构与算法.LeetCode题解.二叉树;

import java.util.LinkedList;

/**
 * 101. 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *
 * 进阶：
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 */

  //Definition for a binary tree node.
  class TreeNode101 {
      int val;
      TreeNode101 left;
      TreeNode101 right;
      TreeNode101(int x) { val = x; }
  }

/**
 * 同剑指offer第13题
 * 方法1：使用递归
 * （1）如果同时满足下面的条件，两个树互为镜像：
 * 它们的两个根结点值相同
 * 每个树的右子树都与另一个树的左子树镜像对称
 *
 * （2）递归结束条件：
 * 都为空指针则返回 true
 * 只有一个为空则返回 false
 *
 * （3）递归过程：
 * 判断两个指针当前节点值是否相等
 * 判断 A 的右子树与 B 的左子树是否对称
 * 判断 A 的左子树与 B 的右子树是否对称
 *
 * （4）短路：
 * 在递归判断过程中存在短路现象，也就是做 与 操作时，
 * 如果前面的值返回 false 则后面的不再进行计算
 *
 * 时间复杂度：O(n)
 */
public class 对称二叉树101 {
        public boolean isSymmetric(TreeNode101 root) {
            //特判
            if (root == null) {
                return true;
            }
            //使用递归
            return isMirror(root.left, root.right);
        }
        private boolean isMirror(TreeNode101 r1, TreeNode101 r2) {
            if (r1 == null && r2 == null) {
                return true;
            }
            if (r1 == null || r2 == null) {
                return false;
            }
            //若这两个结点（的值）都不相等，则也肯定不对称
            if (r1.val != r2.val ) {
                return false;
            }
            //只有这三个条件都满足时才返回true
            return (r1.val == r2.val) && isMirror(r1.left, r2.right) && isMirror(r1.right, r2.left);
        }
    }

/**
 * 方法2：使用队列进行迭代实现
 * （1）回想下递归的实现：
 * 当两个子树的根节点相等时，就比较:
 * 左子树的left 和 右子树的right，这个比较是用递归实现的。
 *
 * （2）现在我们改用队列来实现，思路如下：
 * 首先从队列中拿出两个节点(left和right)比较
 * 将left的left节点和right的right节点放入队列，再比较
 * 将left的right节点和right的left节点放入队列，再比较
 * 时间复杂度是O(n)，空间复杂度是O(n)
 */
class Solution101 {
    public boolean isSymmetric(TreeNode101 root) {
        if(root==null || (root.left==null && root.right==null)) {
            return true;
        }
        //用队列保存节点（list本来就是一个特殊的队列，不要大惊小怪）
        LinkedList<TreeNode101> queue = new LinkedList<TreeNode101>();
        //将根节点的左右孩子放到队列中
        queue.add(root.left);
        queue.add(root.right);
        while(queue.size()>0) {
            //从队列中取出两个节点，再比较这两个节点
            TreeNode101 left = queue.removeFirst();
            TreeNode101 right = queue.removeFirst();
            //如果两个节点都为空就继续循环，两者有一个为空就返回false
            if(left==null && right==null) {
                continue;
            }
            if(left==null || right==null) {
                return false;
            }
            if(left.val!=right.val) {
                return false;
            }
            //将左节点的左孩子， 右节点的右孩子放入队列,
            //它们两个在队列中是相邻的，因为队列是先进先出的，再拿出来比较
            queue.add(left.left);
            queue.add(right.right);
            //再将左节点的右孩子，右节点的左孩子放入队列，同理。
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }
}
