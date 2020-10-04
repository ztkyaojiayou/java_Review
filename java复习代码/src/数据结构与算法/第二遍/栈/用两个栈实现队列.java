package 数据结构与算法.第二遍.栈;

import java.util.Stack;
//简单：且比用两个栈实现一个队列简单

/**
 *  * 注意：
 *  * 队列常用的方法就是：
 *  * 1）入队：add或offer（常用），返回的是true或false
 *  * 2）出队：poll（常用）或remove，会返回并删除队头元素
 *  * 3）是否为空：isEmpty
 *  *
 *  * 栈常用的方法就是：
 *  * 1）进栈：push
 *  * 2）出栈:pop，会返回栈顶元素
 *  * 3）是否为空：isEmpty
 */
public class 用两个栈实现队列 {
    //1、定义两个栈，一个用于处理入栈，一个用于处理出栈
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    //2、入队：直接入栈1
    public void offer(int num){
        stack1.push(num);
    }
    //3、出队：先把栈1的元素转移到栈2，再从栈2中弹出元素即可
    public int poll(){
        //若stack2为空，则先要把stack1中的元素弹出并存入stack2后再弹出，
        // 这是关键，因为这样一出一进后，从stack2中弹出的元素就和进入stack1的元素的顺序一样了，也就满足了队列的特点
        //总结：
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
          return stack2.pop();//该方法会把弹出的值返回
    }
}
