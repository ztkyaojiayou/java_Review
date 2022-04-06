package 数据结构与算法.第三遍;

import java.security.PublicKey;
import java.util.Stack;

//比用两个队列实现一个栈,简单
class myQueue {
    //构造函数
    public myQueue() {
    }

    //定义两个栈（先进后出）
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    //入队：直接入队即可
    public void offer(Integer num) {
        stack1.push(num);
    }

    //出队：从栈2出队，若为空，则先把栈1中的元素全部放入栈2
    public Integer poll() {
        if (stack2.isEmpty()) {
            int size = stack1.size();
            for (int i = 0; i < size; i++) {
                stack2.push(stack1.pop());
            }
        }
        int res = stack2.pop();
        return res;
    }
}

/**
 * 第四遍
 */
class myQueue02 {
    public myQueue02() {
    }

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    //入队
    public void Offer(Integer num) {
        stack1.push(num);
    }

    //出队
    public Integer Poll() {
        if (stack2.isEmpty()) {
            for (int i = 0; i < stack1.size(); i++) {
                stack2.push(stack1.pop());
            }
        }
        Integer res = stack2.pop();
        return res;
    }
}
