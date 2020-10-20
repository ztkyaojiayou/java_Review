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
            if (root == null) {
                return res;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();

                    //关键代码
                    if (i == size - 1) {  //将当前层的最后一个节点放入结果列表
                        res.add(node.val);
                    }

                    if (node.left != null) {
                        queue.offer(node.left);
                    }

                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
            return res;
        }
    }



