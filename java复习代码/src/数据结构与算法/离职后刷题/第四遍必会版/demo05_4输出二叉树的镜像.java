package 数据结构与算法.离职后刷题.第四遍必会版;

import 数据结构与算法.TreeNode;

import java.util.LinkedList;

//即反转二叉树，与demo54相同
public class demo05_4输出二叉树的镜像 {
    /**
     * 方法1：使用递归，注意是在原树上面修改
     */
    public void Mirror01(TreeNode root) {
        //递归结束的条件
        if (root == null) {
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

    //自写一遍
    public void Mirror001(TreeNode root) {
        //递归出口
        if (root == null) {
            return;
        }
        //交换
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        //递归调换其左右结点
        Mirror001(root.left);
        Mirror001(root.right);
    }


    /**
     * 方法2：使用栈，推荐，类似于二叉树的层序遍历
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        LinkedList<TreeNode> singleLine = new LinkedList<>();
        singleLine.add(root);
        while (!singleLine.isEmpty()) {
            //也是一行一行处理
            int size = singleLine.size();
            for (int i = 0; i < size; i++) {
                //处理掉的就删除
                TreeNode cur_node = singleLine.pop();
                //新增逻辑
                if (cur_node == null) {
                    continue;//continue时，跳出本次循环，继续执行下次循环;Break时，跳出循环（结束循环），执行循环体下面的语句。
                }
                //互换当前节点的左右子节点
                TreeNode temp = cur_node.left;
                cur_node.left = cur_node.right;
                cur_node.right = temp;

                //再把其左右子节点入队，依次进行如上相同的处理即可
                if (cur_node.right != null) {
                    singleLine.add(cur_node.right);
                }
                if (cur_node.left != null) {
                    singleLine.add(cur_node.left);
                }
            }
        }
        //返回根节点即可
        return root;
    }

    //自写一遍
    public TreeNode invertTree01(TreeNode root) {
        //用于存储每一层的结点
        LinkedList<TreeNode> singleLine = new LinkedList<>();
        singleLine.add(root);
        while (!singleLine.isEmpty()) {
            int size = singleLine.size();
            for (int i = 0;i<size;i++){
                //处理掉的就删掉
                TreeNode cur_node = singleLine.poll();
                //调换其左右结点
                TreeNode temp = cur_node.left;
                cur_node.left = cur_node.right;
                cur_node.right = temp;
                //同时添加当前结点的左右节点，即在下一个循环处理下一层
                if (cur_node.left != null){
                    singleLine.add(cur_node.left);
                }
                if (cur_node.right != null){
                    singleLine.add(cur_node.right);
                }
            }
        }
        //返回根节点即可，因为根结点下面的元素全部已经调换了
        return root;
    }
}
