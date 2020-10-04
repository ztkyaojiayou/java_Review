package 数据结构与算法.剑指offer题解.链表;

import 数据结构与算法.ListNode;


/**
 * (入门级）83.删除排序链表中的重复元素（简单）
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例 1:
 *
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 *
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 */

/**
 * 思路解析：这是一个简单的问题，仅测试你操作列表的结点指针的能力。
 * 由于输入的列表已排序，因此我们可以通过将结点的值与它之后的结点进行比较来确定它是否为重复结点。
 * 如果它是重复的，我们更改当前结点的 next 指针，以便它跳过下一个结点并直接指向下一个结点之后的结点。
 */
class solution删除链表中重复元素_保留一个{
    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }
}

/**
 * (进阶版）82.题目：在一个排好序的链表中，如何删除重复的结点？
 * (注意：是要求重复元素都删除，而不是还保留一个,这就是区别）
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
public class Linkedlist10删除排序链表中重复的结点 {
//方法一：非递归法（要画图分析，不然指针指向会乱）
public ListNode deleteDuplicates(ListNode head) {
    if(head==null || head.next==null) {
        return head;
    }
    //哑节点，用于处理头结点，因为头节点有可能同样是重复节点，所以需要创建一个空节点作为链表的真正头节点。
    ListNode pre_node = new ListNode(0);
    pre_node.next = head;
    //定义双指针，一前一后
    ListNode p1 = pre_node;//后/慢
    ListNode p2 = head;//前/快
    while(p2!=null && p2.next!=null) {
        //初始化时a指向的是哑结点，所以比较逻辑应该是a的下一个节点和b的下一个节点
        if(p1.next.val!=p2.next.val) {//若不相等，则均右移
            p1 = p1.next;
            p2 = p2.next;
        } else {
            //而若双指针指向的节点的值相等，就不断移动p2，直到二者指向的值不相等
            while(p2!=null && p2.next!=null && p1.next.val==p2.next.val) {
                p2 = p2.next;
            }
            /**
             * 我们思考一下如何才能将重复的元素全部删除呢？
             * 我们可以将重复的元素视为单独的一份链表，构建新的指针temp去操作，
             * 有重复就向下走，一直走到这个链表的最后一个节点，然
             * 后将原链表内的cur指针指向temp的下一个节点。
             */
            //此时二者指向的值不相等，于是把p1和p2的下一个元素连起来，
            //从而达到了删除重复元素的效果，同时p2指针继续右移
            p1.next = p2.next;
            p2 = p2.next;
        }
    }
    //返回头结点，即为去重后的新链表
    return pre_node.next;
}


    //方法二：递归法：较简单，但面试不推荐
    public ListNode10 deleteDuplication(ListNode10 head) {
        if (head == null || head.next == null)// 若只有0个或1个节点，直接返回此节点即可
            return head;
        ListNode10 head_next = head.next;
        if (head.val == head_next.val) {// 当前节点是重复节点
            // 跳过值与当前节点相同的全部节点，找到第一个与当前节点不同的节点
            //退出循环时，next指向第一个不重复值
            while (head_next != null && head.val == head_next.val){
                head_next = head_next.next;
            }
            return deleteDuplication(head_next);// 从第一个与当前结点不同的结点继续递归
        } else {// 当前节点不是重复节点
            head.next = deleteDuplication(head.next);// 保留当前节点，从下一个节点继续递归
            return head;
        }
    }
}
