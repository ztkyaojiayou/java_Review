package 数据结构与算法.LeetCode题解.链表;
/**
 * 160. 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 */

import 数据结构与算法.LeetCode题解.ListNode;

/**
 * 思路解析：该题与剑指offer的第51题相同，那儿也讲得很清楚，这里不再赘述。
 * 主要思路为：（自己画一下图立马就能明白）
 *  创建两个指针p1和p2,分别指向两个链表的头结点，然后依次往后遍历。
 *  如果某个指针到达末尾，则将该指针指向另一个链表的头结点，而另一个指针则不变，然后继续向后遍历；
 *  如果两个指针所指的节点相同，则循环结束，返回当前指针指向的节点。
 *  其实该方法主要就是用链表循环的方式替代了长链表指针先走k步这一步骤。
 *
 *  比如，设两个链表分别为：1->3->4->5->6（短）和2->7->8->9->5->6（长）。
 *  （易知，这两个链表在结点5处开始汇聚，后面5->6都是相同，为重合结点块）
 *  短链表的指针p1会先到达尾部，然后重新指向长链表头部，而此时p2不变，继续一起向后遍历，
 *  当长链表的指针p2到达尾部时，重新指向短链表头部，而此时p1不变，继续一起向后遍历，
 *  此时p1在长链表中已经多走了k步(k为两个链表的长度差值)，p1和p2位于同一起跑线，往后遍历找到相同节点即可。
 * 可以理解成两个人速度一致， 走过的路程一致。那么肯定会同一个时间点到达终点。
 * 如果到达终点的最后一段路两人都走的话，那么这段路上俩人肯定是肩并肩手牵手的。
 *
 * 参考链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/tu-jie-xiang-jiao-lian-biao-by-user7208t/
 */
public class 找到相交链表的相交点160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //特判
        if (headA == null || headB == null) {
            return null;
        }
         //定义两个指针，分别指向各自的头结点
        ListNode pA = headA, pB = headB;
        //开始遍历（当相遇时就退出，此时表示已经找到了相交节点）
        while (pA != pB) {
            //三目表达式的写法，骚气
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
            /**
             * 传统写法：
             *   if (pA == null) {
             *                 pA = headB;
             *             }else {
             *                 pA = pA.next;
             *             }
             *             if (pB == null) {
             *                 pB = headA;
             *             }else {
             *                 pB = pB.next;
             *             }
             */

        }
        return pA;
    }
}
