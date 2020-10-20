package 数据结构与算法.LeetCode题解.回溯_递归_记忆化搜索;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，
 * 用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例：
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 */

/**
 * 解析：使用递归算法，可以根据使用了的左右括号数来递归，相当于遍历一颗树
 * （1）当前剩下的左右括号数都大于 0 时才可以继续递归，才产生分支；
 * （2）产生左分支的条件，只看当前是否还有左括号可以使用；
 * （3）产生右分支的条件，还受到左分支的限制，右边剩余可以使用的括号数量一定得在严格大于左边剩余的数量的时候，才可以产生分支，
 *     否则会有多余的左括号没法被匹配；
 * （4）在剩余的左右括号数都等于 0 时结束递归，完成。
 */
public class 生成有效括号的所有组合22 {

        // 做加法
        //因为n代表生成括号的对数，则相当于有n对括号可以使用，也即左括号和右括号各有n个可供使用
        public List<String> generateParenthesis(int n) {
            //结果集
            List<String> result = new ArrayList<>();
            // 特判
            if (n == 0) {
                return result;
            }
            //调用递归函数
            dfs("", 0, 0, n, result);
            return result;
        }

        /**
         * 递归函数
         * @param curStr 当前递归得到的结果（每一次递归都会记录上一次的结果（即加一个左括号或右括号））
         * @param left   左括号已经用了几个
         * @param right  右括号已经用了几个
         * @param n      左括号、右括号一共可以用几个（即题给的n）
         * @param result    结果集
         */
        private void dfs(String curStr, int left, int right, int n, List<String> result) {
            //递归的终止条件（重要）：即当左右括号都用完时，把拼接的结果集curStr记录进结果集res
            if (left == n && right == n) {
                result.add(curStr);
                return;
            }

            // 剪枝/舍弃/终止递归：即若已经使用了的左括号小于右括号，
            // 则此时多余的右括号没有与之匹配的左括号了，不符合题意，直接舍弃，不再递归。
            // （答疑）哪为什么已经使用的左括号大于右括号却可以呢？
            // 因为此时多余的右括号的作用就是去匹配已经使用了的（还没有被匹配的）左括号呀！
            if (left < right) {
                return;
            }

            //一般情况，使用递归
            if (left < n) {//若左括号还没有用完，则向左递归，
                // 此时递归函数的具体变化就是：curStr在原字符串上加一个左括号，使用了的左括号left加一，右括号不变
                dfs(curStr + "(", left + 1, right, n, result);
            }
            if (right < n) {//若右括号还没有用完，向右递归，
                // 此时递归函数变为：curStr在原字符串上加一个右括号，使用了的右括号right加一，左括号不变
                dfs(curStr + ")", left, right + 1, n, result);
            }
        }
    }
