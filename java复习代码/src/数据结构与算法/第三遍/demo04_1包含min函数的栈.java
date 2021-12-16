package 数据结构与算法.第三遍;

import java.util.Stack;

public class demo04_1包含min函数的栈 {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> minStack = new Stack<>();
    //入栈，min栈也要入栈，不过入的是较小的元素
    public void push(int num){
        stack1.push(num);

        if (minStack.isEmpty()){
            minStack.push(num);
        }else {
            minStack.push(Math.min(minStack.peek(),num));
        }
    }
    //出栈(min栈也要同时出栈）
    public void pop(){
        stack1.pop();
        minStack.pop();
    }

    //min栈
    public int min_stack(){
        Integer res = minStack.peek();
        return res;
    }
}
