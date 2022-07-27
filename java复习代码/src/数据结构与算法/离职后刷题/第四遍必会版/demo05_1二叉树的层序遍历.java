package 数据结构与算法.离职后刷题.第四遍必会版;


import 数据结构与算法.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//即相当于如下两题，只是最终结果的形式不太一样而已：
//tree15把二叉树打印成多行
//tree37从上往下打印二叉树
//不难
public class demo05_1二叉树的层序遍历 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        //用于存放每一层节点（而不是值），用于处理下一层节点
        LinkedList<TreeNode> queue = new LinkedList<>();
        //先把根节点加进去（也即第一层呀），开始遍历之旅
        queue.add(root);
        while (!queue.isEmpty()) {
            //用于临时保存每一层的节点的值，并存入结果集中，
            //因此需要在循环中创建，即每遍历新的一层都要重新创建一个list用于保存
            LinkedList<Integer> temp_list = new LinkedList<>();
            int size = queue.size();
            //遍历每一层的结点（易知：第一层就一个root结点），同时加入下一层的结点
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();//弹出（删除）/remove
                if (node == null) {
                    continue;//continue时，跳出本次循环，继续执行下次循环;Break时，跳出循环（结束循环），执行循环体下面的语句。
                }
                //即把每一层的结点都加进去，并使用一个list单独存储
                // （这其实就是看题目咋要求啦，单独放只是为了体现一层一层的情况）
                //（而左右视图的题就只需加第一个或最后一个即可，只有这里有点区别）
                temp_list.add(node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            //将每一层的结点加入res
            res.add(temp_list);
            //若为从后向前遍历，则只需换个api即可，即把每一次的结果都存在第一个位置
            //res.add(0,temp_list);
        }
        return res;
    }

    //自写一遍
    public List<List<Integer>> levelOrder02(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<TreeNode> singleLine = new LinkedList<>();
        singleLine.add(root);
        while (!singleLine.isEmpty()) {
            LinkedList<Integer> temp = new LinkedList<>();
            for (TreeNode treeNode : singleLine) {
                temp.add(treeNode.val);
                TreeNode cur_node = singleLine.poll();
                //新增逻辑
                if (cur_node == null) {
                    continue;//continue时，跳出本次循环，继续执行下次循环;Break时，跳出循环（结束循环），执行循环体下面的语句。
                }
                if (treeNode.left != null) {
                    singleLine.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    singleLine.add(treeNode.right);
                }
            }
            res.add(temp);
        }
        return res;
    }
}
