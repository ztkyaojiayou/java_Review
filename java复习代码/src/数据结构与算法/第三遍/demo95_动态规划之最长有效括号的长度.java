package 数据结构与算法.第三遍;

import java.util.Stack;

public class demo95_动态规划之最长有效括号的长度 {
    /**
     * 方法1：动态规划
     *
     * @param str
     * @return
     */
    public int longestValidParentheses(String str) {
        int maxLen = 0;
//dp[i]表示字符串前i个字符的最长有效括号
        int[] dp = new int[str.length()];
        //初始化
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < str.length(); i++) {
            if (str.charAt(i) == ')') {
                if (str.charAt(i - 1) == '(') {
                    dp[i] = dp[i - 2] + 2;
                } else if (str.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2;
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        return maxLen;
    }

    /**
     * 方法2：使用栈，其实更推荐，更好理解
     *
     * @param str
     * @return
     */
    public int longestValidParentheses02(String str) {
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {//别忘了
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }
}
