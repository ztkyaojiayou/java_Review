package 数据结构与算法.第二遍.栈;

import java.util.Stack;

public class 实现最小栈 {
    //定义两个栈，一个普通栈，一个存放当前所有元素的较小值，即为题目所述的最小栈
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    //1.入栈
    public void push(int node){
        stack.push(node);
        //同时维护最小栈
        if (minStack.isEmpty()){
            minStack.push(node);
        }else {
            minStack.push(Math.min(minStack.pop(),node));
        }
    }

    //2.出栈
    public int pop(){//也可以不返回值，不过都无所谓
        int res = stack.pop();
        //同时最小栈也要出栈，目的是为了确保最小栈的栈顶元素始终为剩下元素中的最小值
        minStack.pop();
        return res;
    }

    //3.获取栈中的最小元素（得益于在上面的入栈和出栈时维护好了整个最小栈）
    public int GetMin(){
        int res = minStack.pop();
        return res;
    }

}
