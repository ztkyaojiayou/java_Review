package 数据结构与算法.第三遍;

import 数据结构与算法.LeetCode题解.ListNode;

public class demo73_用链表表示的两数相加 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //哑结点
        ListNode dummy = new ListNode(0);
        //用于遍历
        ListNode cur = dummy;
        int carry = 0;//进位
        while (l1 != null || l2 != null || carry != 0){
            int val1 = l1 ==null ? 0:l1.val;
            int val2 = l2 ==null ? 0:l2.val;
            int sum = val1 + val2 + carry;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            //再计算下一个节点
            if (l1 != null){
                l1 = l1.next;
            }
            if (l2  != null){
                l2 = l2.next;
            }
        }
        return dummy.next;
    }
}
