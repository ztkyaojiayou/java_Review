package 数据结构与算法.LeetCode题解.链表;

import 数据结构与算法.LeetCode题解.ListNode;

/**
 给出两个 非空 的链表用来表示两个非负的整数。
 其中，它们各自的位数是按照 逆序 的方式存储的（不是指排序的逆序），
 并且它们的每个节点只能存储 一位 数字。
 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

 示例：
 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 输出：7 -> 0 -> 8
 原因：342 + 465 = 807
 */
 //常规计算中是往后进位，然后从前往后读出结果值；
 //那么同理，对于逆序，则也只需反向进位即可
// 注意：（小技巧）对于链表问题，返回结果为头结点时，通常需要先初始化一个预先指针pre，
// 该指针的下一个节点指向真正的头结点head。
// 使用预先指针的目的在于链表初始化时无可用节点值，
// 而且链表构造过程需要指针移动，进而会导致头指针丢失，无法返回结果。
public class 用链表表示的两数相加02 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //定义一个预节点
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        int carry = 0;
        //只要当前节点和进位均不为空时就一直计算下去
        while(l1 != null || l2 != null || carry != 0){
            //取出节点值，用于计算
            int val1 = l1 == null ? 0:l1.val;//使用了三目运算符，可以省去一个if判断，务必掌握
            int val2 = l2 == null ? 0:l2.val;
            //相加，切记，要加上进位
            int sum = val1 + val2 + carry;
            carry = sum/10;//表示进位，取商即可
            //此时第一位的两个值已经计算完毕，于是要把结果放入要返回的链表中，且令当前节点指向该节点。
            //这里的(sum % m)表示个位（因为每一个节点存储的数是不含进位的，这一点务必注意），取余即可。
            head.next = new ListNode(sum % 10);
            head = head.next;
          //两链表都往后移一个节点，进行下一位的计算（务必要判断一下节点是否为空，否则会报异常）
            if(l1 != null) {
                l1 = l1.next;
            }
            if(l2 != null) {
                l2 = l2.next;
            }
        }
        //最后，返回该链表的头结点即可
        return dummy.next;
    }
}
