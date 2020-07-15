package 数据结构与算法.LeetCode题解.链表;

/**
 * 206. 反转链表（入门级）
 * 反转一个单链表（即整个链表都反转）。
 *
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */

import 数据结构与算法.LeetCode题解.ListNode;

/**
 * 思路解析：本题和剑指offer的第30题相同，不难。
 *  * 具体做法：以3个结点的链表为例：
 *  * （1）用pre记录当前节点的前一个节点，用next记录当前节点的后一个节点
 *  * （2）若当前节点head不为空，则进入循环，先记录head的下一个节点位置next = b;再让head的指针指向pre
 *  * （3）开始移动pre和head的位置，反转下一个结点，正因为刚才记录了下一个节点的位置，所以该链表没有断，我们让head走向下一个位置b。
 *  * （4）同样地，当前节点为b不为空，先记录下一个节点的位置，让b指向pre的位置即a的位置，同时移动pre和head；
 *  *     当前节点c不为空，记录下一个节点的位置，让c指向b，同时移动pre和head，此时head为空，跳出，返回pre。
 */
class 反转链表206 {
    public ListNode ReverseList(ListNode head) {

        // 1.先判断链表为空或长度为1的情况，返回head
        if(head == null || head.next == null){
            return head;
        }
        //2.再定义两个指针pre和next，一个用于指向当前节点的前一个结点（pre），另一个则指向其后一个结点（next）
        ListNode cur_pre = null; // 用于表示当前节点的前一个节点，由于一开始是头结点head，因此先指定为null
        ListNode cur_next = null; // 用于表示当前节点的下一个节点

        while( head != null){//一直向右走，每反转一个结点就向右走，一直到遍历完整个链表
            //3.开始反转（核心代码）
            cur_next = head.next; // 记录当前节点的下一个节点位置，等会要用（用于head指针右移（line50））
            head.next = cur_pre; // 让当前节点指向前一个节点位置，完成反转（反转的真正代码）

            //3.1反转完一个结点之后，把pre和head结点均右移，处理下一个结点
            cur_pre = head; // pre指向head结点，也即往右走，开始反转下一个结点
            head = cur_next;// 当前节点head也往右继续走，开始反转下一个结点
        }
        //4.当循环结束时,head结点指向了null，pre此时所指的就是反转链表（新链表）的头结点，返回即可
        return cur_pre;

    }
}

/**
 * 92. 反转链表 II(进阶版）
 * 反转从位置 m 到 n 的链表。（即只反转链表的某一部分）
 * 请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 */

/**
 * 思路分析：该题与上一题不同，但也不难。
 * 我们先把该链表分为三段，即：[前面不反转的部分A   要反转的部分B   后面不反转的部分C]
 * 第一步：先通过for循环找到待反转节点的前一个节点pre。
 * 第二步：接着反转m到n这部分（使用上一题的方法即可）。
 * 第三步：再将反转后的部分B的末节点的next指向反转的后面一部分C的头结点。
 * 第四步：最后，再将第一步找到的节点pre指向反转以后的部分B的头节点（易知，该头结点就是未反转之前的末节点）即可。
 *
 * 参考链接：https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/ji-bai-liao-100de-javayong-hu-by-reedfan-6/
 */
class Solution92 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        //1.定义一个预指针pre，用于指向头结点，便于等反转结束后，指向新链表的头结点，也即用于返回最终结果
        ListNode pre = new ListNode(0);
        pre.next = head;
        //2.开始遍历，找到需要反转的那一段的上一个节点
        ListNode node = pre;
        for (int i = 1; i < m; i++) {
            node = node.next;
        }
        //3.反转m到n这一段（与上一题的思路完全相同，只是更换了head而已）
        //此时的node是需要反转的这段的起点的前一个节点,因此需要反转的部分的头结点可以表示为node.next,该节点就相当于上一题的head。
        ListNode nextHead = node.next;//即需要反转的部分的头结点
        //3.1先定义两个指针，一个指向当前节点的前一个节点，一个指向其后一个节点（和上一题完全相同）
        ListNode cur_next = null;
        ListNode cur_pre = null;
        //3.2开始反转（因为只反转一部分，因此只需要一个for循环即可）
        for (int i = m; i <= n; i++) {
            //1）在反转之前先记录当前节点的下一个节点
            cur_next = nextHead.next;
            //2）再开始反转，即令当前节点的下一个节点指向其前一个节点
            nextHead.next = cur_pre;

            //3）再向后移动当前节点及其前一个节点，用于反转下一个节点
            cur_pre = nextHead;
            nextHead = cur_next;
        }

        //4.再将反转的起点（指的是还没有反转之前的那个起点，即node.next）的下一个节点指向当前节点
        // （此时，当前节点为反转之后的头结点）的下一个节点（即后面不反转的部分C的头结点）。
        node.next.next = cur_next;//注意：这里的node是需要反转的这段的起点的前一个节点，在其他地方也是。

        //5.再将需要反转的那一段的头结点的前一个节点（即node节点）指向反转后链表的头结点cur_pre(反转之后它就是头结点啦）
        node.next = cur_pre;

        //6.最后，返回新链表的头结点即为所求（对于整个来说，头结点依然可以表示为预节点的下一个节点，因为预节点的位置并没有改变）。
        return pre.next;
    }
}