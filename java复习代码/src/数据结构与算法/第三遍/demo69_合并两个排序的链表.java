package 数据结构与算法.第三遍;

import 数据结构与算法.LeetCode题解.ListNode;

public class demo69_合并两个排序的链表 {
    //递归法很简单，先会做再说
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }

        //一般情况
        if (list1.val < list2.val){
            //则用list1去连接下一个节点
            list1.next = Merge(list1.next,list2);
            return list1;
        }else{
            list2.next = Merge(list1,list2.next);
            return list2;
        }
    }
}
