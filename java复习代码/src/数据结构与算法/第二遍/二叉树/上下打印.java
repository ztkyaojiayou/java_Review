package 数据结构与算法.第二遍.二叉树;

import java.util.ArrayList;
import java.util.LinkedList;

public class 上下打印 {//和层序遍历相同，只是是直接全部放入一个list中，而不分组
    public ArrayList<Integer> PrintUpDown(Node17 root){
        ArrayList<Integer> res = new ArrayList<>();
        LinkedList<Node17> queue = new LinkedList<>();
        queue.add(root);
        int size = queue.size();
        while (!queue.isEmpty()){
           for (int i = 0;i<size;i++){
                Node17 node = queue.poll();
                if (node == null){
                    continue;
                }
                res.add(node.val);
                queue.add(root.left);
                queue.add(root.right);
                size--;
            }
        }
        return res;
    }
}
