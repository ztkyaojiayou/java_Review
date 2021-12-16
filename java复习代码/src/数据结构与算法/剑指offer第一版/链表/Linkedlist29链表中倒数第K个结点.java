package 数据结构与算法.剑指offer第一版.链表;

/**
 * 题目：输入一个链表，输出该链表中倒数第k 个结点．
 * 为了符合大多数人的习惯，本题从 1 开始计数，即链表的尾结点是倒数第 1 个结点．
 * 例如一个链表有 6 个结点，从头结点开始它们的值依次是 1 、2、3、4、5 、6。这个个链表的倒数第 3 个结点是值为 4 的结点。
 *
 * 【思路】
 *   两个指针:设链表的长度为 N，
 *      （1）设置两个指向头结点head的指针 P1 和 P2，
 *      （2）先让 P1 移动 K 个节点，则还有 N - K 个节点可以移动。
 *      （3）此时再让 P1 和 P2 同时移动，
 *      （4）易知当 P1 移动到链表结尾时，P2 移动到第 N - K 个节点处，该位置就是倒数第 K 个节点。
 *
 * 【注意】：特殊值的处理
 *  - 当输入的head为null时，直接return null;
 *  - 当输入k = 0时，由于我们是从1开始计数的，所以查找倒数第0个结点无意义，直接return null;
 *
 *  【相关题目】
 * - 求链表的中间结点
 * - 判断单向链表是否形成了环形结构？
 *  解：
 *      快慢指针，快指针一次走两步，慢指针一次走一步。
 *      若快指针走到头了，慢指针指向的就是中间结点，
 *      若快指针追上了慢指针，则是环形链表。
 */
class ListNode29 {
    int val;
    ListNode29 next = null;
    ListNode29(int val) {
        this.val = val;
    }
}
public class Linkedlist29链表中倒数第K个结点 {
    public ListNode29 FindKthToTail(ListNode29 head, int k) {
        //当输入的head为null或k<=0时，无意义，直接return null;
        if (head == null || k <=0)
            return null;
        //1.先定义一个指向头结点head的指针p1，让其先跑k个结点
        ListNode29 P1 = head;//注意：head结点并不是第一个有value值的结点
        //先让p1跑k个结点（再和p2一起跑）
        while (P1 != null && k > 0){
            P1 = P1.next;
            k--;
        }
        //若链表元素还没有k个时，则倒数第k个元素必为空
        if (k > 0){
            return null;
        }
        //2.再定义一个指向头结点head的指针p2，让其和已经跑了k个结点的p1指针一起跑
        ListNode29 P2 = head;
        //当P1 != null时，继续跑，当它移动到链表结尾时（即p1=null，跳出循环）
        //此时p2刚好跑到了倒数第k个结点，即为所求，返回即可
        while (P1 != null) {
            P1 = P1.next;
            P2 = P2.next;
        }
        return P2;
    }
}
