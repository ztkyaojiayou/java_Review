package 数据结构与算法.第三遍;


import 数据结构与算法.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//不难
public class demo05_二叉树的层序遍历 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        //特判
        if (root == null){
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> res = new ArrayList<>();
        //用于存放每一层节点，用于处理下一层节点
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            //用于临时保存每一层的节点，并存入结果集中
            LinkedList<Integer> temp_list = new LinkedList<>();
            int size = queue.size();
            for (int i = 0;i< size;i++){
                TreeNode node = queue.remove();
                temp_list.add(node.val);
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
            res.add(temp_list);
            //若为从后向前遍历，则只需换个api即可，即把每一次的结果都存在第一个位置
            //res.add(0,temp_list);
        }
        return res;
    }
}
