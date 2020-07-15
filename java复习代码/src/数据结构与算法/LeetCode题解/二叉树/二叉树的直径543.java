package 数据结构与算法.LeetCode题解.二叉树;

import 数据结构与算法.LeetCode题解.TreeNode;

/**
 * 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。
 * 其中，一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
 * 这条路径可能穿过也可能不穿过根结点。
 *
 * 示例 :
 * 给定二叉树
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 */

/**
 * 解析：
 * 任意两个节点之间的边数都可能是最大直径
 * 最大的直径不一定包括根节点
 * 这道题很容易有的误区就是：从根节点出发，找到左边数的最大深度 leftDepth，再找到右边树的最大深度 rigthDepth
 * 然后 return leftDepth+rigthDepth + 1（如果二叉树的根节点深度为0的话）
 *
 * 解题思路
 * 从上面的分析可知，最大值不一定包含根节点，
 * 但是一定是：经过一个节点，该节点左右子树的最大深度之和 +1+1（二叉树的根节点深度为 00）
 * 于是，可以使用 DFS，找出所有节点的最大直径，在取出最大值 res;
 *
 * 定义一个全局变量 res，用来记录最大直径
 * 使用 dfs(root) 遍历所有的节点，dfs(root) 的作用是：找出以 root 为根节点的二叉树的最大深度
 *
 * 将根节点的深度定义为 1（和上面分析的深度定义不一样）
 * root 为跟节点的最大深度为 Math.max(leftDepth,rigthDepth) + 1
 * res 取值为以经过 root，左右子树的最大深度之和 leftDepth + rigthDepth（不用加 1，是因为根节点的深度是 1）
 * 通过递归，找到 res 的最大值
 */
public class 二叉树的直径543 {
    //定义一个全部变量，用于存储结果
    int result;

    public int diameterOfBinaryTree(TreeNode root) {
        result = 0;
        //开始调用递归函数
        traverse(root);
        //返回结果值，
        return result;
    }
    // 递归函数的具体实现
    // 作用：用于返回以当前节点为根节点的树的深度
    int traverse(TreeNode root) {
        //1.递归结束的条件，即当为空节点时，深度定义为0
        if (root == null) {
            return 0;
        }
        //2.开始下一轮递归，分别求出当前节点的左子树和右子树的深度
        int left_deep = traverse(root.left); // 左子树的深度
        int right_deep = traverse(root.right); // 右子树的深度
        //3.直接访问全局变量，更新结果值
        result = Math.max(result, left_deep + right_deep);//这就是结果
        return 1 + Math.max(left_deep, right_deep);//这并不是结果，而只是返回以当前节点为根节点的树的深度
    }
    }