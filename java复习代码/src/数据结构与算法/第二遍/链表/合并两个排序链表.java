package 数据结构与算法.第二遍.链表;

import 数据结构与算法.ListNode;

public class 合并两个排序链表 {
    //方法1：非递归版
public ListNode MergeList(ListNode l1,ListNode l2){
    ListNode pre = new ListNode(0);
    ListNode cur = pre;
    //1.开始比较
    while (l1 != null && l2 != null){
        if (l1.val <= l2.val){
            cur.next = l1;
            l1 = l1.next;
        }else {
            cur.next = l2;
            l2 = l2.next;
        }
        //移动cur节点，用于连接下一个节点
        cur = cur.next;
    }

    //2.若比较到最后，发现其中一个链表为空，则直接连到了一个链表即可
    if (l1 != null){
        cur.next = l1;
    }
    if (l2 != null){
        cur.next = l2;
    }
//3.返回新链表的头结点即可
return pre.next;
}


//递归版
    public ListNode MergeList02(ListNode list1,ListNode list2){
    //递归结束的条件
    if (list1 == null){
        return list2;
    }
    if (list2 == null){
        return list1;
    }
    //一般情况
        if (list1.val <= list2.val){
            list1.next = MergeList02(list1.next,list2);
            return list1;
        }else{
            list2.next = MergeList02(list1,list2.next);
                return list2;
            }
        }
    }

