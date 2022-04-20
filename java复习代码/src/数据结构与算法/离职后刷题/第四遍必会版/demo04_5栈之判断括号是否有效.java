package 数据结构与算法.离职后刷题.第四遍必会版;

import java.util.Stack;

public class demo04_5栈之判断括号是否有效 {
    public boolean isValid(String s) {
        int len = s.length();
        if (len % 2 == 1){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0;i<len;i++){
            char cur_char = s.charAt(i);
            //若为左括号，就入栈
            if (cur_char == '(' || cur_char == '{' || cur_char =='['){
                stack.push(cur_char);
            } else {//否则，易知为右括号，于是要取找对应的匹配
                if (stack.isEmpty()){
                    return false;
                }
                //先取出栈顶字符
                Character pre_char = stack.peek();
                if (cur_char == '(' && pre_char ==')' || cur_char == '{' && pre_char == '}' || cur_char == '[' && pre_char == ']'){
                    stack.pop();//若匹配就弹出
                }else {//否则即说明不匹配
                    return false;
                }
            }
        }
        //只要当栈为空时，才表示匹配完毕
        return stack.isEmpty();
    }
}
