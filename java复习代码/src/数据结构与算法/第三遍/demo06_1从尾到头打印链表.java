package 数据结构与算法.第三遍;

import 数据结构与算法.LeetCode题解.ListNode;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//使用一个栈即可，非常简单
public class demo06_1从尾到头打印链表 {
    public List<Integer> printListFromTailToHead(ListNode root) {
        //先存入栈中
        Stack stack = new Stack();
        while (root != null) {
            stack.push(root.val);
            root = root.next;
        }
        //再从栈中取出即可
        List list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }


    //自写一遍
    public List<Integer> printListFromTailToHead02(ListNode root) {
        //1.先入栈
        Stack<Integer> stack = new Stack<>();
        while (root != null) {
            stack.push(root.val);
            //遍历下一个结点
            root = root.next;
        }

        //2.再出栈
        List<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }
}
