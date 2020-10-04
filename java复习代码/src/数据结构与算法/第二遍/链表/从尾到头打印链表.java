package 数据结构与算法.第二遍.链表;

import 数据结构与算法.LeetCode题解.ListNode;

import java.util.ArrayList;
import java.util.Stack;

public class 从尾到头打印链表 {
    //方法1：使用栈
    public ArrayList<Integer> reversePrintList(ListNode head){
        //先入栈
        Stack<Integer> stack = new Stack<>();
        while (head != null){
            stack.push(head.val);
            head = head.next;
        }
        //再出栈
        ArrayList<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()){
            list.add(stack.pop());
        }
        return list;
    }

    //方法2：使用递归
    public ArrayList<Integer> reversePrintList02(ListNode head){
        ArrayList<Integer> list = new ArrayList<>();
        if (head != null){
            reversePrintList02(head.next);
            list.add(head.val);
        }
        return list;
    }
}
