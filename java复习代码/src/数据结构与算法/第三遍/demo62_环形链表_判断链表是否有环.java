package 数据结构与算法.第三遍;

import 数据结构与算法.LeetCode题解.ListNode;

public class demo62_环形链表_判断链表是否有环 {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
//定义两个指针，一快一慢，一前一后
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            //当快指针的下一个节点为空时，就表示这个链表不是一个环形链表，返回false
            if (fast == null || fast.next == null) {
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;
        }
//只要跳出了循环，就表示相遇，说明有环
        return true;
    }
}
