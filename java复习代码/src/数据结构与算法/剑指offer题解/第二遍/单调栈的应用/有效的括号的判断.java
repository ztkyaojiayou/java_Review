package 数据结构与算法.剑指offer题解.第二遍.单调栈的应用;

import java.util.Stack;

public class 有效的括号的判断 {
    public boolean isValid(String s){
        //若为奇数，则肯定无法完成匹配
        if (s.length() % 2 == 1){
            return false;
        }

        //使用一个栈来存储左括号(包括所有类型的左括号）
        Stack<Character> stack = new Stack<>();
        //开始遍历
        for (int i = 0;i<s.length();i++){
            char cur_char = s.charAt(i);
            //若当前括号为左括号，则入栈
            if (cur_char == '(' || cur_char == '{' || cur_char == '['){
                stack.push(cur_char);
            }else {//而若当前括号为右括号，则与栈顶元素进行比对即可,若可以匹配，则把该栈顶元素出栈（因为已经处理过了），这里不要返回true，只对不符合要求情况返回false即可
                if (stack.isEmpty()){//若栈为空，即说明当前括号是多余的，则肯定匹配失败
                    return false;
                }
                //获取到栈顶元素（不删除）
                Character stack_char = stack.peek();
                if (stack_char == '(' && cur_char == ')' || stack_char =='{' && cur_char =='}' || stack_char =='['&& cur_char == ']'){
                    stack.pop();//把该栈顶元素出栈（因为已经处理过了）
                }else {
                    return false;
                }
            }

        }
        //最终只需看栈顶元素是否为空即可
        return stack.isEmpty();
    }

}
