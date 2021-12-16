package 数据结构与算法.剑指offer第一版.栈;

import java.util.Stack;

/**
 * 题目：用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 *
 * 【解】
 * （1）首先注意：既然题目明确说了是要用到两个栈，
 * 则我们可以直接使用Java中的栈stack了，而不需要自己去实现栈
 *     另外：队列的特点：先入先出
 *          栈的特点：先入后出
 * （2）思路：
 * 设这两个栈分别为stack1和stack2，其中：
 * stack1 栈用来处理入栈（push）操作，stack2 栈用来处理出栈（pop）操作。
 * 因为一个元素进入 stack1 栈之后，出栈的顺序被反转。
 * 因此当元素要出栈时，先让其进入 stack2 栈，此时元素出栈顺序再一次被反转
 * 因此出栈顺序就和最开始入栈顺序是相同的，先进入的元素先退出，这就实现了队列的顺序。
 *
 * （3）具体过程：
 * 1.当入队列（push）时，队列和栈没什么区别，直接插入 栈stack1，不用特殊处理
 * 2.当从队列弹出（pop）时，这是关键，要分两种情况：
 *   （1）若 栈stack2 不为空，弹出 stack2 栈顶元素
 *   （2）若 栈stack2 为空，则先将 stack1 中的全部数逐个出栈，
 *       存入 stack2，再弹出 stack2 栈顶元素即可
 */

public class Stack19用两个栈实现队列 {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    //1.实现入队列（push）操作
    //由于队列和栈没什么区别，直接插入 栈stack1 即可，不用特殊处理
    public void push(int node) {//push操作，要传入值，但没有返回值
        stack1.push(node);
    }
    //2.实现出队列（pop）操作，要分两种情况：
    //（1）若 栈stack2 不为空，弹出 stack2 栈顶元素
    //（2）若 栈stack2 为空，则先将 stack1 中的全部数逐个出栈，存入 stack2，再弹出 stack2 栈顶元素即可
    public int pop() {//pop操作 有返回值，但不需要传入值
        // 若 栈stack2 为空而栈stack1不为空时，单独处理
        // 先将 stack1 中的全部数逐个出栈，存入 stack2，再弹出 stack2 栈顶元素即可
        //if (stack2.size() <= 0) {//该行可以使用isEmpty方法代替
        if (stack2.isEmpty()){
            //把stack1中的值依次弹出并存入到stack2
            //while (stack1.size() != 0) {//该行可以使用isEmpty方法代替
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        //从stack2中弹出，即为所求（这一步包括情况1）
        return stack2.pop();
    }
}
