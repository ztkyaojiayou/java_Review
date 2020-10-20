package 数据结构与算法.第三遍;

import 数据结构与算法.ListNode;

public class demo67_删除排序链表中重复的结点 {
    /**
     * 入门版：重复元素还留一份（字节一面）
     */
    public ListNode deleteDuplicates01(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode cur = head;
        while (cur != null || cur.next != null) {
            if (cur.val == cur.next.val){
                cur.next = cur.next.next;
            }
            cur = cur.next;
        }
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
        //哑结点
        ListNode dummy = new ListNode(0);
        //定义两个指针
        ListNode p1 = dummy;
        ListNode p2 = head;
        while (p2 != null && p2.next != null){
            if (p1.next.val != p2.next.val) {
                p1 = p1.next;
                p2 = p2.next;
            } else {
                while (p1.next.val == p2.next.val) {//若相等，就让p2一直跑，直到不相等
                    p2 = p2.next;
                }
                p1.next = p2.next;//这个就表示进行了连接，即使用p1进行连接
                p2 = p2.next;
            }
        }
        //最终返回新链表的头结点即可，此时这个哑结点就起作用了！
        return dummy.next;
    }
}
