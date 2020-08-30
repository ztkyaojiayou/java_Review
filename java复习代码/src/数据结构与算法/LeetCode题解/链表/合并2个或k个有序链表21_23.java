package 数据结构与算法.LeetCode题解.链表;

/**
 * 21. 合并两个有序链表(入门版）
 * 将两个升序链表合并为一个新的 升序 链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */

import 数据结构与算法.LeetCode题解.ListNode;

/**
 * 思路解析：使用递归，新链表也不需要构造新节点。
 *
 * 终止条件：当两个链表都为空时，表示我们对链表已合并完成。
 * 如何递归：我们判断 l1 和 l2 头结点哪个更小，然后较小结点的 next 指针指向其余结点的合并结果。（调用递归）
 * 返回值：每一层调用都返回排序好的链表头
 * 复杂度分析：时间复杂度和空间复杂度都为：O(m+n)，m 为 l1的长度，n 为 l2 的长度。
 *
 * 参考题解，非常清楚的递归图解：
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/yi-kan-jiu-hui-yi-xie-jiu-fei-xiang-jie-di-gui-by-/
 *
 */
class 合并2个有序链表23 {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ////（易知，可以被下面的递归条件合并，不用单独写）如果 l1 或者 l2 一开始就是空链表 ，
            // 那么没有任何操作需要合并，所以我们只需要返回非空链表。
            //if(l1 == null && l2 == null){
            //    return null;
            //}
            //递归结束的条件，即当 l1 为空或 l2 为空时，此时返回另一个链表即可
            if (l1 == null) {
                return l2;
            }
            else if (l2 == null) {
                return l1;
            }
            //开始递归
            if (l1.val < l2.val) {//如果l1的值小于l2的值，则易知，应该让l1的下一个节点指向“l1原来的下一个节点与l2”的合并结果，
                // 一直推，直到其中一个链表为空，再一级一级地返回结果
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;//返回值表示排好了序的链表头
            }
            else {//同理
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }

        }
    }


/**
 * 23. 合并K个排序链表（进阶版）
 * 合并 k 个排序链表，返回合并后的排序链表。
 * 请分析和描述算法的复杂度。
 *
 * 示例:
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */

/**
 * 思路解析：有了上一题的启发，我们只需对k条链表进行两两合并即可，当然，也是使用的递归。
 * 比如,对于四个链表 A1,A2,A3,A4,
 * 我们可以先合并A1和A2，将这两个链表变成A1-A2，并把其看成一个新的大的链表；
 * 然后再按照两两合并的方式，合并A1-A2和A3，这三个链表就合并成了A1-A2-A3，同样，将其看成一个新的大的链表，
 * 最后将A1-A2-A3跟A4两两合并，至此，四个链表就合并完啦~
 *
 * 参考链接，图解很清晰：
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/duo-tu-yan-shi-23-he-bing-kge-pai-xu-lian-biao-by-/
 */
class 合并k个有序链表23 {
        public ListNode mergeKLists(ListNode[] lists) {
            //结果链表的头节点（注意：对于一个链表，只需要返回它的头结点即可，同样地，对于一颗二叉树，只需返回它的根节点即可，
            //LeetCode后台会根据该节点遍历整个链表或二叉树的，不用我们去遍历）
            ListNode res = null;
            // 遍历的传统写法为：
            // for(int i=1;i<lists.length;i++) {
            //			res = merge(res,lists[i]);
            //		}
            //遍历每一个链表，并开始两两合并
            //注意：每一次新的合并，都是把前一次的结果和新的链表进行合并因此要传入上一次的合并结果
            for (ListNode list: lists) {
                res = mergeTwoLists(res, list);
            }
            //返回合并之后的链表的头结点即可，即为所求。
            return res;
        }

        //两个有序链表的合并的具体实现（照搬上一题的代码）
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ////（易知，可以被下面的递归条件合并，不用单独写）如果 l1 或者 l2 一开始就是空链表 ，
        // 那么没有任何操作需要合并，所以我们只需要返回非空链表。
        //if(l1 == null && l2 == null){
        //    return null;
        //}
        //递归结束的条件，即当 l1 为空或 l2 为空时，此时返回另一个链表即可
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        //开始递归
        if (l1.val < l2.val) {//如果l1的值小于l2的值，则易知，应该让l1的下一个节点指向“l1原来的下一个节点与l2”的合并结果，
            // 一直推，直到其中一个链表为空，再一级一级地返回结果
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;//返回值表示排好了序的链表头
        }
        else {//同理
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }

}
