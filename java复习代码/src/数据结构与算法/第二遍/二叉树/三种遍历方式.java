package 数据结构与算法.第二遍.二叉树;

import java.util.Stack;

public class 三种遍历方式 {
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
    private void preOrder01(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.val + "  ");        //输出结果  1  2  4  8  9  5  10  3  6  7
        if (root.left != null){
            preOrder01(root.left);
        }
        if (root.right != null){
            preOrder01(root.right);
        }
    }

    //方法2：非递归版：用栈实现（常考，掌握）
    private void preOrder02(TreeNode root) {
        if(root == null) return;
        Stack<TreeNode> s = new Stack<>();
        TreeNode node = root;
        while (node != null || s.size() > 0) {
            if (node != null) {//只要当前节点不为空，就打印当前节点和其左节点，同时把该节点及其左节点入栈
                System.out.print(node.val + "  ");//（根）
                s.push(node);//入栈
                node = node.left;//当前节点的左节点(左）
            } else {//若当前节点为空，即已经遍历到了最下方的叶子节点，此时易知栈中的元素从上到下依次为最左边的从下到上的根节点，
                // 要将其一个一个弹出，再看其右节点，若不为空，则又会跳转到上面的if语句，把其右节点打印出来，依次往复即可。
                node = s.pop();//出栈，每出栈一个元素，就把当前节点设为它的右节点，目的是把他们也打印出来。
                node = node.right;//（右）
            }
        }
    }

    //2.中序遍历（左根右）
    // 方法1：递归法
    // 基本思想：先中序遍历左子树，然后再访问根结点，最后再中序遍历右子树即 左—根—右。
    private void inOrder01(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null){
            inOrder01(root.left);//左
        }
        System.out.print(root.val + "  ");//根        //输出 8  4  9  2  10  5  1  6  3  7

        if (root.right != null){
            inOrder01(root.right);//右
        }
    }

    //方法2：非递归版，使用栈实现
    private void inOrder02(TreeNode root) {
        if(root == null) return;

        Stack<TreeNode> s = new Stack<>();

        TreeNode node = root;

        while (node != null || s.size() > 0) {
            if (node != null) {
                s.push(node);
                node = node.left;//（左）
            } else {
                node = s.pop();//（左）
                System.out.print(node.val + "  ");//（根）
                node = node.right;//（右）
            }
        }
    }
    //3.后序遍历（左右根）
    //方法1：递归法
    //基本思想：先后序遍历左子树，然后再后序遍历右子树，最后再访问根结点即 左—右—根。
    private void afterOrderBinTree(TreeNode root) {
        if (root == null) return;
        if (root.left != null){
            afterOrderBinTree(root.left);
        }
        if (root.right != null){
            afterOrderBinTree(root.right);
        }
        System.out.print(root.val + "  ");        //输出 8  9  4  10  5  2  6  7  3  1
    }

    //方法2：非递归版，使用栈实现（要用到两个栈，较难，了解）
    private void afterIterateBinTree(TreeNode root) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        int i = 1;
        while(root != null || !stack1.empty()) {
            while (root != null) {
                stack1.push(root);
                stack2.push(0);
                root = root.left;
            }

            while(!stack1.empty() && stack2.peek() == i) {
                stack2.pop();
                System.out.print(stack1.pop().val + "  ");
            }

            if(!stack1.empty()) {
                stack2.pop();
                stack2.push(1);
                root = stack1.peek();
                root = root.right;
            }
        }
    }
}
