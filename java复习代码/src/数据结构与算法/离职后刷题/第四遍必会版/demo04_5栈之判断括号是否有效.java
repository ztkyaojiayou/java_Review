package 数据结构与算法.离职后刷题.第四遍必会版;

import java.util.Stack;

public class demo04_5栈之判断括号是否有效 {
    public boolean isValid(String s) {
        //0.特判：若字符串的字符为奇数个时， 显然无法完成括号匹配
        if (s.length() % 2 == 1) {
            return false;
        }
        //1.使用一个栈，用于存储左括号
        Stack<Character> stack = new Stack<>();
        //2.开始遍历所有的字符，遇到左括号就进栈，
        //而若遇到右括号，则在栈中弹出一个左括号，表示该右括号能匹配成功，以此类推
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);//获取字符
            //2.1遇到左括号则入栈
            if (curChar == '(' || curChar == '{' || curChar == '[') {
                stack.push(curChar);
            } else {//2.否则，说明此时当前字符串不是左括号，分为两种情况
                //2.1栈为空时,有两种情况：
                //2.1.1.若第一个元素即为右括号，则肯定不是有效括号，易知此时stack为空
                //2.2.2.同时，还包含一种情况：即左括号少于右括号，
                // 即栈中的左括号都匹配完了但还剩了右括号，那整体肯定也不是有效括号呀！
                if (stack.empty()) {
                    //2.1此时若栈中已无左括号，而由于此时字符为右括号，
                    //说明有右括号剩余，说明无法匹配成功，返回false。
                    return false;
                }
                //2.2若第一个字符是左括号，此时stack已经不为空了
                // 此时开始取出第一个栈顶元素，开始和当前的元素进行比较，
                //如果括号能匹配的话（有三种形式），就把该栈顶元素弹出/删除，
                //一直如此匹配，直到栈为空，说明刚好匹配成功，则目标字符串有效
                Character stackChar = stack.peek();
                if ((stackChar == '{' && curChar == '}') || (stackChar == '(' && curChar == ')') || (stackChar == '[' && curChar == ']')) {
                    stack.pop();//2.2.1匹配成功就弹出该右括号
                } else//2.2.2否则，即不匹配，返回false（即有可能出现右括号')'和左括号 '{'的比对，
                // 虽然是一左一右，但这并不是匹配）
                {
                    return false;
                }
            }
        }
        //3.当全部比对完毕之后，若栈为空，说明刚好匹配成功，返回true。
        return stack.empty();
    }


    //自写一遍
    public boolean isValid02(String s) {
        int length = s.length();
        if (length % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            char curChar = s.charAt(i);
            if (curChar == '(' || curChar == '{' || curChar == '[') {
                stack.push(curChar);
            } else {
                //栈为空时有两种情况：
                //1.若第一个元素即为右括号，则肯定不是有效括号，易知此时stack为空
                //2.同时，还包含一种情况：即左括号少于右括号，
                // 即栈中的左括号都匹配完了但还剩了右括号，那整体肯定也不是有效括号呀！
                if (stack.isEmpty()) {
                    return false;
                } else {
                    //此时即第一个元素不为空，开始比对
                    Character stackChar = stack.peek();
                    if ((stackChar == '{' && curChar == '}') || (stackChar == '(' && curChar == ')') || (stackChar == '[' && curChar == ']')) {
                        //匹配到一对，就删除栈中这一对有效括号对应的左括号~
                        stack.pop();
                    } else {//说明，虽然是右括号与左括号的匹配，但是对应错误，如"{"和“)”，则肯定也不是有效括号~
                        return false;
                    }
                }
            }
        }
        //若为有效括号，则最终stack必为空
        return stack.isEmpty();
    }
}
