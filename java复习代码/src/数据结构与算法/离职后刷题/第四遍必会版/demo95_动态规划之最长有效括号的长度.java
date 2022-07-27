package 数据结构与算法.离职后刷题.第四遍必会版;

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

        //dp[i]表示由第i个字符结尾的字符串的有效括号的最大长度
        int[] dp = new int[str.length()];
        //初始化
        dp[0] = 0;
        dp[1] = 0;
        //i至少要从2开始呀
        for (int i = 2; i < str.length(); i++) {
            //若当前字符为“（”，则dp值肯定为0呀，因为我们定义的是前i个字符所构成的有效括号
            if (str.charAt(i) == ')') {
                if (str.charAt(i - 1) == '(') {
                    dp[i] = dp[i - 2] + 2;
                } else if (str.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2;
                }
                //再从每一个dp中寻找最大的dp值才是所求（老生常谈啦）
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        return maxLen;
    }

    //自写一遍
    public int longestValidParentheses01(String str) {
        int len = str.length();
        int res = 0;
        //dp[i]定义为：前i个括号构成的有效括号的长度
        int[] dp = new int[len];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) == ')') {
                if (str.charAt(i - 1) == '(') {
                    dp[i] = dp[i - 2] + 2;
                } else if (str.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2];
                }
                //每求出一个dp，就立马更新dp中的最大值，因为是要求所有dp中的最大值，
                //就是个套路呀！！！
                res = Math.max(res, dp[i]);
            }
        }
        return res;
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
        stack.push(-1);//先压入-1，便于计算
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
            } else {//先弹出，再计算
                stack.pop();
                if (stack.isEmpty()) {//别忘了，若此时为空，则也要把当前字符入栈
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }
}
