package 数据结构与算法.剑指offer题解.二叉树;

import 数据结构与算法.TreeNode;

import java.util.*;

class TreeNode22 {
    //创建二叉树所需要的结点（牛客网上则不需要自己写，系统默认已输入）
    int val = 0;
    TreeNode22 left;
    TreeNode22 right;
    TreeNode22(int x){
        this.val = x;
    }
}
public class 二叉树的三种遍历方式 {
    /**
     * 基础回顾：
     * 二叉树有很多种遍历方法，最基本的有三种：
     * 1.前序遍历：对于下图二叉树的前序遍历结果为：1  2  4  8  9  5  10  3  6  7
     * 2.中序遍历：对于下图二叉树的中序遍历结果为：8  4  9  2  10  5  1  6  3  7
     * 3.后序遍历：对于下图二叉树的后 序遍历结果为：8  9  4  10  5  2  6  7  3  1
     */
    //（1）前序遍历（根左右）
    // 方法1：递归法（简单，一般不考）：
    // 基本思想：先访问根结点，再先序遍历左子树，最后再先序遍历右子树。
    private void preOrder01(TreeNode22 root) {
        if (root == null) return;
        System.out.print(root.val + "  ");        //输出结果  1  2  4  8  9  5  10  3  6  7
        if (root.left != null){
            preOrder01(root.left);
        }
        if (root.right != null){
            preOrder01(root.right);
        }
    }

    //方法2：非递归版：用栈实现（常考，掌握）
    // 非递归前序遍历(这个版本非常之好，类比层序遍历，思路清晰）
    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode current = stack.pop();
            res.add(current.val);

            if(current.right != null) {
                stack.push(current.right);
            }

            if(current.left != null) {
                stack.push(current.left);
            }
        }
        return res;
    }

    //2.中序遍历（左根右）
    // 方法1：递归法
    // 基本思想：先中序遍历左子树，然后再访问根结点，最后再中序遍历右子树即 左—根—右。
    private void inOrder01(TreeNode22 root) {
        if (root == null) return;
        if (root.left != null){
            inOrder01(root.left);
        }
        System.out.print(root.val + "  ");        //输出 8  4  9  2  10  5  1  6  3  7
        if (root.right != null){
            inOrder01(root.right);
        }
    }

    //方法2：非递归版，使用栈实现
    private void inOrder02(TreeNode22 root) {
        if(root == null) return;
        Stack<TreeNode22> stack = new Stack<>();
        TreeNode22 cur = root;
        while (cur != null || stack.size() > 0) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;//（左）
            } else {
                cur = stack.pop();
                System.out.print(cur.val + "  ");//（根）
                cur = cur.right;//（右）
            }
        }
    }
    //3.后序遍历（左右根）
    //方法1：递归法
    //基本思想：先后序遍历左子树，然后再后序遍历右子树，最后再访问根结点即 左—右—根。
    private void afterOrderBinTree(TreeNode22 root) {
        if (root == null) return;
        if (root.left != null){
            afterOrderBinTree(root.left);
        }
        if (root.right != null){
            afterOrderBinTree(root.right);
        }
        System.out.print(root.val + "  ");        //输出 8  9  4  10  5  2  6  7  3  1
    }


    /**
     * 方法2：非递归版，使用栈实现
     * 后序遍历的输出顺序是左、右、根，当我们采用先序遍历的方法，
     * 但是先遍历右子树，实现的效果是根、右、左，刚好和后序遍历的结果想法，
     * 所以我们通过add(0, node)的方式将顺序反序，达到我们想要的效果。
     * @param root
     * @return
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()) {
            if(node != null) {
                stack.push(node);
                list.add(0, node.val);
                node = node.right;
            }else {
                node = stack.pop();
                node = node.left;
            }
        }
        return list;
    }
}
