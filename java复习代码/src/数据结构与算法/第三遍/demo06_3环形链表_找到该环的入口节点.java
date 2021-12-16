package 数据结构与算法.第三遍;

import 数据结构与算法.LeetCode题解.ListNode;

public class demo06_3环形链表_找到该环的入口节点 {
    public ListNode detectCycle(ListNode head) {

        //定义快慢指针，但先都指向头结点
        ListNode slow = head;
        ListNode fast = head;
        while (true){//先跑
            slow = slow.next;
            fast = fast.next.next;
            if (fast == null ||fast.next == null){
                return null;
            }
            if (fast == slow){//相遇就跳出循环
                break;
            }
        }
        //此时他们相遇，于是把快指针指向head节点，且速度和慢指针相同
        fast = head;
        while (fast != slow){
            slow = slow.next;
            fast  = fast.next;
        }
        return fast;
    }
}
