package 数据结构与算法.第三遍;

import 数据结构与算法.ListNode;

import java.util.ArrayList;
import java.util.List;

//最好的方式是使用：反转前面半链表+再也使用双指针判断
public class demo06_11双指针之回文链表的判断 {
    public boolean isPalindrome(ListNode head) {
        //1.定义一个list，用于存储链表的节点
        List<Integer> list = new ArrayList<>();

        //2.遍历链表，将链表的节点存入list中
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        //3.再使用双指针验证是否为回文
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            //务必注意：这里要使用!equals方法也不是!=符号，因为比较的是Integer类型，而并不是int类型。
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //自写一遍
    public boolean isPalindrome02(ListNode head) {
        //先把结点加入list
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        //再使用双指针比较
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //自写一遍2-使用反转半链表+双指针判断（推荐）
    public boolean isPalindrome021(ListNode head) {
//        //定义双指针，用于遍历
//        //其实就定义一个快指针即可
//        ListNode head = head;
        ListNode fast = head;
        //用于反转列表
        ListNode cur_pre = null;
        ListNode cur_next;

        while (fast!=null && fast.next != null){
            cur_next = head.next;
            head.next = cur_pre;
            cur_pre = head;
            head = cur_next;
            //保证只反转前半部分
            fast = fast.next.next;
        }

        //3.易知，当退出上述循环时，要么是fast == null（当链表节点为偶数时），要么是fast.next == null（当链表节点为奇数时）
        // 而当链表的节点个数为奇数时，此时slow指向的是最中间的那个节点，
        // 但我们要将slow结点跳过中间结点而指向其后一个节点，否则两条半链表不一样长,
        // 且易知，此时的中间节点并不影响该链表是否为回文，因为它只是一个节点。
        if (fast != null){
            head = head.next;
        }

        //此时cur_pre即为前半部分链表的头结点，head即为后半部分链表的头结点，
        //且由于前半部分已经反转，因此此时这两个半链表的值应该完全相同
        //遍历比较即可
        while (cur_pre!= null){
            if (cur_pre.val != head.val){
                return false;
            }
            cur_pre = cur_pre.next;
            head = head.next;
        }
        return true;
    }

}
