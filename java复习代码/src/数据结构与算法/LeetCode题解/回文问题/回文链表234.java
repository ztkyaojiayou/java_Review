package 数据结构与算法.LeetCode题解.回文问题;

import 数据结构与算法.LeetCode题解.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 *
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */

/**
 * 思路解析：有两种方法
 * （1）额外使用一个list或数组，将链表中的值存入到list中，再使用一头一尾的双指针逐一比对即可（其实也是好方法，只是需要额外的空间而已）
 * （2）反转链表的前/后半边部分+快慢双指针（推荐）
 *
 * 参考链接：https://leetcode-cn.com/problems/palindrome-linked-list/solution/234java-shuang-zhi-zhen-tu-jie-by-ustcyyw/
 */

/**
 * 方法1：利用数组
 * 我们可以分为两个步骤：
 *（1）复制链表值到数组列表中。
 *（2）使用双指针法判断是否为回文。
 *
 * 第一步，我们需要遍历链表将值复制到数组列表中。我们用 currentNode 指向当前节点。
 * 每次迭代向数组添加 currentNode.val，并更新 currentNode = currentNode.next，当 currentNode = null 则停止循环。
 *
 * 第二步，使用双指针法来检查是否为回文。
 * 我们在起点放置一个指针，在结尾放置一个指针，每一次迭代判断两个指针指向的元素是否相同，
 * 若不同，返回 false；相同则将两个指针向内移动，并继续判断，直到相遇。
 *
 * 要注意的是，在编码的过程中，我们比较的是节点值的大小，而不是节点本身。
 * 即正确的比较方式是：node_1.val==node_2.val。而 node_1==node_2 是错误的。
 */
class 回文链表234 {
        public boolean isPalindrome(ListNode head) {
            //1.定义一个list，用于存储链表的节点
            List<Integer> list = new ArrayList<>();

            //2.遍历链表，将链表的节点存入list中
            while (head != null) {
                list.add(head.val);
                head = head.next;
            }

            //3.再使用双指针（一头一尾）来判断链表是否为回文
            int front = 0;//头指针
            int back = list.size() - 1;//尾指针
            while (front < back) {
            //3.1若其对应的值有一组不相等，则直接返回false
                if (!list.get(front).equals(list.get(back))) {
                    return false;
                }
                //3.2否则，开始比对下一个
                front++;
                back--;
            }
            //4.若全部比对完毕之后，都没有返回false，则说明该链表为回文链表，则返回true
            return true;
        }
    }

/**
 * 方法2：反转链表的前/后半边部分+快慢双指针（推荐）
 * 思路分析：
 * （官方版）避免使用 O(n) 额外空间的方法就是改变输入。
 * 我们可以将链表的后半部分反转（修改链表结构），然后将前半部分和后半部分进行比较。
 * 比较完成后我们应该将链表恢复原样。
 * 虽然不需要恢复也能通过测试用例，因为使用该函数的人不希望链表结构被更改。
 *
 * 算法：
 * 我们可以分为以下几个步骤：
 *     找到前半部分链表的尾节点。
 *     反转后半部分链表。
 *     判断是否为回文。
 *     恢复链表。
 *     返回结果。
 *
 * 执行步骤一，我们可以计算链表节点的数量，然后遍历链表找到前半部分的尾节点。
 * 或者可以使用快慢指针在一次遍历中找到：慢指针一次走一步，快指针一次走两步，快慢指针同时出发。
 * 当快指针移动到链表的末尾时，慢指针到链表的中间。通过慢指针将链表分为两部分。
 * 若链表有奇数个节点，则中间的节点应该看作是前半部分。
 * 步骤二可以使用在反向链表问题中找到解决方法来反转链表的后半部分。
 * 步骤三比较两个部分的值，当后半部分到达末尾则比较完成，可以忽略计数情况中的中间节点。
 * 步骤四与步骤二使用的函数相同，再反转一次恢复链表本身。
 *
 复杂度分析
 时间复杂度：O(n)，其中 nnn 指的是链表的大小。
 空间复杂度：O(1)，我们是一个接着一个的改变指针，我们在堆栈上的堆栈帧不超过 O(1)。
 该方法的缺点是，在并发环境下，函数运行时需要锁定其他线程或进程对链表的访问，因为在函数执执行过程中链表暂时断开。

 * （民间版）回文链表的性质：它关于中垂线是轴对称的，也就是说只要翻转其中一半，则两条"半链表"值出现的顺序是一致的。
 * 因此我们可以将后半链表进行了翻转，额外维持一条新的半链表，则易知此方法还是需要额外的空间。
 * 而官方标答的思路就是，在找中间结点的过程中直接将前半个链表进行翻转。
 * 需要注意在链表节点数为奇数时，也就是当遍历结束后fast != null的情况，要将slow结点跳过中间结点，否则两条半链表不一样长。
 *
 * 时间复杂度O(n)，空间复杂度O(1)
 *
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list/solution/234java-shuang-zhi-zhen-tu-jie-by-ustcyyw/
 */
