package 数据结构与算法.剑指offer题解.第二遍.链表;

import 数据结构与算法.剑指offer题解.ListNode;

public class 链表中倒数第K个结点 {
public ListNode findKNode(ListNode head,int k){
    if (head == null || k<=0){
        return null;
    }
    ListNode p1 = head;
    ListNode p2 = head;
    while (head != null && k > 0){
        p1 = p1.next;
        k--;
    }
    if (k > 0){
        return null;
    }
    while (p1 != null){
        p1 = p1.next;
        p2 = p2.next;
    }
    return p2;
}
}
