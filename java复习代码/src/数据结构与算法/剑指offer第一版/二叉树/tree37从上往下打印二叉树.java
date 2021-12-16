package 数据结构与算法.剑指offer第一版.二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：从上往下打印出二叉树的每个结点
 *      （也即：按层打印，且同一层的结点都按照从左向右的顺序打印，但把各行的结果写在一起，
 *      而第15题则是将每一行都放在一个小的list中，再把这些小的list又放入一个大的list中）
 *
 * 【解】：与题15和14的思路完全一样，代码上也只在关键处有几行之差，注意对比理解
 * 并不需要使用两个队列分别存储当前层的节点和下一层的节点，
 * 因为在开始遍历一层的节点时，
 * 当前队列中的节点数就是当前层的节点数，只要控制遍历这么多节点数，就能保证这次遍历的都是当前层的节点。
 *
 *
 * 回顾：
 * 在Java中，Queue是和List、Map同等级别的接口，LinkedList中也实现了Queue接口，该接口中的主要函数有：
 * 1.获取头元素并移除
 * poll() 　　获取并移除此队列的头，如果此队列为空，则返回 null
 * remove()　 获取并移除此队列的头，如果此队列为空，则抛出NoSuchElementException异常
 *
 * 2.获取头元素但不移除
 * peek()　　 获取队列的头但不移除此队列的头。如果此队列为空，则返回 null
 * element() 获取队列的头但不移除此队列的头。如果此队列为空，则将抛出NoSuchElementException异常
 *
 * 3.添加元素到队列
 * offer()　将指定的元素插入此队列（的末尾）（如果立即可行且不会违反容量限制），插入成功返回 true；否则返回 false。
 * 当使用有容量限制的队列时，offer方法通常要优于 add方法——add方法可能无法插入元素，而只是抛出一个  IllegalStateException异常
 * add()　　将指定的元素插入此队列（的末尾），如果容量不够或队列为空，则抛出异常
 */

 class TreeNode37 {
    int val = 0;
    TreeNode37 left = null;
    TreeNode37 right = null;

    public TreeNode37(int val) {
        this.val = val;

    }

}

public class tree37从上往下打印二叉树 {
     //队列：先入先出
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode37 root) {
        Queue<TreeNode37> queue = new LinkedList<>();//用于存储所有节点值，之后再poll出到ret中，用于返回
        ArrayList<Integer> ret = new ArrayList<>();//用于存储从queue中poll出的所有节点值，此时的顺序即满足题目要求，返回
        queue.add(root);//每添加一个结点，同时就poll出到ret中保存
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            while (cnt > 0) {
                TreeNode37 t = queue.poll();//取出节点（先入先出）
                if (t == null)
                    continue;//意义：退出本次循环，进行下次循环
                ret.add(t.val);
                //如此循环，逐个添加其左右结点到queue中，再逐个poll到ret中，最后返回
                queue.add(t.left);
                queue.add(t.right);
                cnt--;
            }
        }
        return ret;
    }
}
