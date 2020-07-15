package 数据结构与算法.剑指offer题解.二叉树;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：输入一棵二叉树和一个整数， 打印出二叉树中结点值的和为输入整数的所有路径。
 * 【从树的根结点开始往下一直到叶结点所经过的结点形成一条路径】
 *
 */
class TreeNode39 {
    int val = 0;
    TreeNode39 left = null;
    TreeNode39 right = null;

    public TreeNode39(int val) {
        this.val = val;

    }

}
public class tree39二叉树中和为某一值的路径 {
    //方法一：回溯，且每遍历结点，和值减少，直到0，则返回
     ArrayList<Integer> path = new ArrayList<>();
     ArrayList<ArrayList<Integer>> ret = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode39 root, int target) {//target为目标和值
        int currentSum = 0;//当前和值
        backtracking(root, currentSum, target, path);//调用backtracking方法
        return ret;
    }

    private void backtracking(TreeNode39 node, int currentSum, int target, ArrayList<Integer> path) {
        if (node == null)
            return;
        path.add(node.val);//把遍历过的结点值加入到path中
        currentSum += node.val;//累加已经遍历过的结点值
        if (currentSum == target && node.left == null && node.right == null) {
            ret.add(new ArrayList<Integer>(path));
        } else {//调用backtracking方法向其左右子节点进行回溯，变量是其子节点
            backtracking(node.left, currentSum,target, path);
            backtracking(node.right, currentSum,target, path);
        }

        //（关键代码）此时已经遍历到了叶子节点，且加入到了path中，
        // 此时不管这个叶子节点是否满足要求，在判断其另外一个叶子节点是否满足要求时，
        // 应该删除掉这个叶子结点值而换成另外一个叶子节点重新计算
        path.remove(path.size() - 1);
    }

    //另一种写法（思路完全相同，只是更直白）
    private static void findPath(TreeNode39 head, int expectedSum) {
        if (head == null){
            return;
        }

        List<Integer> path = new ArrayList<>();
        int currentSum = 0;
        findPath(head, expectedSum, path, currentSum);
    }

    private static void findPath(TreeNode39 head, int expectedSum, List<Integer> path, int currentSum) {
        currentSum += head.val;
        path.add(head.val);
        //已经遍历到了最末尾，且已经找到了
        if (head.left==null && head.right==null && currentSum == expectedSum){
            System.out.println(path);
        }
        //若有左子结点，则向左递归
        if (head.left != null){
            findPath(head.left, expectedSum, path, currentSum);
        }
        //若有右子结点，向右递归
        if (head.right != null){
            findPath(head.right, expectedSum, path, currentSum);
        }
        //因为当本次递归结束返回上一层的时候，我们已经遍历完这个节点的左右子树，
        //也就是已经该树中可能存在的路径，再次返回上一层的时候要把这个节点挪除去，
        //这样在遍历上一个节点的其他子树的时候遍历的结果才是对的

        //因为你遍历到某一叶子节点的时候 ，把该叶子节点的值加入list中 ，不管该叶子结点是否合适 ，
        //递归回溯到该叶子结点所在的子树 ，判断子树的另一个叶子结点是否合适时，刚刚加入list中的结点肯定要删除的

        //因为我们使用了list这个列表来存储当前走过的节点，
        //所以为了寻找新的路径，递归完返回上一次的时候要删除掉，这样才能保证寻找下一个路径的时候是正确的
        path.remove(path.size()-1);//删除ArrayList中的最后一个元素，因为要重新回溯到这个结点去查找它的另一个子结点
    }


}
