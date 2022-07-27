package 数据结构与算法.离职后刷题.第四遍必会版;

import 数据结构与算法.LeetCode题解.ListNode;

public class demo06_2环形链表_判断链表是否有环 {
    //自写一遍（已通过，推荐，因为和找到入口结点的逻辑保持一致）
    public boolean hasCycle02(ListNode head) {
        //其实两个指针都在head也行，若有环则最终也总会相遇
        ListNode slow = head;
        ListNode fast = head;

        // 先跑--使用while(true)
        // 注意：因为是无限循环，因此在循环体外不能写return了，
        // 因为是不可能被执行的，因此必须在循环体里面返回！！！
        while (true) {
            //快慢指针一起跑
            slow = slow.next;
            fast = fast.next.next;
            //若快指针为空，说明肯定无环
            if (fast == null || fast.next == null) {
                return false;
            }
            //若相遇，则表示有环，返回true即可
            if (slow == fast) {
                return true;
            }
        }
    }


    public boolean hasCycle(ListNode head) {
        //定义两个指针，一快一慢，一前一后
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            //当快指针的下一个节点为空时，即肯定无环
            // （因为有环的话，总会有下一个结点，因为是循环呀），
            // 就表示这个链表不是一个环形链表，返回false
            if (fast == null || fast.next == null) {
                return false;
            }
            //开始跑，慢指针一次一步，快指针一次两步
            slow = slow.next;
            fast = fast.next.next;
        }
        //只要跳出了循环，就表示相遇，说明有环
        return true;
    }


}
