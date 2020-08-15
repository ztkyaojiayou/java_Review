package 数据结构与算法.剑指offer题解.第二遍.栈;


import java.util.Stack;

public class 栈的压入和弹出序列 {
    public boolean isPopOrder(int[] push,int[] pop){
        Stack<Integer> temp_stack = new Stack<>();
        int j = 0;
        for (int i=0;i<push.length;i++){
            temp_stack.push(push[i]);
            while (j < push.length && temp_stack.peek() == pop[j]){
                temp_stack.pop();
                j++;
            }
        }
        boolean res = temp_stack.isEmpty();
        return res;
    }
}
