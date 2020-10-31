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
 * 解析：对于二叉树而言，基本上都是使用递归来遍历其左右节点，因此重要的并不是递归，而是与二叉树相关的思想和技巧
 * 一般都要联想到二叉树的三种遍历方式所得到的的结果的特点
 * 对比本题，首先应该明确一点，即：二叉搜索树「中序遍历」得到的值构成的序列一定是升序的（核心关键）
 * 因此，本题的思路可以为：
 * （1）先使用中序遍历，得到节点值的列表
 * （2）再判断列表是否是升序（无重复）
 */

//非递归版：也是使用中序遍历,但是要用到一个辅助数据结构，栈（面试可能更倾向于这种方法，推荐）
//思路：
//中序遍历的时候实时检查当前节点的值是否大于前一个中序遍历到的节点的值即可。
//如果均大于说明这个序列是升序的，整棵树是二叉搜索树，否则不是，
class 验证二叉搜索树01 {
    public boolean isValidBST(TreeNode98 root) {
        Stack<TreeNode98> stack = new Stack();

        // 为什么要用double或long型呢？int不行吗？其实是没问题的，只是力扣的题给的测试案例太大了，没办法哈哈哈
        double inorder = - Double.MAX_VALUE;//用于表示根节点

        while (!stack.isEmpty() || root != null) {
            while (root != null) {//将根节点和所有的左子节点存入栈中
                stack.push(root);
                root = root.left;
            }
            TreeNode98 temp = stack.pop();//再取出最后一个左子节点
// 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (temp.val <= inorder)
                return false;
            inorder = temp.val;//保存当前节点
            root = temp.right;//遍历右子节点
        }
        return true;
    }
}

//方法2：递归版1（思路清晰）
public class 验证二叉搜索树98 {
    //是否为二叉搜索树的判断方法
    public boolean isValidBST(TreeNode98 root) {
        List<Integer> list = getList(root);
        //System.out.println(list);
        if (isOrdered(list)) {
            return true;
        }
        return false;
    }
    // 辅助方法1：中序遍历，得到节点值的列表，用list存储起来
    List<Integer> list = new ArrayList<>();
    private List<Integer> getList(TreeNode98 root) {
        if (root != null) {
            getList(root.left);//先向左递归
            list.add(root.val);//再把中间的节点保存起来
            getList(root.right);//后向右递归
        }
        return list;
    }
    // 辅助方法2：判断列表是否是升序（无重复），若不是升序，则肯定不是二叉搜索树
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

//方法3：递归版2：（递归版推荐）
// 中序遍历时，判断当前节点是否大于中序遍历的前一个节点，
// 如果大于，说明满足 BST，继续遍历；否则直接返回 false。
class Solution {
    long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode98 root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST(root.left)){
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
    }
}





