package 数据结构与算法.第三遍;

import java.util.Stack;

//题目：判断pop1是否是push1的一个弹出序列
public class demo04_2栈的压入和弹出序列 {
    //先入栈，再比对出栈
    public boolean IsPopOrder(int[] push1, int[] pop1) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < push1.length; i++) {
            //先入栈
            stack.push(push1[i]);
            //若栈顶元素和弹出序列相等，就弹出栈顶元素，并继续比较，直到不相等为止
            while (j < pop1.length) {
                if (stack.peek() == pop1[j]) {
                    stack.pop();
                    j++;
                }
            }
        }
        //对于结果，只需检查栈是否为空即可
        return stack.isEmpty();
    }


    //自写一遍
    public boolean IsPopOrder02(int[] push1, int[] pop1) {
        Stack<Integer> temp_stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < push1.length; i++) {
            //第一个元素先入栈
            temp_stack.push(push1[i]);
            while (j < pop1.length) {
                //若栈顶元素和弹出序列相等，就弹出栈顶元素，并继续比较，直到不相等为止
                if (temp_stack.peek() == pop1[j]) {
                    temp_stack.pop();
                    j++;
                }
            }
        }
        //对于结果，只需检查栈是否为空即可
        return temp_stack.isEmpty();
    }

}
