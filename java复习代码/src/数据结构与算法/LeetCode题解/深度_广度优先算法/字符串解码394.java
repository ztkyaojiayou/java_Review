package 数据结构与算法.LeetCode题解.深度_广度优先算法;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 394. 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 注意：
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例:
 * （1）s = "3[a]2[bc]", 返回 "aaabcbc".
 * （2）s = "3[a2[c]]", 返回 "accaccacc".
 * （3）s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 */

/**
 * 解题思路(没懂）
 * 用栈来进行操作，其中遇到'['时，入栈，遇到']'时出栈。
 * 使用迭代的方式操作栈，这里需要两个栈，一个存储数字,一个存储当前操作的字符串。
 * 具体操作如下代码和注释
 *
 * 使用2个栈一个存储重复次数，一个存储字符，遇到“【”代表字符解码开始，“】”代表结束，
 * 将中间的字符以此出栈拼接，并将数字出栈拼接，再讲字符重新进栈
 */
public class 字符串解码394 {
        public String decodeString(String s) {
            // 思路：栈，遇到'['时，入栈，遇到']'时出栈。

            // 结果集（字符串拼接用StringBuilder），表示上一层（数字字符之前）的字符串
            StringBuilder result = new StringBuilder();

            // 数字栈，存储当前需要循环拼接的次数
            Deque<Integer> numStack = new LinkedList<>();
            // 字符串栈，存储当前已拼接的字符串
            Deque<StringBuilder> strStack = new LinkedList<>();

            // 数字，用于处理>10的数字字符
            StringBuilder numBuilder = new StringBuilder();

            for (int i = 0; i < s.length(); i++) {
                char curr = s.charAt(i);
                /*如果当前字符为0~9，加入数字numBuilder中*/
                if (curr >= 48 && curr <= 57) {
                    numBuilder.append(curr - 48);
                }
                /*如果当前字符为'['，开始入栈操作*/
                else if (curr == 91) {
                    // 数字栈中加入之前获取到的数字字符串（强转Integer）
                    numStack.push(Integer.valueOf(numBuilder.toString()));
                    // 字符串栈中加入当前操作拼接的字符串
                    strStack.push(result);

                    // 重新实例化字符结果集和数字集，用于里层的字符操作
                    result = new StringBuilder();
                    numBuilder = new StringBuilder();

                }
                /*如果当前字符为']'，开始出栈操作*/
                else if (curr == 93) {

                    // 取出数字，代表需要循环拼接的字符的数量
                    int count = numStack.pop();

                    // 需要拼接的字符串
                    String innerString = result.toString();

                    // 循环从1开始，因为当前的字符已有内容
                    for (int loop = 1; loop < count; loop++) {
                        result.append(innerString);
                    }

                    // 取出字符串（上一层中拼接的字符串），并拼接上当前结果
                    result = strStack.pop().append(result);
                }
                /*如果为其他字符，则直接拼接*/
                else {
                    result.append(curr);
                }
            }

            return result.toString();
        }
    }
