package 数据结构与算法.第三遍;

import 数据结构与算法.TreeNode;

public class demo18_输出二叉树的镜像 {//即反转二叉树，与demo54相同
    //在原树上面修改,同样是使用递归
    public void Mirror(TreeNode root) {
        //递归结束的条件
        if (root == null){
            return;
        }
        //先交换当前节点左右子节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        //再使用递归处理其左右节点
        Mirror(root.left);
        Mirror(root.right);
    }

}
