package 数据结构与算法.LeetCode题解;

//链表的节点类（通用）
//要返回的链表的节点类
public class ListNode {
    //当前结点的值
    public int val;
    //定义一个节点，用于表示下一个节点
    public ListNode next;

    //有参构造函数
    public ListNode(int x) {
        val = x;
    }
}