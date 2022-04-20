package 数据结构与算法.离职后刷题.第四遍必会版;

import 数据结构与算法.TreeNode;

import java.util.*;

public class demo05_0二叉树的遍历_非递归版 {
    //（1）非递归前序遍历(这个版本非常之好，类比层次遍历，思路清晰）
    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        //先把根节点入栈
        stack.push(root);
        //再处理栈中的节点
        while (!stack.isEmpty()) {
                //出栈
                TreeNode cur = stack.pop();
                if (cur == null) {
                    continue;//continue时，跳出本次循环，继续执行下次循环;Break时，跳出循环（结束循环），执行循环体下面的语句。
                }
                res.add(cur.val);
                //再把其左右节点入栈
//因为是栈，所以是先进后出，而前序遍历的顺是根节点-左节点-右节点，
//因此要先把右节点入栈，再把左节点入栈
                if (cur.right != null) {
                    stack.push(cur.right);
                }

                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        return res;
    }

    //（2）非递归中序遍历
    public List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {//左
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();//中
                res.add(cur.val);
                cur = cur.right;//右
            }
        }
        return res;
    }


    //（3）非递归后序遍历

    /**
     * 后序遍历的输出顺序是左、右、根，当我们采用先序遍历的方法，
     * 但是先遍历右子树，实现的效果是根、右、左，刚好和后序遍历的结果想法，
     * 所以我们通过add(0, node)的方式将顺序反序，达到我们想要的效果。
     *
     * @param root
     * @return
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                list.add(0, cur.val);
                cur = cur.right;
            } else {
                cur = stack.pop();
                cur = cur.left;
            }
        }
        return list;
    }
}
