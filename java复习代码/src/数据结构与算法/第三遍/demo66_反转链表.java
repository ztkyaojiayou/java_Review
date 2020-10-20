package 数据结构与算法.第三遍;

import 数据结构与算法.LeetCode题解.ListNode;

public class demo66_反转链表 {
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur_pre = null;
        ListNode cur_next = null;
        while (head != null) {
            cur_next = head.next;
            head.next = cur_pre;
            cur_pre = head;
            head = cur_next;
        }
        return cur_pre;//因为cur_pre最终移动到了最末尾，成为了新链表的头结点
    }
}

/**
 * 反转部分链表
 */
class Solution02 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        //找到需要反转的那一段的上一个节点。
        for (int i = 1; i < m; i++) {
            node = node.next;
        }
        //node.next就是需要反转的这段的起点。
        ListNode tar_head = node.next;
        ListNode cur_next = null;
        ListNode cur_pre = null;
        //反转m到n这一段
        for (int i = m; i <= n; i++) {
            cur_next = tar_head.next;
            tar_head.next = cur_pre;
            cur_pre = tar_head;
            tar_head = cur_next;
        }
        //将反转的起点的next指向next。
        node.next.next = cur_next;
        //需要反转的那一段的上一个节点的next节点指向反转后链表的头结点
        node.next = cur_pre;
        return dummy.next;
    }
}