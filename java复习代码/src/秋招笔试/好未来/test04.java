package 秋招笔试.好未来;

import 数据结构与算法.TreeNode;

import java.util.LinkedList;
import java.util.Stack;

public class test04 {
    //方法2：非递归版：用栈实现（常考，掌握）
    private String preOrder02(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if(root == null) return null;
        Stack<TreeNode> s = new Stack<>();
        TreeNode node = root;
        while (node != null || s.size() > 0) {
            if (node != null) {//只要当前节点不为空，就打印当前节点和其左节点，同时把该节点及其左节点入栈
               sb.append(root.val + ",");//（根）
                s.push(node);//入栈
                node = node.left;//当前节点的左节点(左）
            } else {//若当前节点为空，即已经遍历到了最下方的叶子节点，此时易知栈中的元素从上到下依次为最左边的从下到上的根节点，
                // 要将其一个一个弹出，再看其右节点，若不为空，则又会跳转到上面的if语句，把其右节点打印出来，依次往复即可。
                node = s.pop();//出栈，每出栈一个元素，就把当前节点设为它的右节点，目的是把他们也打印出来。
                node = node.right;//（右）
            }
        }
        String res = sb.toString();
        String ress = res.substring(0, res.length() - 1);
        return ress;
    }

//非递归2
    class Solution {
        public String preorderTraversal(TreeNode root) {
            LinkedList<TreeNode> stack = new LinkedList<>();
            StringBuilder sb = new StringBuilder();
            if (root == null) {
                return null;
            }
            stack.add(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pollLast();
                sb.append(root.val + ",");//（根）
                if (node.right != null) {
                    stack.add(node.right);
                }
                if (node.left != null) {
                    stack.add(node.left);
                }
            }
            String res = sb.toString();
            String ress = res.substring(0, res.length() - 1);
            return ress;
        }
    }

    //递归
    private String preOrder(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) return null;
       sb.append(root.val+ ",");    //输出结果  1  2  4  8  9  5  10  3  6  7
        if (root.left != null){
            preOrder(root.left);
        }
        if (root.right != null){
            preOrder(root.right);
        }
        String res = sb.toString();
        String ress = res.substring(0, res.length() - 1);
        return ress;
    }

}
