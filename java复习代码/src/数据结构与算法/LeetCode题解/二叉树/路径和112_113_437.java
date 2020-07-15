package 数据结构与算法.LeetCode题解.二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 112. 路径总和I(入门版，只判断）
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，
 * 这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 */
class TreeNode112 {
    int val;
    TreeNode112 left;
    TreeNode112 right;
    TreeNode112(int x) { val = x; }
}
/**
 * 思路解析：简单题，使用递归即可（很基础的递归）
 * 一般来说关于DFS有两个套路：
 * 第一个就是用recursion来完成，这种在理解上着实也不是很简单，但是一旦理解了，起飞！
 * 第二个的话就是用stack来完成iteration，会比上面好理解，但是复杂度会稍微差一点。
 *
 * 思路：
 * 此题就是一个明显的recursion的问题，我们要从root开始，逐步的先往左枝走，如果不满足再倒退回上一个节点，再往右枝走。
 * 如果我们遍历的node不是叶子节点，那么我们就获取它的值val，进而获取我们接下来需要满足的条件：Sum-val.
 * 最后每一个节点node都会进行左右node的两个recursion call。
 * 每一步我们都会进行判断是不是叶子节点和有没有达到sum的要求，如果到了，就返回true；反之，false。
 * 关于为什么去并集，因为只要有一个path存在，我们的任务就完成了，所以只要有true，最后就是true。
 */
class solution1_112{
    public boolean hasPathSum(TreeNode112 root, int sum) {
        //1.递归结束的条件
        if (root == null) {
            return false;
        }
        //2.确定一个符合要求的结果,返回true（也同时起到了剪枝的效果？）
        //若当前值就为sum值且当前节点的左右子点都为空时，则表示到头了，且该路径还刚好符合题目要求，于是返回true即可。
        if (root.val == sum && root.left == null && root.right == null) {
            return true;
        }
        //3.开始递归，把根节点分别更新为当前节点的左右节点，同时把sum减去当前值，因为该值已经使用过啦
        boolean left = hasPathSum(root.left, sum - root.val);
        boolean right = hasPathSum(root.right, sum - root.val);
        //最后返回结果，只要左右子树存在一条路径即符合题意，就返回true，因此使用“与”符号||即可。
        return left || right;
    }
}

/**
 * 113. 路径总和 II（进阶版，求路径和）
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */

/**
 * 思路：
 * 要建立list组成的list来存最终的result，要建立list去存当前的这个路径。
 * 接下来思路与之前近乎相同，采用recursion的方法，先遍历左侧，如果没有满足条件且是leaf,则倒退一个node，继续右枝（如果有的话）。
 * 将每一个遍历到的node放入curPath，然后改变条件至sum-node.val。
 * 如果满足条件，则存下当前的一个list，也就是curPath；如果不满足条件，则将sum改为sum-node.val继续进行recursion。
 * 最重要的一点，当我们到达了某一个leaf的时候，我们发现当前path并不满足和为sum的时候，我们在倒退会上一个node的同时，要在curPath中清除当前节点！！！！
 */

class Solution2_113 {
    public List<List<Integer>> pathSum(TreeNode112 root, int sum) {
        //结果集
        List<List<Integer>> result = new LinkedList<>();
        //当前路径
        List<Integer> curPath = new LinkedList<>();
        //开始调用递归函数
        recur(result, curPath, root, sum);
        //最后，返回结果集
        return result;
    }
    /**
     * 具体的递归函数
     * @param result 结果集
     * @param curPath 当前路径
     * @param curNode 当前节点
     * @param sum 题给的目标和
     */
    private void recur(List<List<Integer>> result, List<Integer> curPath, TreeNode112 curNode, int sum){
        //1.递归终止的条件
        if(curNode == null){
            return;
        }
        //2.开始做选择，即把当前节点的值存入当前路径list中
        curPath.add(curNode.val);
        //3.确定一个符合要求的结果（也同时起到了剪枝的效果？）
        //若当前值就为sum值且当前节点的左右子点都为空时，则表示到头了，且该路径还刚好符合题目要求，则把它添加到结果集result中。
        if(curNode.val == sum && curNode.left == null && curNode.right == null){
            result.add(new ArrayList<>(curPath));
        }
        //4.开始下一轮递归，即把当前节点换为左右节点，同时更新sum值，即把sum值减去当前值curNode.val
        recur(result, curPath, curNode.left, sum - curNode.val);
        recur(result, curPath, curNode.right, sum - curNode.val);

        //5.撤销，即把最后一个节点删除，用于回溯。
        curPath.remove(curPath.size() - 1);
    }
}

/**
 * 437. 路径总和 III(高阶版，要把符合要求的路径全部打印出来）
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * 找出路径和等于给定数值的路径总数。
 * 路径不需要从根节点开始，也不需要在叶子节点结束，
 * 但是路径方向必须是向下的（只能从父节点到子节点）。
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 *
 * 示例：
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * 返回 3,因为和等于 8 的路径三条，分别是:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 */
/**
 * 思路解析：
 * 题目要求 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点） 。
 * 这就要求我们只需要去求三部分即可：
 * 1）以当前节点作为头结点的路径数量
 * 2）以当前节点的左孩子作为头结点的路径数量
 * 3）以当前节点的右孩子作为头结点啊路径数量
 * 最后，将这三部分之和作为最后结果即可。
 *
 * 最后的问题是：我们应该如何去求以当前节点作为头结点的路径的数量？
 * 这里依旧是按照树的遍历方式模板，每到一个节点让sum-root.val，
 * 并判断sum是否为0，如果为零的话，则找到满足条件的一条路径。
 */

class 路径和3_437 {
    public int pathSum(TreeNode112 root, int sum) {
        if(root == null){
            return 0;
        }
        //1）以当前节点作为头结点的路径数量
        int curSum = countPath(root,sum);
        //2）以当前节点的左孩子作为头结点的路径数量
        int leftSum = pathSum(root.left,sum);
        //3）以当前节点的右孩子作为头结点啊路径数量
        int rightSum = pathSum(root.right,sum);
        //最后，将这三部分之和作为最后结果即可。
        return curSum+leftSum+rightSum;

    }
    //以当前节点作为头结点的路径数量的计算方法
    public int countPath(TreeNode112 root, int sum){
        //递归终止的条件
        if(root == null){
            return 0;
        }
        //每访问过一个节点，就减去该值
        sum = sum - root.val;
        //判断sum是否刚好为0，如果为零的话，则说明找到了一条满足条件的路径。
        int result = sum == 0 ? 1:0;
        return result + countPath(root.left,sum) + countPath(root.right,sum);
    }
}

