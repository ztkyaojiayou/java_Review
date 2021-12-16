package 数据结构与算法.剑指offer第一版.二叉树;


import java.util.ArrayList;

/**
 * 题目：给定一棵二叉搜索树，请找出其中的第k小的结点。
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。
 * 例如， （5，3，7，2，4，6，8）中，按结点数值大小顺序第三小结点的值为4。
 *
 * 【解】：(1)首先，二叉搜索树的结点特点为：左边<中间<右边
 *  (2)具体方法：利用二叉搜索树的中序遍历后的结果是递增序列的特点
 *  则只需要用中序遍历的方法遍历这棵二叉搜索树，就很容易找出它的第k大结点。
 *
 */
class TreeNode17 {
    int val = 0;
    TreeNode17 left = null;
    TreeNode17 right = null;

    public TreeNode17(int val) {
        this.val = val;

    }
}

public class tree17二叉搜索树的第k小的结点 {

//用于存储中序遍历后的结果
    ArrayList<TreeNode17> list = new ArrayList<>();

    TreeNode17 KthNode(TreeNode17 root, int k) {
        //1.自定义一个中序遍历方法addNode，通过传入的根节点pRoot进行中序遍历，把结果存入list中
        inOrder(root);

        //2.再查找第k小结点：
        //易知，此时的list已经是一个递增的有序序列啦，因此，对于第k小的数即为第k个数，非常简单
        if(k>=1 && list.size()>=k) {//只有序列长度不小于k，才存在“第k小的数”的说法
            return list.get(k-1);//索引是从0开始的，因此第k个数即为索引为k-1处的值
        }
        return null;
    }

    // 1.1中序遍历的具体实现（使用递归）
    void inOrder(TreeNode17 cur) {
        if(cur != null) {
            inOrder(cur.left);
            list.add(cur);//把结点值存储到list中
            inOrder(cur.right);
        }
    }
}
