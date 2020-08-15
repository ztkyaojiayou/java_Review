package 数据结构与算法.剑指offer题解.第二遍.栈;

import java.util.Stack;

public class 用两个栈实现队列 {
    //1、定义两个栈，一个用于处理入栈，一个用于处理出栈
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    //2、入栈
    public void push(int node){
        stack1.push(node);
    }
    //3、出栈
    public int pop(){
        //若stack2为空，则先要把stack1中的元素弹出并存入stack2后再弹出，
        // 这是关键，因为这样一出一进后，从stack2中弹出的元素就和进入stack1的元素的顺序一样了，也就满足了队列的特点
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
return stack2.pop();//该方法会把弹出的值返回
    }
}
