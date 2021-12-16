package 数据结构与算法.第三遍;

import 数据结构与算法.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 【解决思路】首先，“每行第一个数”，让我们想到这是层序遍历的变形。
 * 用 size 记录当层的结点数，循环访问时用 child 记录下一层的结点数，当 i = 0 时，是当层第一个结点。
 * 思路写得比较简单，注释写得挺详细的，应该很好懂。
 */
public class demo05_3二叉树的左视图 {
    //左视图
        public List<Integer> LeftView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                //关键代码
                if (i == 0) {  //将当前层的第一个节点放入结果列表
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
