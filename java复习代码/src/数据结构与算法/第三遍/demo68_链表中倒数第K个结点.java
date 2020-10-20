package 数据结构与算法.第三遍;


import 数据结构与算法.LeetCode题解.ListNode;

public class demo68_链表中倒数第K个结点 {
    public ListNode FindKthToTail(ListNode head, int k) {
ListNode p1 = head;
while (p1 != null && k > 0) {
    p1 = p1.next;
    k--;
}
        //若链表元素还没有k个时，则倒数第k个元素必为空
        if (k > 0){
            return null;
        }
ListNode p2 = head;
while (p1 != null){
    p1 = p1.next;
    p2 = p2.next;
}

return p2;
    }
}
