package 数据结构与算法.第三遍;

import java.util.Stack;

//比用两个队列实现一个栈简单
class demo70_使用两个队列实现一个栈 {
    //构造函数
    public demo70_使用两个队列实现一个栈(){
    }
    //定义两个栈
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    //入队：直接入队即可
    public void Offer(Integer num){
        stack1.push(num);
    }
    //出队：从栈2出队，若为空，则先把栈1中的元素放入栈2
    public Integer poll(){
        if (stack2.isEmpty()){
            int size = stack1.size();
            for (int i = 0;i<size;i++){
                stack2.push(stack1.pop());
            }
        }
        Integer res = stack2.pop();
        return res;
    }
}
