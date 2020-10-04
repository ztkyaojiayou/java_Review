package 数据结构与算法.第三遍;

import java.util.Stack;

public class demo30_有效的括号的判断 {
    public boolean isValid(String s) {
        int len = s.length();
        if (len % 2 == 1){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0;i<len;i++){
            char cur_char = s.charAt(i);
            if (cur_char == '(' || cur_char == '{' || cur_char =='['){
                stack.push(cur_char);
            } else {
                if (stack.isEmpty()){
                    return false;
                }
                Character pre_char = stack.peek();
                if (cur_char == '(' && pre_char ==')' || cur_char == '{' && pre_char == '}' || cur_char == '[' && pre_char == ']'){
                    stack.pop();
                }else {
                    return false;//即说明不匹配
                }
            }
        }
        return stack.isEmpty();
    }
}
