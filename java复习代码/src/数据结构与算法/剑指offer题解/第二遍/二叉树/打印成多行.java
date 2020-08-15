package 数据结构与算法.剑指offer题解.第二遍.二叉树;

import java.util.ArrayList;
import java.util.LinkedList;

public class 打印成多行 {
    public ArrayList<ArrayList<Integer>> PrintLine(Node17 root){
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        LinkedList<Node17> queue = new LinkedList<>();
        queue.add(root);
        int cnt = queue.size();
        while (!queue.isEmpty()){
            ArrayList<Integer> temp_list = new ArrayList<>();
            while (cnt>0){
                Node17 node = queue.poll();
                if (node == null){
                    continue;
                }
                temp_list.add(node.val);
                queue.add(root.left);
                queue.add(root.right);
                cnt--;
            }
            if (!temp_list.isEmpty()){
                res.add(temp_list);
            }
        }
        return res;
    }
}