//该代码反转的是前半链表
class solution234{
        public boolean isPalindrome3(ListNode head) {
            //0.特判
            if (head == null || head.next == null)
                return true;
            //1.0定义一快一慢两个指针，且先均指向头结点，其中，快指针一次走两步，慢指针一次走一步
            //为了找到需要翻转的半条链表的起点，需要遍历半个链表
            ListNode slow = head;
            ListNode fast = head;
            //1.1再定义一个预指针，用于辅助翻转前半条链表
            ListNode pre = null;
            //2.下面是反转前半部分链表的具体过程，与之前的反转链表的思路完全相同
            while (fast != null && fast.next != null) {//这里循环的条件是分了两种情况，即当链表节点为奇数和偶数时，（慢指针不用考虑，边界肯定由快指针决定嘛~）
                //1）当为奇数时，我们希望慢指针slow就指向最中间那个节点,
                //（但我们更希望它指向其后一个节点，因为只有这样，我们才可以真正比较两个等长的半链表了，也正因此，后面专门把这种情况拿出来处理)
                //而当慢指针指向最中间那个节点时，快指针就指向了最后一个节点，此时就应该退出循环了，
                //因此退出循环的条件就应该为fast.next == null
                //2）当为偶数时，我们希望慢指针slow就指向右半边链表的头节点，
                //而当慢指针指向右半边链表的头节点时，快指针指向了最后一个节点的下一个节点，即为null，
                // 此时就应该退出循环了，因此退出循环的条件就应为fast == null
                ListNode cur = slow;
                slow = slow.next;
                fast = fast.next.next;//快指针并不参与链表的反转，只是起到控制循环啥时候终止的目的
                cur.next = pre;
                pre = cur;
            }

            //3.易知，当退出上述循环时，要么是fast == null（当链表节点为偶数时），要么是fast.next == null（当链表节点为奇数时）
            // 而当链表的节点个数为奇数时，此时slow指向的是最中间的那个节点，
            // 但我们要将slow结点跳过中间结点而指向其后一个节点，否则两条半链表不一样长,
            // 且易知，此时的中间节点并不影响该链表是否为回文，因为它只是一个节点。
            if (fast != null) {
                slow = slow.next;
            }

            //4.当反转了前半部分链表之后，此时的slow就处在了后半链表的头结点，
            //而pre则成为了前半部分链表的头结点啦~

            //5.两个半长链表开始比较
            while (pre != null) {
                //5.1若二者对应的值有一个不相等，就直接返回false
                if (pre.val != slow.val)
                    return false;
                //5.2否则，开始遍历每一个节点并比对他们的值是否相等
                slow = slow.next;
                pre = pre.next;
            }
            //6.若全部比对完毕之后，都没有返回false，则说明该链表为回文链表，则返回true
            return true;
        }

    }