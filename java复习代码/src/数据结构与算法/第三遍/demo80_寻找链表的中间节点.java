package 数据结构与算法.第三遍;

import 数据结构与算法.LeetCode题解.ListNode;

/**
 * （1）问题
 * 给出任意单向链表，找出并返回该链表的中间节点。
 *
 * 奇数长度的链表时，例如：1->2->3->4->5
 * 返回节点 3
 *
 * 偶长度的链表时，例如：1->2->3->4->5->6
 * 返回节点 4
 *
 * （2）方法：双指针
 * 设置两个指针，一个快指针，每次走两步，一个慢指针，每次走一步，
 * 当快指针为空(偶数个节点)或者快指针的next指针指向空时(奇数个节点)，
 * 此时慢指针即为中间节点
 */
public class demo80_寻找链表的中间节点 {
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
