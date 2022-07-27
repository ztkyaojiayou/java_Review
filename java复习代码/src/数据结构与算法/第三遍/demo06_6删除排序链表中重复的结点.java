package 数据结构与算法.第三遍;

import 数据结构与算法.ListNode;

public class demo06_6删除排序链表中重复的结点 {
    /**
     * 入门版：重复元素还留一份（字节一面）
     */
    public ListNode deleteDuplicates01(ListNode head) {
        //定义一个指针，用于遍历
        ListNode cur = head;
        while (cur != null || cur.next != null) {//即当还有元素时，就不断处理
            if (cur.val == cur.next.val) {
                //跳过该相同元素即可，此时cur这个元素还在，然后再循环判断是否还相等即可
                cur.next = cur.next.next;
            }
            //此时的cur就是最后一个重复元素，next就是下一个非重复元素了
            cur = cur.next;
        }
        //直接返回head即可，因为它肯定不会被删除（即使第二个元素与之相同，也是删除第二个元素）
        return head;
    }

    //自写一遍
    public ListNode deleteDuplicates001(ListNode head) {
        ListNode cur = head;
        while (cur != null || cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * 进阶版：重复元素全删除（掌握）
     * 此时就需要使用哑结点了，因为头结点有可能被删除呀
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates02(ListNode head) {
        //哑结点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //定义两个指针
        ListNode p1 = dummy;
        //用于遍历
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            //若不相等，就二者一直跑，直到相等
            if (p1.next.val != p2.next.val) {
                p1 = p1.next;
                p2 = p2.next;
            } else {
                //若相等，就让p2一直跑，直到不相等
                while (p1.next.val == p2.next.val) {
                    p2 = p2.next;
                }
                //这个就表示进行了连接，即使用p1进行连接
                p1.next = p2.next;
                p2 = p2.next;
            }
        }
        //最终返回新链表的头结点即可，此时这个哑结点就起作用了！
        return dummy.next;
    }

    //自写一遍
    public ListNode deleteDuplicates002(ListNode head) {
        //定义哑结点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //定义两个指针
        ListNode p1 = dummy;
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            if (p1.next.val != p2.next.val) {
                p1 = p1.next;
                p2 = p2.next;
            } else {
                while (p1.next.val == p2.next.val) {
                    p2 = p2.next;
                }
                p1.next = p2.next;
                p2 = p2.next;
            }
        }

        return dummy.next;
    }
}
