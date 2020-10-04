package 数据结构与算法.第二遍.链表;

import 数据结构与算法.LeetCode题解.ListNode;

public class 两个单向链表的第一个公共结点 {
    public ListNode theFirstNode(ListNode pHead1,ListNode pHead2){
        if (pHead1 == null || pHead2 == null){
            return null;
        }
      ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while (p1 != p2){
            //先遍历自己的链表，结束后就转到对方链表的头部
            if (p1 == null){
                p1 = pHead2;
            }else {
                p1 = p1.next;
            }
            //同理
            if (p2 == null){
                p2 = pHead1;
            }else {
                p2 = p2.next;
            }
        }
        return p1;
    }
}
