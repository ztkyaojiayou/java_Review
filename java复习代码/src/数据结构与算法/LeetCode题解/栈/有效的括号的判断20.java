package 数据结构与算法.LeetCode题解.栈;

import java.util.Stack;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 * 输入: "()"
 * 输出: true
 *
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 *
 * 示例 3:
 * 输入: "(]"
 * 输出: false
 *
 * 示例 4:
 * 输入: "([)]"
 * 输出: false
 *
 * 示例 5:
 * 输入: "{[]}"
 * 输出: true
 */

/**
 * 思路分析：
 *
 * 字符串长度为奇数显然无法完成匹配。在字符串长度为偶数的情况下讨论。
 * 括号匹配一个左括号一个右括号，难处理的是括号的嵌套，因为这需要跳过中间的部分去寻找匹配的右括号。
 * 处理这个问题？ :sob:我也不知道怎么想到用栈来处理，我能想起来是因为之前看《算法第四版》时见过类似的于是就直接拿来用了。（扩展里提一下）
 *
 * 遇到左括号就将其压入栈，遇到右括号就与栈顶元素对比，括号不匹配或者栈中已经不存在左括号stack.empty()为true，说明匹配失败。
 * 如果括号匹配说明，这一组匹配成功，它可能是嵌套在其它匹配括号中的，所以此时要将当前栈顶的左括号弹出。
 * 如果最后最终，栈中没有剩余元素，也就是没有剩下左括号，说明刚好完成匹配，括号字符串有效。否则匹配失败，括号字符串无效。
 *
 * 复杂度分析：
 * 遍历一次字符串，每个元素最多一次入栈一次出栈，出栈入栈的操作是O(1)的，所以时间复杂度为O(n)。
 * 最坏的情况下字符串中都是左括号，则栈中有n个元素，所以空间复杂度为O(n)。
 * 代码分析：
 *
 * 7~8行，。
 * 9~16行，右括号的处理。10.11行if (stack.empty()) return false;栈中不存在左括号的情况。13.14行当前括号匹配的情况。
 */
public class 有效的括号的判断20 {
    public boolean isValid(String s) {
            //0.特判：若字符串的字符为奇数个时， 显然无法完成括号匹配
        if (s.length() % 2 == 1)
            return false;
        //1.使用一个栈，用于存储左括号
        Stack<Character> stack = new Stack<>();
        //2.开始遍历所有的字符，遇到左括号就进栈，
        //而若遇到右括号，则在栈中弹出一个左括号，表示该右括号能匹配成功，以此类推
        for (int i = 0; i < s.length(); i++) {
            char theChar = s.charAt(i);//获取字符
            //2.1遇到左括号则入栈
            if (theChar == '(' || theChar == '{' || theChar == '['){
                stack.push(theChar);
            }
            else {//2.否则，说明此时当前字符串为右括号
                if (stack.empty()) {
                    //2.1此时若栈中已无左括号，而由于此时字符为右括号，
                    //说明有右括号剩余，说明无法匹配成功，返回false。
                    return false;
                }
                //2.2再开始取出第一个栈顶元素，开始和当前的元素进行比较，
                //如果括号能匹配的话（有三种形式），就把该栈顶元素弹出/删除，
                //一直如此匹配，直到栈为空，说明刚好匹配成功，则目标字符串有效
                char preChar = stack.peek();
                if ((preChar == '{' && theChar == '}') || (preChar == '(' && theChar == ')') || (preChar == '[' && theChar == ']')){
                    stack.pop();//2.2.1匹配成功就弹出该右括号
                }
                else//2.2.2否则，即不匹配，返回false（即有可能出现右括号')'和左括号 '{'的比对，虽然是一左一右，但这并不是匹配）
                    return false;
            }
        }
        //3.当全部比对完毕之后，若栈为空，说明刚好匹配成功，返回true。
        return stack.empty();
    }
}
