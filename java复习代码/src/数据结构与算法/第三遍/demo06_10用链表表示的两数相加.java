package 数据结构与算法.第三遍;

import 数据结构与算法.LeetCode题解.ListNode;

public class demo06_10用链表表示的两数相加 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //哑结点
        ListNode dummy = new ListNode(0);
        //用于遍历,指向哑结点
        ListNode cur = dummy;
        int carry = 0;//进位
        while (l1 != null || l2 != null || carry != 0){//一直计算的条件
            //1.先计算当前两个节点的值并连接成链表
            //1.1获取到节点的值
            int val1 = l1 == null ? 0:l1.val;
            int val2 = l2 == null ? 0:l2.val;
            //1.2再求和
            int sum = val1 + val2 + carry;
            //1.3获取进位
            carry = sum / 10;
            //1.4构造新链表
            cur.next = new ListNode(sum % 10);//连接的是个位
            cur = cur.next;
            //2.再计算下一个节点
            if (l1 != null){
                l1 = l1.next;
            }
            if (l2  != null){
                l2 = l2.next;
            }
        }
        //3.最后，返回新链表的头结点即可
        return dummy.next;
    }
}
