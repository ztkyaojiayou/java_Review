package 数据结构与算法.第三遍;

import 数据结构与算法.TreeNode;

import java.util.Stack;

//使用栈，迭代比较即可，利用的是二叉搜索树的中序遍历的序列为递增序列的性质
//这里具体做法为：在中序遍历时，判断当前元素的值是否大于前一个元素的值即可，只要有一处小于，则表示不是BST树
public class demo05_14验证二叉搜索树 {
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        double pre_value = -Double.MAX_VALUE;//表示当前正在遍历的元素的前一个元素
        while (!stack.isEmpty() || root != null) {
            //先把左节点全部入栈（左）
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            //再弹出并比较（中）
            TreeNode cur = stack.pop();
            if (cur.val < pre_value) {//核心代码，若当前元素的值还小于前一个元素的值（因为BST树的中序遍历肯定为递增序列），则肯定不是BST树
                return false;
            }
            pre_value = cur.val;//记录当前节点的值，用于下一次比较
            root = cur.right;//遍历右节点，即把当前节点的右节点当成root节点（右）
        }
        return true;
    }

}
