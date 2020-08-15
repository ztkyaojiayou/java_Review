package 数据结构与算法.剑指offer题解.第二遍.二叉树;

import java.util.ArrayList;

public class 二叉树中和为k的所有路径 {
    ArrayList<Integer> path_list = new ArrayList<>();
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int k){
        int cur_sum = 0;
        //使用递归
        findPath(root,k,cur_sum,path_list);
        return res;
    }
//递归算法
    private void findPath(TreeNode node,int k,int cur_sum,ArrayList<Integer> path_list) {
        if (node == null){
            return;
        }

        //1.递归结束的条件
        if (cur_sum == k && node.left == null && node.right == null){
            res.add(new ArrayList<Integer>(path_list));
        }
        //2.对于一般情况，则递归左右节点
        path_list.add(node.val);//把当前节点加入list中
        cur_sum += node.val;//累加该值
            findPath(node.left,k,cur_sum,path_list);
            findPath(node.right,k,cur_sum,path_list);
            //3.回溯/后退，即把最后一个节点删除，因为要回溯到上一个节点去看其他节点是否还满足条件
        path_list.remove(path_list.size()-1);
    }
}
