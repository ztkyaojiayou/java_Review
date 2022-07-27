package 数据结构与算法.离职后刷题.第四遍必会版;

import 数据结构与算法.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 【解决思路】首先，“每行第一个数”，让我们想到这是层序遍历的变形。
 * 用 size 记录当层的结点数，循环访问时用 child 记录下一层的结点数，当 i = 0 时，是当层第一个结点。
 * 思路写得比较简单，注释写得挺详细的，应该很好懂。
 */
public class demo05_3二叉树的左视图 {
    //左视图
    public List<Integer> LeftView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                //删除当前要处理的结点，同时获取到该节点的值，供后续使用
                TreeNode node = queue.poll();
                //新增逻辑
                if (node == null) {
                    //continue时，跳出本次循环，继续执行下次循环;
                    //Break时，跳出循环（结束循环），执行循环体下面的语句。
                    continue;
                }
                //关键代码
                //只将当前层的第一个节点放入结果列表
                if (i == 0) {
                    res.add(node.val);
                }

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return res;
    }

    //自写一遍
    public List<Integer> LeftView02(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> singleLine = new LinkedList<>();
        singleLine.add(root);
        while (!singleLine.isEmpty()) {
            int size = singleLine.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur_node = singleLine.poll();
                //新增逻辑
                if (cur_node == null) {
                    continue;//continue时，跳出本次循环，继续执行下次循环;Break时，跳出循环（结束循环），执行循环体下面的语句。
                }
                if (i == 0) {
                    res.add(cur_node.val);
                }
                if (cur_node.left != null) {
                    singleLine.add(cur_node.left);
                }
                if (cur_node.right != null) {
                    singleLine.add(cur_node.right);
                }
            }
        }
        return res;
    }

}
