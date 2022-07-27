package 数据结构与算法.离职后刷题.第四遍必会版;

import java.util.Stack;

public class demo04_1包含min函数的栈 {
    //关键是要维护minStack
    Stack<Integer> stack1 = new Stack<>();
    //存的时候存小值，出栈的时候也要一并出栈
    Stack<Integer> minStack = new Stack<>();

    //入栈，min栈也要入栈，不过入的是较小的元素
    public void push(int num) {
        stack1.push(num);

        if (minStack.isEmpty()) {
            minStack.push(num);
        } else {
            //比较要存入stack的新元素与minStack的栈顶元素，存入较小值
            // 易知此时可能会有重复元素呀
            minStack.push(Math.min(minStack.peek(), num));
        }
    }

    //出栈(min栈也要同时出栈）
    public void pop() {
        stack1.pop();
        minStack.pop();
    }

    //min栈，此时每次都可以获得/查看最小值了
    public int min() {
        //peek（）：查看栈顶部的元素（但不从堆栈中移除它）
        return minStack.peek();
    }
}
