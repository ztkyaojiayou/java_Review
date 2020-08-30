package 数据结构与算法.剑指offer题解.第二遍.链表;

import 数据结构与算法.剑指offer题解.ListNode;

//返回值：返回头结点
public class 删除链表的倒数第N个节点 {
public ListNode removeNofEnd(ListNode head,int n){
    //定义一个预节点pre，指向头结点，用于结果返回
    ListNode pre = new ListNode(0);
    pre.next = head;
    ListNode p1 = head;
    ListNode p2 = head;
    for (int i=0;i<n;i++){
        p1 = p1.next;
        if (i == n-1){
            while (p1 !=null)
            p1 = p1.next;
            p2 = p2.next;
        }
    }
    p2.next = p2.next.next;
    return pre.next;
}

}
