package 数据结构与算法.第二遍.链表;

import 数据结构与算法.LeetCode题解.ListNode;

import java.util.List;

public class 两个单向链表的第一个公共结点 {
    public ListNode theFirstNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while (p1 != p2) {
            //先遍历自己的链表，结束后就转到对方链表的头部
            if (p1 == null) {
                p1 = pHead2;
            } else {
                p1 = p1.next;
            }
            //同理
            if (p2 == null) {
                p2 = pHead1;
            } else {
                p2 = p2.next;
            }
        }
        return p1;
    }

    /**
     * 第三遍-tk.zou
     *
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode test03(ListNode pHead1, ListNode pHead2) {
        //特判
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        //对于每一个结点，先走自己的结点，到头后走对方的结点，必会相遇（即两个指针都指向同一结点）
        //先定义两个指针，分别指向自己的头部
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while (p1 != p2){
            //一直走，到头(即指向null）跳转到对方头结点
            if (p1 == null){
                p1 = pHead2;
            }else {
                p1 = p1.next;
            }
            //p2同理
            if (p2 == null){
                p2 = pHead1;
            }else {
                p2 = p2.next;
            }
        }
        //此时相遇，返回任意一个结点即可
        return p1;
    }
}
