package 数据结构与算法.剑指offer第一版.链表;

/**
 * 题目：输入两个单向链表，找出它们的第一个公共结点。
 *
 *【解】
 * 首先明确，题目的意思是是说：这两个链表不是单独的两个链表，
 * 而是他们有交叉点，也就是在某一个节点处汇聚一个链表
 * 即这两个链表构成一个“Y”型，因为一个结点只能有一个next元素
 *
 【法一】：最简单，但应该不是面试期待的答案，这里不详细展开
         使用HashMap 保存第一个链表结点之间的关系，再比较第二个链表

 【法二】：双指针法，推荐

 （1）思路：
 设 链表A 的长度为 a + c，链表B 的长度为 b + c，其中 c 为尾部公共部分长度，可知 a + c + b = b + c + a。
 当访问链表 A 的指针P1访问到链表尾部时，令它从链表 B 的头部重新开始访问链表 B；
 同样地，当访问链表 B 的指针P2访问到链表尾部时，令它从链表 A 的头部重新开始访问链表 A。
 此时P1在长链表中已经多走了k步(k为两个链表的长度差值)，于是P1和P2已经位于同一起跑线上了，往后遍历找到相同节点即可。

 （2）具体过程：
 创建两个指针p1和p2,分别指向两个链表的头结点，然后依次往后遍历。
 如果某个指针到达末尾，则将该指针指向另一个链表的头结点；
 如果两个指针所指的节点相同，则说明找到了，于是循环结束，返回当前指针指向的节点。
 其实该方法主要就是用链表循环的方式替代了长链表指针先走k步这一步骤。

 比如，设两个链表分别为：1->3->4->5->6（短）和2->7->8->9->5->6（长）。
 （易知，这两个链表在结点5处开始汇聚，后面5->6都是相同，为重合结点块）
 短链表的指针p1会先到达尾部，然后重新指向长链表头部，
 当长链表的指针p2到达尾部时，重新指向短链表头部，
 此时p1在长链表中已经多走了k步(k为两个链表的长度差值)，p1和p2位于同一起跑线，往后遍历找到相同节点即可。

 */
class ListNode51 {
    int val;
    ListNode51 next = null;

    ListNode51(int val) {
        this.val = val;
    }
}

public class Linkedlist51两个单向链表的第一个公共结点 {
    public ListNode51 FindFirstCommonNode(ListNode51 pHead1, ListNode51 pHead2) {//传入两个链表的头结点
        //1.若两个链表均为null，则直接返回null（这也是一个结点，即空节点，符合返回值类型）
        if (pHead1 == null || pHead2 == null)
            return null;
        //2.创建两个指针p1和p2,分别指向两个链表的头结点，然后依次往后遍历
        ListNode51 l1 = pHead1;
        ListNode51 l2 = pHead2;
        while (l1 != l2) {//若l1和l2不相等，则说明还没相遇（没到公共点），开始遍历
            //2.1分别遍历链表，当遍历到末尾时，把指针指向另一个链表的头部
            //这里用的是三目表达式，比较简洁，推荐
            l1 = (l1 == null) ? pHead2 : l1.next;
            l2 = (l2 == null) ? pHead1 : l2.next;
        }
        //2.2返回公共点，此时l1=l2，因此返回任意一个结点均可
        return l1;//return l2也一样的
    }
}
