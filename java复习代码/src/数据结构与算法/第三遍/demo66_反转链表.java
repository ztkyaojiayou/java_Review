package 数据结构与算法.第三遍;

import 数据结构与算法.LeetCode题解.ListNode;

public class demo66_反转链表 {
    public ListNode ReverseList(ListNode head) {
if (head == null || head.next == null){
    return head;
}
ListNode cur_pre = null;
ListNode cur_next = null;
while (head != null){
    cur_next = head.next;
    head.next = cur_pre;
    cur_pre = head;
    head = cur_next;
}
return cur_pre;//因为cur_pre最终移动到了最末尾，成为了新链表的头结点
    }
}
