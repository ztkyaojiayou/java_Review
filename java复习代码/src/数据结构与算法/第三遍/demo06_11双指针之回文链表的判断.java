package 数据结构与算法.第三遍;

import 数据结构与算法.ListNode;

import java.util.ArrayList;
import java.util.List;

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
}
