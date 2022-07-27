package 数据结构与算法.离职后刷题.第四遍必会版;
import 数据结构与算法.LeetCode题解.ListNode;
/**
 * 单向链表的快排
 * （洛特一面）
 * 1）普通快排的思路
 * 选择1个结点为中心点,保证中心点左边比中心点小,中心点右边比中心点大即可。
 * 这就是一次快排，确定一个数的正确位置，然后进行递归。
 *
 * 2）单链表的实现为
 * 使第一个节点为中心点
 * 创建2个指针(p，q)，p指向头结点，q指向p的下一个节点
 * q开始遍历,如果发现q的值比中心点的值小，则此时p=p->next，
 * 并且执行当前p的值和q的值交换，q遍历到链表尾即可
 * 把头结点的值和p的值执行交换。此时p节点为中心点,并且完成1轮快排
 * 此时再使用递归的方法即可完成排序
 * @author :zoutongkun
 * @date :2022/4/18 6:36 下午
 * @description :
 * @modyified By:
 */
public class demo06_12单向链表的快排 {

    public void quickSort(ListNode begin, ListNode end) {
        //判断为空，判断是不是只有一个节点
        if (begin == null || end == null || begin == end) {
            return;
        }
        //从第一个节点和第一个节点的后面一个结点
        ListNode first = begin;
        ListNode second = begin.next;

        int nMidValue = begin.val;
        //结束条件，second到最后了
        while (second != null && second != end.next) {
            if (second.val < nMidValue) {
                first = first.next;
                //判断一下，避免后面的数比第一个数小，不用换的局面
                if (first != second) {
                    int temp = first.val;
                    first.val = second.val;
                    second.val = temp;
                }
            }
            second = second.next;
        }
        //判断，有些情况是不用换的，提升性能
        if (begin != first) {
            int temp = begin.val;
            begin.val = first.val;
            first.val = temp;
        }
        //前部分递归
        quickSort(begin, first);
        //后部分递归
        quickSort(first.next, end);
    }

    //测试
    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(5);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(8);
        ListNode l5 = new ListNode(4);
        ListNode l6 = new ListNode(2);
        ListNode l7 = new ListNode(1);

        /*ListNode p = l1;
        System.out.println(p.equals(head));
        System.out.println(p == head);*/

        head.next = l1;
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = null;

        ListNode p = head;
        while (p.next != null) {
            System.out.print(p.val);
            p = p.next;
        }
        System.out.print(p.val);
        System.out.println();

        ListNode begin = head, end = p;
        new demo06_12单向链表的快排().quickSort(begin, end);

        p = head;
        while (p != null) {
            System.out.print(p.val);
            p = p.next;
        }
    }
}

