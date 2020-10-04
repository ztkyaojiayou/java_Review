package 数据结构与算法.第三遍;

import 数据结构与算法.LeetCode题解.ListNode;

import java.util.ArrayList;
import java.util.Stack;

//使用一个栈即可
public class demo61_从尾到头打印链表 {
    public ArrayList<Integer> printListFromTailToHead01(ListNode root) {
        Stack stack = new Stack();
        while (root != null){
            stack.push(root.val);
            root = root.next;
        }
        ArrayList list = new ArrayList<>();
        while (!stack.isEmpty()){
            list.add(stack.pop());
        }
        return list;
    }
}
