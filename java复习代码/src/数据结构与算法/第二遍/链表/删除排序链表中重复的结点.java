package 数据结构与算法.第二遍.链表;

import 数据结构与算法.ListNode;

public class 删除排序链表中重复的结点 {

    /**
     * 入门版：重复元素还留一份
     */
    public ListNode deleteDuplicates01(ListNode head) {
        //特判
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        while (cur != null && cur.next != null) {
            //若有重复元素，则删除
            if (cur.next.val == cur.val) {
                cur.next = cur.next.next;
            }
            //
                cur = cur.next;
        }
        //最后，返回新链表的头结点即可
        return head;
    }

    /**
     * 进阶版：重复元素全删除（掌握）
     * @param head
     * @return
     */
    public ListNode deleteDuplicates02(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        //定义一个哑节点，用于指向头结点（因为原头结点也有可能是重复节点）
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p1 = dummy;//慢指针
        ListNode p2 = head;//快指针
        while (p2 != null && p2.next != null) {
            if (p1.next.val != p2.next.val) {
                p1 = p1.next;
                p2 = p2.next;
            } else {
                while (p2 != null && p2.next != null && p1.next.val == p2.next.val) {
                    p2 = p2.next;
                }
                p1.next = p2.next;
                p2 = p2.next;
            }
        }
        return dummy.next;
    }
}

