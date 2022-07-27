package 数据结构与算法.LeetCode题解.动态规划;

import java.util.Stack;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * <p>
 * 示例 1:
 * 输入: "(()"
 * 输出: 2（一对有效括号计为2，以此类推）
 * 解释: 最长有效括号子串为 "()"
 * <p>
 * 示例 2:
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 * <p>
 * 方法1：也是使用动态规划求解（推荐）
 * 我们用dp[i]表示字符串前i个字符的最长有效括号。
 * 如果我们要想计算dp[i]就会有下面几种判断：
 * <p>
 * （1）如果第i个字符是左括号"("，那么以他结尾的是构不成有效括号的，所以dp[i]==0;
 * （2）如果第i个字符是右括号")"，那么以他结尾的是有可能构成有效括号的，但不一定，其有两种情况，具体如下：
 * 1）当第i-1个字符是"("时，类似于这种……()，此时最长有效括号就是：
 * 第i-2个字符之前构成的最长有效括号+2（2表示这已经有一对了，即一对有效括号算成2），也就是dp[i]=dp[i-2]+2。
 * <p>
 * 2）类似于这种……((……))，我们看一下下面的图来看下，所以我们要判断第i -1- dp[i - 1]个字符是否是"(",
 * 如果是，那么递推公式是dp[i]= dp[i - 1] + 2 + dp[i - dp[i - 1] - 2],
 * 这里dp[i - dp[i - 1] - 2]是第一个省略号构成的有效括号，这个不要忘了
 * <p>
 * 参考链接：https://leetcode-cn.com/problems/longest-valid-parentheses/solution/javadai-ma-de-ji-chong-jie-fa-by-sdwwld/
 */

/**
 * 方法1：也是使用动态规划求解（推荐）
 * 我们用dp[i]表示字符串前i个字符的最长有效括号。
 * 如果我们要想计算dp[i]就会有下面几种判断：
 *
 * （1）如果第i个字符是左括号"("，那么以他结尾的是构不成有效括号的，所以dp[i]==0;
 * （2）如果第i个字符是右括号")"，那么以他结尾的是有可能构成有效括号的，但不一定，其有两种情况，具体如下：
 *     1）当第i-1个字符是"("时，类似于这种……()，此时最长有效括号就是：
 *     第i-2个字符之前构成的最长有效括号+2（2表示这已经有一对了，即一对有效括号算成2），也就是dp[i]=dp[i-2]+2。
 *
 *     2）类似于这种……((……))，我们看一下下面的图来看下，所以我们要判断第i -1- dp[i - 1]个字符是否是"(",
 *       如果是，那么递推公式是dp[i]= dp[i - 1] + 2 + dp[i - dp[i - 1] - 2],
 *      这里dp[i - dp[i - 1] - 2]是第一个省略号构成的有效括号，这个不要忘了
 *
 * 参考链接：https://leetcode-cn.com/problems/longest-valid-parentheses/solution/javadai-ma-de-ji-chong-jie-fa-by-sdwwld/
 *
 *
 */

/**
 * 方法1：非常标准的动态规划写法
 */
public class 最长有效括号的长度32 {
    public int longestValidParentheses(String str) {
        //0.结果集
        int res = 0;
        //1.定义dp数组,dp[i]表示字符串前i个字符的最长有效括号
        int dp[] = new int[str.length()];
        //2.确定初始值
        dp[0] = 0;
        dp[1] = 0;
        //3.处理一般情况
        for (int i = 2; i < str.length(); i++) {
            if (str.charAt(i) == ')') {
                if (str.charAt(i - 1) == '(') {
                    dp[i] = dp[i - 2] + 2;
                } else if (str.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2;
                }
                //易知，该题不是求个dp[len-1]就够了，而是还要求所有的dp中的最大值呀，越来越清晰啦！！！
                //因此，每算出一个dp值就要赶紧维护所有dp的最大值~
                res = Math.max(res, dp[i]);
            }
        }
        //4.最后，返回结果即可
        return res;
    }

}

/**
 * 方法2：使用栈，其实更推荐
 */

/**
 * 如果遇到左括号我们就把他的下标压栈，如果遇到右括号说明和栈顶元素匹配，那么栈顶元素比如m出栈，
 * 用当前元素的下标减去新的栈顶元素的值，
 * 为什么是减去新的栈顶元素值？这是因为新的栈顶元素还没匹配成功，
 * 之前的栈顶元素m到现在元素的下标之间构成了有效的括号
 */
class Solution32 {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);//为了方便第一个合法序列长度的计算，首先将-1入栈
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {//若为左边括号，则把它的下标压入栈中（而不是左括号本身，务必注意）
                stack.push(i);
            } else {//否则，就是右边括号，此时我们从栈中弹出一个‘(’（所对应的下标），易知栈中放的都是左边括号
                stack.pop();
                if (stack.empty()) {//若栈为空，说明之前没有与之匹配的左括号，那么就将当前的右括号（的下标）也入栈。
                    stack.push(i);
                } else {//主要，求出/更新此时的有效括号的长度
                    maxLen = Math.max(maxLen, i - stack.peek());//用当前值减去弹出后的栈的第一个元素即为新的最大长度，再和之前的最大值比较选最大值即可
                }
            }
        }
        return maxLen;
    }
}


/**
 * 方法3：暴力破解,使用栈
 * 在这种方法中，我们考虑给定字符串中每种可能的非空偶数长度子字符串，
 * 检查它是否是一个有效括号字符串序列。为了检查有效性，我们使用栈的方法。
 *
 * 每当我们遇到一个‘(’,我们把它放在栈顶。
 * 对于遇到的每个‘)’，我们从栈中弹出一个‘(’，如果栈顶没有‘(’，或者遍历完整个子字符串后栈中仍然有元素，那么该子字符串是无效的。
 * 这种方法中，我们对每个偶数长度的子字符串都进行判断，并保存目前为止找到的最长的有效子字符串的长度。
 */
class Solution {
    public int longestValidParentheses(String s) {
        int maxlen = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j += 2) {
                if (isValid(s.substring(i, j))) {//注意：substring：包含i但不包含j
                    maxlen = Math.max(maxlen, j - i);
                }
            }
        }
        return maxlen;
    }

    //判断一个字符串是否是有效括号的方法
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
}


