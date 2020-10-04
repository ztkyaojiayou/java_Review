package 数据结构与算法.第二遍.链表;

import 数据结构与算法.ListNode;

class 环形链表01_判断 {
    //入门版，只判断是否有环
    public boolean hasCycle(ListNode head){
        //特判
        if (head == null && head.next == null){
            return false;
        }
        //快慢双指针，且快指针在慢指针前一个位置
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast){
            if (fast == null || fast.next == null){
                return false;
            }
            //快指针走两步、慢指针走一步
            slow = slow.next;
            fast = fast.next.next;
        }
        //若跳出了循环，则表示有环，于是返回true
        return true;
    }
}

class 环形链表02_找到该入口节点{
public ListNode findTheNodeOfLoop(ListNode head){
    if (head == null || head.next == null){
        return null;
    }
    ListNode fast = head;
    ListNode slow = head;
    while (fast != null && fast.next != null){
        fast = fast.next.next;
        slow = slow.next;
        if (fast == slow){
            break;
        }
    }
    //此时表示快慢指针已经相遇，且相遇点已经找到，于是再定义一个慢指针，
    // 和之前的慢指针一起从头结点开始跑，他们俩相遇的节点即为该环形链表的入口节点，很妙~
    //注意：当然也可以废物利用，把快指针重新指向头结点来当慢指针使用~
    ListNode slow2 = head;
    while (slow != slow2){
        slow = slow.next;
        slow2 = slow2.next;
    }
    return slow;
}
}
