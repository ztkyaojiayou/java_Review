package 数据结构与算法.剑指offer题解.链表;

/**
 * （每太明白）题目：在一个排序的链表中，如何删除重复的结点？(注意：是要求重复元素都删除，而不是还保留一个）
 * 例如，1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5 -> null 中重复结点被删除之后，链表变成了：
 *      1 -> 2 -> 5 -> null
 *
 * 【注意】：
 *      （1）是要求重复元素都删除，而不是还保留一个
 *      （2）头结点也可能被删除，所以在链表头添加一个临时头结点 root。
 *           然后，最后返回 root.next即可
 *      （3）链表是排好序了的，因此同一重复元素肯定相邻
 *      （4）链表中的删除是指：没有指针指向此结点
 *
 * 【思路】：
 * 借助一个临时头结点root作辅助，可避免单独讨论头结点的情况。
 * 设置两个结点 pre 和 cur，当 cur 和 cur.next 值相等，cur 一直向前走，
 * 直到不等退出循环，这时候 cur 指的值还是重复值，调整 cur 和 pre 的指针再次判断
 *
 */
class ListNode10 {
    int val;
    ListNode10 next = null;

    ListNode10(int val) {
        this.val = val;
    }
}
public class Linkedlist10删除链表中重复的结点 {
//方法一：非递归法（没太懂，要画图分析，不然指针指向会乱）
    private ListNode09 deleteDuplication(ListNode09 head) {// head是头结点，也是当前开始处理的结点
        if (head == null) {
            return null;
        }
        //设置一个临时头结点，这个结点的val可以任意设置啦
        ListNode09 root = new ListNode09(Integer.MIN_VALUE);
        root.next = head;       // 临时的头结点

        ListNode09 pre = root;   // 当前结点的前驱结点
        ListNode09 cur = head;   // 当前处理的结点，其实也就是头结点head，这里为便于理解，把它换了个名字而已

        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                while (cur.next != null && cur.next.val == cur.val) {
                    // 若当前节点和后一个结点的值相同，则一直前进
                    cur = cur.next;
                }
                // 退出循环时，cur 指向重复值，也需要删除，而 cur.next 指向第一个不重复的值
                // cur 继续前进
                // pre 连接新结点
                pre.next = cur.next;//pre指向第一个不重复值
            } else {//若当前节点和后一个结点的值不相同，则一直前进
                pre.next = cur;
                pre = pre.next;
            }
            cur = cur.next;
        }
        return root.next;
    }

    //方法二：递归法：较简单
    public ListNode10 deleteDuplication(ListNode10 pHead) {
        if (pHead == null || pHead.next == null)// 若只有0个或1个节点，直接返回此节点即可
            return pHead;
        ListNode10 next = pHead.next;
        if (pHead.val == next.val) {// 当前节点是重复节点
            // 跳过值与当前节点相同的全部节点，找到第一个与当前节点不同的节点
            //退出循环时，next指向第一个不重复值
            while (next != null && pHead.val == next.val)
                next = next.next;
            return deleteDuplication(next);// 从第一个与当前结点不同的结点继续递归
        } else {// 当前节点不是重复节点
            pHead.next = deleteDuplication(pHead.next);// 保留当前节点，从下一个节点继续递归
            return pHead;
        }
    }
}
