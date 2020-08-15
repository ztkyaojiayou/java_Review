package 数据结构与算法.剑指offer题解.第二遍.二叉树;

import java.util.HashMap;

public class 重建二叉树 {
HashMap<Integer, Integer> index_InOrder = new HashMap<>();
public TreeNode reBuildTree(int[] pre,int[] in){
    for (int i = 0;i< in.length;i++){
        index_InOrder.put(in[i],i);
    }
    TreeNode root_Node = reBuild(pre,0,pre.length-1,0);
    return root_Node;
}
//重建二叉树，递归
    private TreeNode reBuild(int[] pre, int preL, int preR, int inL) {
    //先创建根节点，其值即为前序遍历中的第一个元素
        TreeNode root = new TreeNode(pre[preL]);
        int in_Index = index_InOrder.get(root.val);
        int left_size = in_Index - inL;
        root.left = reBuild(pre,preL+1,preL + left_size,inL);
        root.right = reBuild(pre,preL+left_size+1,preR,inL+left_size+1);
        return root;
    }
}
