package 数据结构与算法.第二遍.链表;

import 数据结构与算法.LeetCode题解.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 从尾到头打印链表 {
    //方法1：使用栈
    public List<Integer> reversePrintList(ListNode head) {
        //先入栈
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        //再出栈
        ArrayList<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }

    //方法2：使用递归
    public List<Integer> reversePrintList02(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        if (head != null) {
            reversePrintList02(head.next);
            list.add(head.val);
        }
        return list;
    }

    /**
     * 第三遍-tk.zou
     *
     * @return
     */
    //法1：使用栈，一切都刚刚好（推荐）
    public List<Integer> test03(ListNode head) {
        List<Integer> res = new ArrayList<>();
        //先链表入栈
        Stack<Integer> stack = new Stack<>();
        while (head != null){
            stack.push(head.val);
            head = head.next;
        }
        //再出栈/打印
        while (!stack.isEmpty()){
            //弹出/删除
            Integer pop = stack.pop();
            res.add(pop);
        }
        return res;
    }

    //法2：使用递归（也要掌握）
    public List<Integer> test03reverse(ListNode head) {
        List<Integer> res = new ArrayList<>();
        //即先遍历到尾结点，再打印出来，之后再递归/回溯到倒数第二个结点再打印，以此类推
        while (head != null){
            //不断next，直到尾结点
            test03reverse(head.next);
            res.add(head.val);
        }
        return res;
    }
}
