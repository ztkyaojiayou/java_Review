package 数据结构与算法.剑指offer题解.链表;

/**
 * 题目：一个链表中包含环，如何找出环的入口结点？
 *
 * 【解】：双指针法，一快一慢
 * 这题我们可以采用双指针解法，一快一慢指针。快指针每次跑两个元素，慢指针每次跑一个。
 * 如果存在一个圈，总有一天，快指针是能追上慢指针的。
 *
 * 我们先找到快慢指针相遇的点，设为p。我们再假设，环的入口在点q，
 * 从头节点到点q距离为A，q p两点间距离为B，p q两点间距离为C。
 * 因为快指针是慢指针的两倍速，当他们同时从起点出发时（假设为顺时针走），
 * 他们在某个时刻在p点相遇，则可以得到等式 2(A+B) = A+B+C+B.（时间相同，速度快一倍，则位移也大一倍）
 * 则有：C = A，也即当他们俩同时相遇在p点时，slow结点和头结点（起点）离入口结点的距离是相等的。
 * 这时，因为我们的slow指针已经在p，我们可以新建一个另外的指针slow2，让他从头节点开始走，
 * 每次只走一个，原slow指针继续保持原来的走法（顺时针），和slow2同样，每次只走一个。
 * 因为A=C，所以当他们肯定会在入口p处相遇了，我们返回slow2或者slow中任意一个节点即可，
 * 因为此刻他们指向的是同一个节点，即环的起始点q。
 *
 * 双指针，且一开始都指向头结点：
 * Fast：一次走两步、Slow：一次走一步
 * 1）如果有环，则Fast与Slow一定会在环中相遇。
 * 相遇时，再来一个指针Slow2：一次走一步，（原Slow：也一次走一步）
 * 则Slow2与Slow 一定会在入环的第一个结点相遇【一定】，则可返回其中任意一个结点即可。
 * 2）如果无环，则直接return null;
 */
class ListNode09 {
    int val;
    ListNode09 next = null;

    ListNode09(int val) {
        this.val = val;
    }
}
public class Linkedlist09链表中环的入口结点 {
    private ListNode09 EntryNodeOfLoop(ListNode09 head) {
        //1.先定义两个指针，一快一慢
        ListNode09 fast = head;
        ListNode09 slow = head;
        //2.若链表中没有环，则直接返回null（废话，但也要考虑周全）
        if (fast == null || fast.next == null) {
            return null;
        }
        while (fast != null && fast.next != null) {// 不用再判断slow指针，因为slow指针比fast慢，所以肯定安全
            //3.让fast指针一次走两步，slow指针一次只走一步
            fast = fast.next.next;//fast指针一次走两步
            slow = slow.next;//slow指针一次只走一步
            //3.当他们相遇时，退出循环，p点找到
            if (fast == slow) {//（假设指针是顺时针走）说明这两个指针已经相遇(务必注意，该相遇点并不是入口节点！！！），
                // 此时fast和slow指针都已经在相遇点了，假设为P点
                //由之前的分析易知，此时slow结点和头结点（起点）离入口结点的距离是相等的，
                //此时只需要再使用一个指向头结点的慢指针slow2（就用fast结点重新指向头结点也行），
                // 当slow2和slow（在P点）相遇时，必定在入口处，返回其中任意一个指针即可
                break;
            }
        }
        //4.再使用一个指向头结点的慢指针slow2(当然，也可以废物利用fast指针，让其重新指向头结点）
        ListNode09 slow2 = head;
//        // 或使用fast重新指向第一个结点
//        fast = head;
        while (slow2 != slow) {//slow2和slow指针速度相同，一次都只走一步，当二者相等时，
            // 表明相遇，则一定是在入口处，返回任意一个结点即可
            slow2 = slow2.next;
            slow = slow.next;
        }
        return slow2;//返回slow也行，因为此时他们俩都在入口处啦~
    }
}
