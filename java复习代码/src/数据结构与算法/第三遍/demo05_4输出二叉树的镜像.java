package 数据结构与算法.第三遍;

import 数据结构与算法.TreeNode;
import java.util.Stack;

public class demo05_4输出二叉树的镜像 {//即反转二叉树，与demo54相同
    /**
     * 方法1：使用递归，注意是在原树上面修改
     */
    public void Mirror01(TreeNode root) {
        //递归结束的条件
        if (root == null){
            return;
        }
        //先交换当前节点左右子节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        //再使用递归处理其左右节点
        Mirror01(root.left);
        Mirror01(root.right);
    }

    /**
     * 方法2：使用栈，推荐，类似于二叉树的层序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            //互换当前节点的左右子节点
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;

            //再把其左右子节点入队，依次进行如上相同的处理即可
            stack.push(cur.right);
            stack.push(cur.left);
        }
        //返回根节点即可
        return root;
    }
}
