package 秋招笔试.好未来;

import 数据结构与算法.LeetCode题解.ListNode;

public class test05 {
    public ListNode reverseList (ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}
