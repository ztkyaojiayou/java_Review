package 数据结构与算法.LeetCode题解.二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层序遍历(从上到下输出版）（简单）
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。
 * （即逐层地，从左到右访问所有节点）。
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 返回其层次遍历结果：(一个大的list中存了多个小list）
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
//Definition for a binary tree node.
class TreeNode102 {
    int val;
    TreeNode102 left;
    TreeNode102 right;
    TreeNode102(int x) { val = x; }
}

/**
 * 解析：使用广度优先遍历
 * 广度优先遍历是按层层推进的方式，遍历每一层的节点。题目要求的是返回每一层的节点值，所以这题用广度优先来做非常合适。
 * 广度优先需要用队列作为辅助结构，我们先将根节点放到队列中，然后不断遍历队列。,比如：
 *      1
 *     / \
 *    2  5
 *   / \  \
 *  3  4   7
 * （1）首先拿出根节点1，此时如果左子树/右子树不为空，就将他们放入队列中。
 * （2）第一遍处理完后，根节点已经从队列中拿走了，而根节点的两个孩子已放入队列中了，现在队列中就有两个节点 2 和 5。
 * （3）第二次处理，会将 2 和 5 这两个节点从队列中拿走，然后再将 2 和 5 的子节点放入队列中，现在队列中就有三个节点 3，4，6。
 * （4）我们把每层遍历到的节点都放入到一个结果集中，最后返回这个结果集就可以啦~
 * 时间复杂度： O(n)
 * 空间复杂度：O(n)
 */
public class 二叉树的层序遍历102 {
    public List<List<Integer>> levelOrder(TreeNode102 root) {
        //特判
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        //结果集list
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        //保存每一层节点的队列
        LinkedList<TreeNode102> queue = new LinkedList<TreeNode102>();
        //将根节点放入队列中，然后不断遍历队列
        queue.add(root);
        while (queue.size() > 0) {
            //获取当前队列的长度，这个长度相当于 当前这一层的节点个数
            int size = queue.size();
            //临时list，用于保存每一层的节点
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            //将队列中的所有元素都拿出来(也就是获取这一层的节点)，放到临时list中（并删除它们，使用remove方法即可）
            //如果当前这些节点（也就是这一层的所有节点）的左/右子树不为空，就把它们放入队列中
            //（此时队列中存的就是下一层的节点啦，当前层的节点已经被拿出来并删除啦）
            for (int i = 0; i < size; ++i) {//每循环一次，就处理一个节点，即就把该节点放入临时list中，同时把其左右节点放入队列中
                TreeNode102 t = queue.remove();//取出并删除该节点
                tmp.add(t.val);//取出后放入临时list中
                //再把其（每个节点）左右子节点放入队列中，此时队列中存放的就是下一层的所有节点啦~
                if (t.left != null) {
                    queue.add(t.left);
                }
                if (t.right != null) {
                    queue.add(t.right);
                }
            }
            //将临时list加入到最终的返回结果集中
            results.add(tmp);
        }
        return results;
    }
}

/**
 * 107. 二叉树的层次遍历 II（从下到上输出版）（简单）
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。
 * （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */

/**
 * 本题和 102 题基本一致，区别就是每层的结点在二维数组中存放的先后顺序不同
 * 前面所有代码都和 102 题一样，只需将最后存放位置每次都从 0 开始放就可以了
 * result.add(temp) 改成 result.add(0, temp),
 * 即因为是逆序的，所以我们将新的list加到index为0的地方即可。
 * 具体区别步骤写在代码注释处。
 */
class Solution102 {
    public List<List<Integer>> levelOrderBottom(TreeNode102 root) {
        List<List<Integer>> result = new LinkedList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode102> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> curLevel = new LinkedList<>();
            int levelSize = queue.size();
            for(int i = 0; i < levelSize; i++){
                TreeNode102 curNode = queue.poll();
                curLevel.add(curNode.val);
                if(curNode.left != null){
                    queue.add(curNode.left);
                }
                if(curNode.right != null){
                    queue.add(curNode.right);
                }
            }
            /**
             * void add(int index,E element)在此列表中指定的位置插入指定的元素。
             * 移动当前在该位置处的元素（如果有），所有后续元素都向右移（在其索引中添加 1）。
             */
            //只有这一步有点区别，其他地方都完全相同
            //即因为是逆序的，所以我们将新的list加到index为0的地方即可。
            result.add(0, curLevel);//
        }
        return result;
    }
}
