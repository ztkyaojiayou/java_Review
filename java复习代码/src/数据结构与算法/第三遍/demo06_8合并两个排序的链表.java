package 数据结构与算法.第三遍;

import 数据结构与算法.LeetCode题解.ListNode;

class demo69_合并两个排序的链表 {
    //递归法很简单，先会做再说
    public ListNode Merge(ListNode list1, ListNode list2) {
        //递归出口
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        //一般情况
        if (list1.val < list2.val) {
            //则用list1去连接下一个节点
            list1.next = Merge(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge(list1, list2.next);
            return list2;
        }
    }

    //自写一遍（递归版）
    public ListNode Merge02(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val < list2.val) {
            list1.next = Merge02(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge02(list1, list2.next);
            return list2;
        }
    }
}

/**
 * 进阶版（了解）
 */
class 合并k个有序链表 {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = null;
        for (ListNode list : lists) {
            res = mergeTwoLists(res, list);
        }
        return res;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ////（易知，可以被下面的递归条件合并，不用单独写）如果 l1 或者 l2 一开始就是空链表 ，
        // 那么没有任何操作需要合并，所以我们只需要返回非空链表。
        //if(l1 == null && l2 == null){
        //    return null;
        //}
        //递归结束的条件，即当 l1 为空或 l2 为空时，此时返回另一个链表即可
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        //开始递归
        if (l1.val < l2.val) {//如果l1的值小于l2的值，则易知，应该让l1的下一个节点指向“l1原来的下一个节点与l2”的合并结果，
            // 一直推，直到其中一个链表为空，再一级一级地返回结果
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;//返回值表示排好了序的链表头
        } else {//同理
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }
}
