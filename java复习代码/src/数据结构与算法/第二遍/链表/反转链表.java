package 数据结构与算法.第二遍.链表;

import 数据结构与算法.ListNode;

public class 反转链表 {
public ListNode reverseList(ListNode head){
    if (head == null || head.next == null){
        return head;
    }
    ListNode cur_pre = null;//用于反转
    ListNode cur_next = null;// //用于记录当前节点的下一个节点
    while (head != null){
        //记录当前节点的下一个节点
        cur_next = head.next;
        //反转
        head.next = cur_pre;
        //cur_pre也要往前走，目的是处理下一个节点
        cur_pre = head;//移至反转后的head节点
        //head也要往前走(这也是为什么之前要记录下一个节点原因），目的是处理下一个节点
        head = cur_next;
    }
    return cur_pre;
}

}
