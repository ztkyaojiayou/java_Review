package 数据结构与算法.LeetCode题解.链表;

import 数据结构与算法.LeetCode题解.ListNode;

/**
 *
 给出两个 非空 的链表用来表示两个非负的整数。
 其中，它们各自的位数是按照 逆序 的方式存储的（不是指排序的逆序），并且它们的每个节点只能存储 一位 数字。
 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

 示例：
 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 输出：7 -> 0 -> 8
 原因：342 + 465 = 807
 */
 //常规计算中是往后进位，然后从前往后读出结果值；
 //那么同理，对于逆序，则也只需反向进位即可
//注意：（小技巧）对于链表问题，返回结果为头结点时，通常需要先初始化一个预先指针pre，该指针的下一个节点指向真正的头结点head。
//使用预先指针的目的在于链表初始化时无可用节点值，
//而且链表构造过程需要指针移动，进而会导致头指针丢失，无法返回结果。
public class 两数相加02 {
public ListNode addTwoNumbers(ListNode l1, ListNode l2){
    //创建一个要返回的链表（要素是一个头结点，一个指向下一个节点的next节点，以及其对应的值，这里就是两数之和sum以及进位carry；
    //当要操作链表时，通常还要创建一个指向当前节点的节点cur
    ListNode pre = new ListNode(0);//先创建一个值为0的预节点
    ListNode cur = pre;//表示当前节点
    int carry = 0;//进位
    while (l1 != null|| l2 != null){
        //取出节点值，用于计算
        int x = l1 == null? 0: l1.val;
        int y = l2 == null? 0: l2.val;
        int sum = x + y + carry;
        carry = sum/10;
        sum = sum%10;
        cur.next = new ListNode(sum);//此时第一位的两个值已经计算完毕，再把结果放入要返回的链表中
        //于是，再计算下一位，分别后移一位即可
        cur = cur.next;
        if(l1 != null){//链表1往后移动一位
            l1 = l1.next;
        }
        if (l2 != null){//链表2也同样往后移动一位
            l2 = l2.next;
        }
    }
if(carry == 1){//此时已经算到了最后一位，此时要是有进位（进位就是1），就再创建一个值为1节点，很好理解
    cur.next = new ListNode(carry);
}
return pre.next;//遍历出该链表并返回即可
}
}
