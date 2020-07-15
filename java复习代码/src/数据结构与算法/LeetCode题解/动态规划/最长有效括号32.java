package 数据结构与算法.LeetCode题解.动态规划;

import java.util.Stack;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 *
 * 示例 2:
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 */

//方法1：也是使用动态规划求解（推荐）
public class 最长有效括号32 {
        public int longestValidParentheses(String s) {
            int maxans = 0;
            int dp[] = new int[s.length()];
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') {
                        dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                    } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                    maxans = Math.max(maxans, dp[i]);
                }
            }
            return maxans;
        }
    }


//方法2：暴力破解,使用栈
/**
 * 在这种方法中，我们考虑给定字符串中每种可能的非空偶数长度子字符串，
 * 检查它是否是一个有效括号字符串序列。为了检查有效性，我们使用栈的方法。
 *
 * 每当我们遇到一个‘(’,我们把它放在栈顶。
 * 对于遇到的每个‘)’，我们从栈中弹出一个‘(’，如果栈顶没有‘(’，或者遍历完整个子字符串后栈中仍然有元素，那么该子字符串是无效的。
 * 这种方法中，我们对每个偶数长度的子字符串都进行判断，并保存目前为止找到的最长的有效子字符串的长度。
 */
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {//若为左边括号，则压入栈中
                stack.push('(');//压入
            } else if (!stack.empty() && stack.peek() == '(') {//否则，就是右边括号，此时我们从栈中弹出一个‘(’。
                stack.pop();//弹出一个右边括号
            } else {//如果栈顶没有‘(’，或者遍历完整个子字符串后栈中仍然有元素，那么该子字符串是无效的。
                return false;
            }
        }
        return stack.empty();//当栈为空时返回
    }
    public int longestValidParentheses(String s) {
        int maxlen = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j+=2) {
                if (isValid(s.substring(i, j))) {
                    maxlen = Math.max(maxlen, j - i);
                }
            }
        }
        return maxlen;
    }
}
//写法2：
class Solution32 {
    public int longestValidParentheses(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);//为了方便第一个合法序列长度的计算，首先将-1入栈
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {//若为左边括号，则压入栈中
                stack.push(i);
            } else {//否则，就是右边括号，此时我们从栈中弹出一个‘(’，易知栈中放的都是左边括号
                stack.pop();
                if (stack.empty()) {//若栈为空，说明之前没有与之匹配的左括号，那么就将当前的位置入栈。
                    stack.push(i);
                } else {//主要
                    maxans = Math.max(maxans, i - stack.peek());//用当前值减去弹出后的栈的第一个元素即为新的最大长度，再和之前的最大值比较选最大值即可
                }
            }
        }
        return maxans;
    }
}