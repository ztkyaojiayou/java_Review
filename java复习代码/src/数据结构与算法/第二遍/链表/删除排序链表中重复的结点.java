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
            //若有重复元素，则删除/跳过该节点指向下一个结点
            if (cur.next.val == cur.val) {
                cur.next = cur.next.next;
            }
            //处理下一个结点
            cur = cur.next;
        }
        //最后，返回新链表的头结点即可
        return head;
    }

    /**
     * 进阶版：重复元素全删除（掌握）
     *
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

    /**
     * 第三遍-tk.zou
     * @return
     */
    public ListNode test03入门版(ListNode head) {
        //1.特判
        if (head == null || head.next == null) {
            return null;
        }
        //定义一个指向/等于头结点的指针用于遍历
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
                //这里不能加cur = cur.next，这一点非常重要，因为重复元素可能不止一个，只有重复元素都走完后才处理下一个结点
            }
            //再继续往下走，注意：此时的cur已经指在最后（这很关键）那个重复元素上了
            cur = cur.next;
        }
        //最后返回头结点（题目要求）
        return head;
    }

    public ListNode test03进阶版(ListNode head) {

        return head;
    }
}

