package 数据结构与算法.第三遍;

import 数据结构与算法.LeetCode题解.ListNode;

public class demo06_4找到相交链表的相交点 {//即两个链表呈Y型

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = pA.next;
            pB = pB.next;
            if (pA == null) {
                pA = headB;
            }
            if (pB == null) {
                pB = headA;
            }
        }
        //循环退出时就表示他们相遇了，此时返回哪个都行
        return pA;
    }

    //自写一遍
    public ListNode getIntersectionNode01(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            //正常遍历，若到头了则换到对方的头结点继续开始即可
            p1 = p1.next;
            p2 = p2.next;
            if (p1 == null) {
                p1 = headB;
            }
            if (p2 == null) {
                p2 = headA;
            }
        }
        return p1;
    }
}
