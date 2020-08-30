package 数据结构与算法.剑指offer题解.第二遍.链表;

import 数据结构与算法.剑指offer题解.ListNode;

public class 用链表表示的两数相加 {
public ListNode addTwoNumbers(ListNode l1,ListNode l2){
    if (l1 == null || l2 == null){
        return null;
    }
    ListNode dummy = new ListNode(0);
    ListNode head = dummy;
    int carry = 0;
    while (l1 != null ||l2 != null || carry != 0){
        int val1 = l1 == null ? 0:l1.val;
        int val2 = l2 == null ? 0:l2.val;
        int sum = val1 + val2 + carry;
        carry = sum / 10;
        ListNode sum_node = new ListNode(sum % 10);
        head.next = sum_node;
        head = head.next;
        //开始计算下一位
        if (l1 != null){
            l1 = l1.next;
        }
        if (l2 != null){
            l2 = l2.next;
        }
    }
    return dummy.next;
}
}
