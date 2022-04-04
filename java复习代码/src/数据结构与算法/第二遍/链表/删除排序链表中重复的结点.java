package 数据结构与算法.第二遍.链表;

import 数据结构与算法.ListNode;

public class 删除排序链表中重复的结点 {

    /**
     * 入门版：重复元素还留一份
     */
    public ListNode deleteDuplicates01(ListNode head) {
        //特判
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        while (cur != null && cur.next != null) {
            //若有重复元素，则删除/跳过该节点指向下一个结点
            if (cur.next.val == cur.val) {
                cur.next = cur.next.next;
            }
            //处理下一个结点
            cur = cur.next;
        }
        //最后，返回新链表的头结点即可
        return head;
    }

    /**
     * 进阶版：重复元素全删除（掌握）
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates02(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        // 1.此时就需要定义一个哑节点（即放在头结点之前的一个值为0的新结点，它没有实际意义）了，用于指向头结点，
        // 易知，此时原链表的结构发生了改变，其头部多了一个（哑）结点
        // 原因是：原头结点也有可能是重复节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //2.再定义两个指针，一前一后，但速度相同
        ListNode p1 = dummy;//前
        ListNode p2 = head;//后
        //约束快指针即可
        while (p2 != null && p2.next != null) {
            if (p1.next.val != p2.next.val) {
                p1 = p1.next;
                p2 = p2.next;
            } else {
                //相等时，别急，继续走，直到不相等为止，此时才删除
                while (p2 != null && p2.next != null && p1.next.val == p2.next.val) {
                    //别急，继续走
                    p2 = p2.next;
                }
                //直到不相等，删除
                p1.next = p2.next;
                //指向不重复的结点
                p2 = p2.next;
            }
        }
        //即返回头结点
        return dummy.next;
    }

    /**
     * 第三遍-tk.zou
     * @return
     */
    public ListNode test03入门版(ListNode head) {
        //1.特判
        if (head == null || head.next == null) {
            return null;
        }
        //定义一个指向/等于头结点的指针用于遍历
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                //才找到一个重复结点，可能有多个，继续找，一锅端！！！
                cur.next = cur.next.next;
                //这里不能加cur = cur.next，这一点非常重要，因为重复元素可能不止一个，只有重复元素都走完后才处理下一个结点
            }
            //再继续往下走，注意：此时的cur已经指在最后（这很关键）那个重复元素上了
            cur = cur.next;
        }
        //最后返回头结点（题目要求）
        return head;
    }

    public ListNode test03进阶版(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        //先定义哑结点，指向头结点，方便返回删除了重复元素之后的新链表的头结点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        //再定义一前一后的两个指针，速度一样，用于遍历
        ListNode p1 = dummy;
        ListNode p2 = head;
        //开始遍历
        while (p2 != null && p2.next != null){
            if (p1.next.val == p2.next.val){
                //此时说明找到重复结点了，别急，继续找出所有重复的结点，一锅端
                p2 = p2.next;
            }
            //此时再调整指针
            p1.next = p2.next;
            p2 = p2.next;
        }
        //返回头结点
        //关键在于：不能直接返回head，因为原head可能被删除了，这也是定义这个哑结点的意义所在！！！
        return dummy.next;
    }
}

