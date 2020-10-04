package 数据结构与算法.第三遍;

import java.util.Stack;

public class demo27_栈的压入和弹出序列 {
    public boolean IsPopOrder(int [] push1,int [] pop1) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0;i<push1.length;i++){
            stack.push(push1[i]);
            while (j<pop1.length && stack.peek() == pop1[j]){
                stack.pop();
                j++;
        }
        }
        return stack.isEmpty();
    }
}
