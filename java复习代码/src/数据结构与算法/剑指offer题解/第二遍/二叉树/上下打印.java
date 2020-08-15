package 数据结构与算法.剑指offer题解.第二遍.二叉树;

import java.util.ArrayList;
import java.util.LinkedList;

public class 上下打印 {
    public ArrayList<Integer> PrintUpDown(Node17 root){
        ArrayList<Integer> res = new ArrayList<>();
        LinkedList<Node17> queue = new LinkedList<>();
        queue.add(root);
        int cnt = queue.size();
        while (!queue.isEmpty()){
            while (cnt > 0){
                Node17 node = queue.poll();
                if (node == null){
                    continue;
                }
                res.add(node.val);
                queue.add(root.left);
                queue.add(root.right);
                cnt--;
            }
        }
        return res;
    }
}
