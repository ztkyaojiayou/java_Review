package 秋招笔试.小米;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class test02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            list.add(str);
        }
for (int i = 0;i<list.size();i++){
    String str1 = list.get(i);
    boolean res = test02.isValid(str1);
    if (res){
        System.out.println("true");
    }else {
        System.out.println("false");
    }

}
    }

    public static boolean isValid(String s) {
        int len = s.length();
        if (s == null || s.length() == 0){
            return true;
        }
        if (len % 2 == 1){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0;i<len;i++){
            char cur_char = s.charAt(i);
            if (cur_char == '(' || cur_char == '{' || cur_char =='['){
                stack.push(cur_char);
            }else {
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

class Solution {
    public boolean isValid(String s) {
        if(s.length() % 2 != 0) return false;
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(c == '(') stack.push(')');
            else if(c == '[') stack.push(']');
            else if(c == '{') stack.push('}');
            else if(stack.isEmpty() || c != stack.pop()) return false;
        }
        return stack.empty();
    }
}

