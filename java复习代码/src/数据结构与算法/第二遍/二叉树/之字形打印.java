package 数据结构与算法.第二遍.二叉树;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class 之字形打印 {
    public ArrayList<ArrayList<Integer>> PrintZhi(Node17 root){
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        LinkedList<Node17> queue = new LinkedList<>();
        queue.add(root);
        boolean reverse = false;
        while (!queue.isEmpty()){
            ArrayList<Integer> temp_list = new ArrayList<>();
            int cnt = queue.size();
            while (cnt>0){
                Node17 node = queue.poll();
                if (node == null){
                    continue;
                }
                temp_list.add(node.val);
                  cnt--;
                queue.add(root.left);
                queue.add(root.right);
            }

if (reverse){//为真才反转，则易知，第一行恰好不反转
    Collections.reverse(temp_list);
}
reverse = !reverse;
if (!temp_list.isEmpty()){
    res.add(temp_list);
}
        }
        return res;
    }

}
