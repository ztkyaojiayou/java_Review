package 数据结构与算法.离职后刷题.第四遍必会版;

import 数据结构与算法.LeetCode题解.ListNode;

class 反转链表 {
    public ListNode ReverseList(ListNode head) {
        ListNode cur_pre = null;
        ListNode temp_cur_next;
        while (head != null) {
            temp_cur_next = head.next;
            head.next = cur_pre;
            cur_pre = head;
            head = temp_cur_next;
        }
        //因为cur_pre最终移动到了最末尾，成为了新链表的头结点
        return cur_pre;
    }

    //自写一遍
    public ListNode ReverseList02(ListNode head) {
        ListNode cur_pre = null;
        ListNode cur_next;
        while (head != null) {
            //记录当前结点的下一个结点（原顺序时的下个结点）
            cur_next = head.next;
            //反转
            head.next = cur_pre;
            //此时一轮反转结束，作下一个结点的准备工作
            //把当前结点的前一个结点也后移，即用于反转下一个结点
            cur_pre = head;
            //同时把头结点也移动到下一个结点，相当于同步移动
            head = cur_next;
        }
        //最终变成了反转列表的头结点
        return cur_pre;
    }
}

/**
 * 反转部分链表（较难，了解）
 */
class 反转部分链表 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        //设置一个哑结点，指向头结点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode node = dummy;
        //找到需要反转的那一段的上一个节点。
        for (int i = 1; i < m; i++) {
            node = node.next;
        }
        //node.next就是需要反转的这段的起点。
        ListNode new_head = node.next;
        ListNode cur_next = null;
        ListNode cur_pre = null;
        //反转m到n这一段
        for (int i = m; i <= n; i++) {
            cur_next = new_head.next;
            new_head.next = cur_pre;
            cur_pre = new_head;
            new_head = cur_next;
        }
        //将反转的起点的next指向next。
        node.next.next = cur_next;
        //需要反转的那一段的上一个节点的next节点指向反转后链表的头结点
        node.next = cur_pre;
        return dummy.next;
    }


    //猿辅导一面
//
//    class ListNode {
//        int val;
//        ListNode next;
//
//        ListNode(int val,ListNode next){
//            this.val = val;
//            this.next = next;
//        }
//    }
//
//    public class Main {
//    public static void main(String[] args) {
//    单元测试自己写！！！
//            ListNode p3 = new ListNode(1,null);
//            ListNode p2 = new ListNode(2,p3);
//            ListNode p1 = new ListNode(3,p2);
//
//            ListNode b1 = Main.method(p1);
//            ListNode b2 = b1.next;
//            ListNode b3 = b2.next;
//
//            while(b1 != null){
//                System.out.println(b1.val);
//                b1 = b1.next;
//            }
//
//        }
//
//        private static ListNode method(ListNode head){
//            ListNode cur_pre = null;
//            ListNode cur_next;
//            while(head != null){
//                cur_next = head.next;
//                head.next = cur_pre;
//                cur_pre = head;
//                head = cur_next;
//            }
//            return cur_pre;
//
//        }
//
//    }

}