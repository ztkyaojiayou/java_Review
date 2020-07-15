package 数据结构与算法.剑指offer题解.链表;

/**
 * 题目：定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
 *
 * 【思路】
 * 题目所给的是单链表，想了一下反转后的样子，即：
 * 最后一个结点指向倒数第二个，倒数第二个指向倒数第三个，......，第二个指向第一个，第一个指向null;
 * 知道了反转后各个结点指向哪之后，就需要开始调整每个结点的next指针。
 * 这就需要把结点挨个从链表上摘下来，做调整；
 * 这个调整过程需要两个指针辅助：pre记录当前节点的前一个结点的位置，好让该结点的next指针指向前一个结点，
 * 但是在指向前一个结点前需要用一个指针next记录后一个结点地址，避免结点丢失。
 *
 * 具体做法：以3个结点的链表为例：
 * （1）用pre记录当前节点的前一个节点，用next记录当前节点的后一个节点
 * （2）若当前节点head不为空，则进入循环，先记录head的下一个节点位置next = b;再让head的指针指向pre
 * （3）开始移动pre和head的位置，反转下一个结点，正因为刚才记录了下一个节点的位置，所以该链表没有断，我们让head走向下一个位置b。
 * （4）同样地，当前节点为b不为空，先记录下一个节点的位置，让b指向pre的位置即a的位置，同时移动pre和head；
 *     当前节点c不为空，记录下一个节点的位置，让c指向b，同时移动pre和head，此时head为空，跳出，返回pre。
 *
 * 参考链接：https://www.nowcoder.com/questionTerminal/75e878df47f24fdc9dc3e400ec6058ca?answerType=1&f=discussion
 */
class ListNode30 {
    int val;
    ListNode30 next = null;

    ListNode30(int val) {
        this.val = val;
    }
}
public class Linkedlist30反转链表 {
    //易知这个方法要求返回一个结点，那到底是返回哪个结点呢？
    //答曰：在链表题当中，一般都是返回满足题意的链表的头结点，因为有了头结点，就可以遍历其所有结点啦
    public ListNode30 ReverseList(ListNode30 head) {

        // 1.先判断链表为空或长度为1的情况，返回head
        if(head == null || head.next == null){
            return head;
        }
        //2.再定义两个指针pre和next，一个用于指向当前节点的前一个结点（pre），另一个则指向其后一个结点（next）
        ListNode30 cur_pre = null; // 用于表示当前节点的前一个节点，由于一开始是头结点head，因此先指定为null
        ListNode30 cur_next = null; // 用于表示当前节点的下一个节点

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
