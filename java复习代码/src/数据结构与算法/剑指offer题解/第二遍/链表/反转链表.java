package 数据结构与算法.剑指offer题解.第二遍.链表;

import 数据结构与算法.剑指offer题解.ListNode;

public class 反转链表 {
public ListNode reverseList(ListNode head){
    if (head == null || head.next == null){
        return head;
    }
    ListNode cur_pre = null;
    ListNode cur_next = null;
    while (head != null){
        //记录当前节点的下一个jed
        cur_next = head.next;
        head.next = cur_pre;
        cur_pre = head;
        head = cur_next;
    }
    return cur_pre;
}

}
