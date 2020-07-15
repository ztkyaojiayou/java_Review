package 数据结构与算法.LeetCode题解.二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 示例 1:
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 */


//Definition for a binary tree node.
 class TreeNode98 {
    int val;
    TreeNode98 left;
    TreeNode98 right;
    TreeNode98(int x) { val = x; }
}
/**
 * 解析：对于二叉树而言，基本上都是使用递归爱遍历其左右节点，因此重要的并不是递归，而是与二叉树相关的思想和技巧
 * 一般都要联想到二叉树的三种遍历方式所得到的的结果的特点
 * 对比本题，首先应该明确一点，即：二叉搜索树「中序遍历」得到的值构成的序列一定是升序的
 * 因此，本题的思路可以为：
 * （1）先使用中序遍历，得到节点值的列表
 * （2）再判断列表是否是升序（无重复）
 */
public class 验证二叉搜索树98 {
    public boolean isValidBST(TreeNode98 root) {
        List<Integer> list = getList(root);
        //System.out.println(list);
        if (isOrdered(list)) {
            return true;
        }
        return false;
    }
    // 中序遍历，得到节点值的列表，用list存储起来
    List<Integer> list = new ArrayList<>();
    private List<Integer> getList(TreeNode98 root) {
        if (root != null) {
            getList(root.left);//先向左递归
            list.add(root.val);//再把中间的节点保存起来
            getList(root.right);//后向右递归
        }
        return list;
    }
    // 判断列表是否是升序（无重复），若不是升序，则肯定不是二叉搜索树
    private boolean isOrdered(List<Integer> list) {
        if (list.size() <= 1) {
            return true;
        }
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > list.get(i-1)) {//若升序，则没毛病，跳过
                continue;
            } else {//否则，肯定不是二叉搜索树
                return false;
            }
        }
        //若遍历完之后都没有发现降序的情况，则必为二叉搜索树，返回true
        return true;
    }
}

//其他方法1：也是使用中序遍历,但没太看懂
//思路：
//中序遍历的时候实时检查当前节点的值是否大于前一个中序遍历到的节点的值即可。
//如果均大于说明这个序列是升序的，整棵树是二叉搜索树，否则不是，
class Solution01 {
    public boolean isValidBST(TreeNode98 root) {
        Stack<TreeNode98> stack = new Stack();
        double inorder = - Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {//将所有的左子节点存入栈中
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();//再取出最后一个左子节点
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder)
                return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }
}

//其他方法2：(没太懂）
/**
 * 设计一个递归函数 helper(root, lower, upper) 来递归判断，函数表示考虑以 root 为根的子树，
 * 判断子树中所有节点的值是否都在 (l,r)(l,r) 的范围内（注意是开区间）。
 * 如果 root 节点的值 val 不在 (l,r)(l,r) 的范围内说明不满足条件直接返回，
 * 否则我们要继续递归调用检查它的左右子树是否满足，如果都满足才说明这是一棵二叉搜索树。
 *
 * 那么根据二叉搜索树的性质，在递归调用左子树时，我们需要把上界 upper 改为 root.val，
 * 即调用 helper(root.left, lower, root.val)，因为左子树里所有节点的值均小于它的根节点的值。
 * 同理递归调用右子树时，我们需要把下界 lower 改为 root.val，即调用 helper(root.right, root.val, upper)。
 *
 * 函数递归调用的入口为 helper(root, -inf, +inf)， inf 表示一个无穷大的值。
 *
 */
class Solution02 {
    public boolean isValidBST(TreeNode98 root) {
        // 递归判断以每个节点为根节点的二叉树是否为有效的二叉搜索树。
        return isValidBST(null, null, root);
    }

    private boolean isValidBST(Integer max, Integer min, TreeNode98 root) {
        // 如果根节点为空，直接返回 true 。
        if (root == null) {
            return true;
        }
        // 比较下限，主要针对右子树以及右子树的左子树。
        if (min != null && root.val <= min) {
            return false;
        }
        // 比较上限，主要针对左子树以及左子树的右子树。
        if (max != null && root.val >= max) {
            return false;
        }
        // 递归判断左右子树。
        return isValidBST(root.val, min, root.left) && isValidBST(max, root.val, root.right);
    }
}


