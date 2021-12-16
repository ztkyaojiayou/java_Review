package 数据结构与算法.第三遍;

import 数据结构与算法.LeetCode题解.ListNode;

import java.util.ArrayList;
import java.util.Stack;

//使用一个栈即可
public class demo06_1从尾到头打印链表 {
    public ArrayList<Integer> printListFromTailToHead(ListNode root) {
        //先存入栈中
        Stack stack = new Stack();
        while (root != null) {
            stack.push(root.val);
            root = root.next;
        }

        //再从栈中取出即可
        ArrayList list = new ArrayList<>();
        while (!stack.isEmpty()){
            list.add(stack.pop());
        }
        return list;
    }
}
