package 数据结构与算法.剑指offer题解.链表;
/**
 * 题目：输入两个递增排序的链表，合并这两个链表并使新链表中的结点仍然是按照递增排序的
 *
 * 【解】：
 *
 * 方法一：直接法
 * 设置一个“哨兵节点”叫 preHead(即代码中的pre），这会让代码写起来非常“清爽”。整体流程如下：
 *
 * 如果 pHead1 和 pHead2，均没遍历完：
 * 如果 pHead1.val <= pHead2.val，那么当前 cur 的 next 指向 pHead1。并且移动 pHead1 指针。
 * 否则，当前 cur 的 next 指向 pHead2，移动 pHead2 指针。
 * 移动 cur 指针
 * 继续循环
 * 否则，结束循环：
 * 如果 pHead1 未遍历完，cur 的 next 指向 pHead1
 * 如果 pHead2 未遍历玩，cur 的 next 指向 pHead2
 *
 * 方法二：递归（最优雅也很简单）
 *
 */

class ListNode31 {
    int val;
    ListNode31 next = null;

    ListNode31(int val) {
        this.val = val;
    }
}
public class Linkedlist31合并两个排序的链表 {
    /**
     * 方法一：直接法
     * @param list1
     * @param list2
     * @return
     */
    public ListNode31 Merge01(ListNode31 list1, ListNode31 list2) {//注意：这里的list1和list2分别指两个链表的头结点
        //1.先定义一个结点pre，插到新链表头结点的前面，其val值任意设置即可，这里设置为0.
        //但是由于这个结点毕竟不是原链表里的值，因此最终返回的链表应该从其下一个结点开始（即为pre.next，见line71）
        //（为什么不直接就用头结点呢？因为头结点本身也要比较）
        ListNode31 pre = new ListNode31(0);//
        //2.再定义一个辅助的临时结点cur，表示当前节点，起初就令其为pre
        // 这个结点是要移动的，但它移动不等于pre结点移动，只是起初令他们二者相等而已，
        ListNode31 cur = pre;
        //3.当这两个链表都没有遍历完时，比较结点list1的值和list2的值，确定cur的指向，同时移动cur
        while(list1 != null && list2 !=null){
            //3.1若list1的值<list2的值,则令当前结点cur（此时cur还在头结点head处）指向/连接到list1结点（以构成递增序列）。
            // 并且同时移动 list1 指针，比较其下一个结点的值与list2的大小。
            //同时移动 cur 指针（line60），用于连接下一个值
            if(list1.val<=list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else{//3.2否则，令当前结点cur（此时cur还在头结点pre处）指向/连接到list1结点（以构成递增序列）
                cur.next = list2;
                list2 = list2.next;
            }
            //3.3连接完一个值后，要移动 cur 指针，用于连接下一个较小值
            cur = cur.next;
        }
        //4.若其中某一个链表没有遍历完，则直接把其拼接到cur结点后面即可
        //4.1若链表list2已经遍历完，但list2还没有遍历完，则直接把剩下的结点连接到cur结点上即可
        //（因为剩下的结点是有序的，且易知其肯定不比cur结点的值小）
        if(list1!=null)
            cur.next = list1;
        //4.2同理，若链表list2已经遍历完，但list2还没有遍历完，则直接把剩下的结点连接到cur结点上即可
        if(list2!=null)
            cur.next = list2;
        return pre.next;//因为pre结点是我们自己添加的，并不是原链表中的值，因此要从其下一个结点开始
    }


    /**
     * 方法二：递归法，思路简单，代码也很好理解
     */

    public ListNode31 Merge2(ListNode31 list1, ListNode31 list2) {
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        if (list1.val <= list2.val) {//此时，list1即为头结点，list1.next即表示要通过递归去连接下一个较小值的结点了
            list1.next = Merge2(list1.next, list2);
            return list1;
        } else {//此时，list2是头结点
            list2.next = Merge2(list1, list2.next);
            return list2;
        }
    }
}

