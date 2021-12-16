package 数据结构与算法.剑指offer第一版.二叉树;

import java.util.HashMap;
import java.util.Map;

/**
 * （思路懂，代码不太懂）题目：根据前序和中序序列（不含有重复的数字），构建一棵二叉树
 *
 * 分析：
 * 根据中序遍历和前序遍历可以确定二叉树，具体过程为：
 * 1.根据前序序列第一个结点确定根结点
 * 2.再根据根结点在中序序列中的位置分割出左右两个子序列
 * 3.最后对左子树和右子树分别递归使用同样的方法继续分解即可（也是先从前序遍历看）
 * 例如：
 * 前序序列{1,2,4,7,3,5,6,8} = pre
 * 中序序列{4,7,2,1,5,3,8,6} = in
 *
 * 根据当前前序序列的第一个结点确定根结点，为 1
 * 找到 1 在中序遍历序列中的位置，为 in[3]
 * 切割左右子树，则 in[3] 前面的为左子树， in[3] 后面的为右子树
 * 则切割后的左子树前序序列为：{2,4,7}，切割后的左子树中序序列为：{4,7,2}；
 * 切割后的右子树前序序列为：{3,5,6,8}，切割后的右子树中序序列为：{5,3,8,6}
 * 对子树分别使用同样的方法分解
 *
 * （前序遍历的第一个值为根节点的值，使用这个值将中序遍历结果分成两部分，
 * 左部分为树的左子树中序遍历结果，右部分为树的右子树中序遍历的结果。）
 */


 //Definition for binary tree
//创建二叉树所需要的结点（牛客网上则不需要自己写，系统默认已输入）
  class TreeNode11 {
      int val;
     TreeNode13 left;
     TreeNode13 right;

     TreeNode11(int x){
         this.val = x;
     }
 }

public class tree11重建二叉树 {
    // 缓存中序遍历数组每个值对应的索引
    private Map<Integer, Integer> indexForInOrders = new HashMap<>();

    public TreeNode13 reConstructBinaryTree(int[] pre, int[] in) {//pre：前序遍历后的数组，in:中序遍历后的数组
        for (int i = 0; i < in.length; i++)
            indexForInOrders.put(in[i], i);// 存储中序遍历数组每个值对应的索引（即：key=in[i], value=i）

        //调用递归方法
        //参数说明：preL：前序数组的第一个索引，preR：前序数组的最后一个索引，inL：中序数组的第一个索引
        return method(pre, 0, pre.length - 1, 0);
    }

    //使用递归，开始真正重新构建二叉树
    private TreeNode13 method(int[] pre, int preL, int preR, int inL) {
        if (preL > preR)
            return null;
        TreeNode13 root = new TreeNode13(pre[preL]);//在前序遍历数组中获取根节点的值，易知，其第一个值即为根节点的值
        int inIndex = indexForInOrders.get(root.val);//获取根节点在中序数组中所在的索引，目的是为了求出左右子树的长度
        int leftTreeSize = inIndex - inL;//左子树的长度=中序数组中根节点的索引-第一个数的索引
        //使用递归方法创建二叉树即可（没太懂）
        //创建左子树
        root.left = method(pre, preL + 1, preL + leftTreeSize, inL);
        //创建右子树
        root.right = method(pre, preL + leftTreeSize + 1, preR, inL + leftTreeSize + 1);
        return root;
    }
}
