package 数据结构与算法.第三遍;

import 数据结构与算法.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 思路： 利用 BFS 进行层次遍历，记录下每层的最后一个元素。
 * 时间复杂度： O(N)O(N)，每个节点都入队出队了 1 次。
 * 空间复杂度： O(N)O(N)，使用了额外的队列空间。
 */
public class demo05_2二叉树的右视图 {
    //和层序遍历差不多
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    //新增逻辑
                    if (node == null) {
                        continue;//continue时，跳出本次循环，继续执行下次循环;Break时，跳出循环（结束循环），执行循环体下面的语句。
                    }
                    //关键代码
                    //只将当前层的最后一个节点放入结果列表
                    if (i == size - 1) {
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
    public List<Integer> rightSideView02(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> singleLine = new LinkedList<>();
        singleLine.add(root);
        while (!singleLine.isEmpty()){
            int size = singleLine.size();
            for (int i = 0;i<size;i++){
                TreeNode cur_node = singleLine.poll();
                //新增逻辑
                if (cur_node == null) {
                    continue;//continue时，跳出本次循环，继续执行下次循环;Break时，跳出循环（结束循环），执行循环体下面的语句。
                }
                if (i == size-1){
                    res.add(cur_node.val);

                }
                if (cur_node.left != null){
                    singleLine.add(cur_node.left);
                }
                if (cur_node.right != null){
                    singleLine.add(cur_node.right);
                }
            }
        }
        return res;
    }
    }



