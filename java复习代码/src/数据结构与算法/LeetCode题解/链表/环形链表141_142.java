package 数据结构与算法.LeetCode题解.链表;

import 数据结构与算法.LeetCode题解.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 141. 环形链表（入门版）
 * 给定一个链表，判断链表中是否有环。（判断型）
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 *
 * 进阶：
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 */

/**
 * 解题思路：这是一个使用快慢指针的经典问题，具体思路及步骤如下：
 *（1）创建两个指针，P1（快指针）和P2（慢指针），并且慢指针指向头结点(即head），而快指针指向其下一个节点（即head.next)。
 *（2）遍历链表，每遍历一个节点，指针P1向下移动两个节点，指针P2向下移动一个节点。
 *（3）因为如果存在环，那么快慢指针一定会相遇，因此在链表遍历结束之前，
 *     1）若双指针指向了同一节点，则说明链表有环，返回true即可；
 *     2）否则，当某一个指针遍历完毕都没有相遇，则说明无环，返回false即可。
 */
class 环形链表_判断141 {
    public boolean hasCycle(ListNode head) {
        //0.特判
        if (head == null || head.next == null) {
            return false;
        }
        //1.定义快慢指针，一个在前（指向头结点head），一个在后（即head.next）
        //因为如果起点一样，循环里面第一次判断i=j，直接返回了
        ListNode slow = head;
        ListNode fast = head.next;
        //2.快慢指针开始遍历链表
        while (slow != fast) {
            //2.1若快指针遍历完毕了后都没有相遇，则表示无环，返回false即可
            if (fast == null || fast.next == null) {
                return false;
            }
            //2.2快指针每次走两步，慢指针每次走一步
            slow = slow.next;
            fast = fast.next.next;
        }
        //3.当相遇时（跳出循环）表示有环，返回true即可
        return true;
    }
}

//写法2：do...while
class Solution141_1 {
    public boolean hasCycle(ListNode head) {
        //特判
        if (head == null || head.next == null) {
            return false;
        }
        //定义快慢指针，且都指向头结点
        ListNode slow = head;
        ListNode fast = head;
        do {
            if (fast == null || fast.next == null) {
                return false;
            }
            //快指针每次走两步，慢指针每次走一步
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);
        return true;
    }
}

/**
 * 方法2：使用hashSet（需要用到额外空间，但也不失为一种好方法，也需要掌握）
 * 遍历链表，使用hashSet来存储节点（因为元素不可重复），节点的id作为key。
 * 每遍历一个节点，就判断hashSet中是否已存在此节点，
 * 如果存在则说明节点有环,返回true即可。
 * 如果不存在，就将新节点存到hashSet中，重复上述步骤。
 *
 * 复杂度分析：
 * 时间复杂度：O(n)
 * 空间复杂度：使用了额外的存储空间，所以空间复杂度为O(n)
 */
class solution141_2{
    public boolean hasCycle(ListNode head) {
        // 1.先创建一个set集合存储每一个节点
        Set<ListNode> node_set = new HashSet<>();
        // 2.再开始判断链表中的每一个节点是否在set集合中
        // 2.1先判断头结点
        while (head != null) {
            //1）如果存在则说明节点有环,返回true即可。
            if (node_set.contains(head)) {
                return true;
            } else {//2）如果不存在，就将新节点存到hashSet中
                node_set.add(head);
            }
            //2.2再遍历链表节点，开始判断下一个节点
            head = head.next;
        }
        // 3.若最后遍历完所有节点都没有发现环，则返回false即可
        return false;
    }
}

/**
 * 142. 环形链表 II(进阶版）
 * 给定一个链表，返回链表开始入环的第一个节点（也叫入口节点）。 如果链表无环，则返回 null。（要找到这个节点）
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 * 说明：不允许修改给定的链表。
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 */

/**
 * 思路解析：本题和剑指offer的第9题相同，不难。
 *
 * 同样是使用快慢双指针，且一开始都指向头结点，快指针一次走两步，慢指针一次走一步，
 * 当他们第一次相遇时，再让快指针从头结点开始，且变成一次也只走一步，
 * 则当他们第二次相遇时，一定是在环的入口点。（证明很简单，这里略去）
 *
 *参考链接：https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/
 */


class 环形链表_找到该节点142 {
    public ListNode detectCycle(ListNode head) {
        //1.先定义快慢指针，且一开始都指向头结点
        ListNode fast = head, slow = head;
        while (true) {//2.开始向后走
            //2.1特判，即若其中一个走到头了都没有相遇，则说明没有环，直接返回null
            if (fast == null || fast.next == null) {
                return null;
            }
            //2.2快慢指针开始往后走，快指针一次走两步，慢指针一次只走一步
            fast = fast.next.next;
            slow = slow.next;
            //2.3当他们第一次相遇时，直接跳出while循环，此时fast指针和slow指针都指向同一个位置
            if (fast == slow) break;
        }
        //3.接着，令快指针指向头结点，且和慢指针一样，一次也只走一步，而慢指针不变，如此继续往后走
        fast = head;
        //3.1当他们第二次相遇时，相遇的位置即为环的入口点。
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        //3.2此时已经第二次相遇，随便选一个节点返回即为环的入口点
        return fast;
    }
}

/**
 * 关于while中的continue和break：
 * break       直接跳出循环；
 * continue    结束当前循环，继续下一层循环，（不执行 循环体内continue 后面的语句，直接进行下一循环）
 * return      跳出函数，返回调用函数 处。
 * 比如，对于以下语句：
 * int i = 0;
 * while(1)                //                                                  <------.
 * {                       //                                                         |
 *        i++;             //                                                         |
 *        if( i % 2 )      //如果 i %2  >0                                            |
 *            continue;    //跳出当前的if循环，继续while的下一层循环--------------------|
 *
 *        printf("%d",i);
 *        if( i> 10)
 *             break;     //直接跳出整个if循环和while循环，开始执行while之外的语句；----------------.
 *        if( i< 0)       //不会有这种情况:)                                                     |
 *             return ;   //--->跳出当前函数                                                     |
 * }                      //                                                                    |
 * //other lines          //         <<<--------------------------------------------------------:
 */
