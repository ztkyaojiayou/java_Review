package 数据结构与算法.离职后刷题.第四遍必会版;


import java.util.ArrayList;
import java.util.List;

//解法参考：https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
public class demo105_递归之生成有效括号的所有组合 {
    //全局变量，存储每一对有效括号
    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        //直接调用递归函数即可
        dfs("", 0, 0, n);
        return res;
    }

    /**
     * 递归算法
     *
     * @param curStr
     * @param userd_left
     * @param userd_right
     * @param n
     */
    private void dfs(String curStr, int userd_left, int userd_right, int n) {
        //递归结束的条件/出口
        if (userd_left == n && userd_right == n) {
            res.add(curStr);
            return;
        }
        //剪枝,若使用过的左括号小于右括号，则说明肯定无法组成有效括号
        if (userd_left < userd_right) {
            return;
        }
        //一般情况
        if (userd_left < n) {
            dfs(curStr + "(", userd_left + 1, userd_right, n);
        }
        if (userd_right < n) {
            dfs(curStr + ")", userd_left, userd_right + 1, n);
        }
    }

    //自写一遍
    List<String> res02 = new ArrayList<>();

    public List<String> generateParenthesis02(int n) {
        if (n == 0) {
            return res02;
        }
        dfs02("", 0, 0, n);
        return res02;
    }

    private void dfs02(String curStr, int usedLeft, int usedRight, int n) {
        //递归出口
        if (usedLeft == n && usedRight == n) {
            res02.add(curStr);
            return;
        }
        //剪枝
        //使用的左括号必须要多于右括号才行，否则无法组成有效的括号对
        if (usedLeft < usedRight) {
            return;
        }
        //递归
        //若左括号还没有用完，则向左递归
        if (usedLeft < n) {
            dfs02(curStr + "(", usedLeft + 1, usedRight, n);
        }
        if (usedRight < n) {
            //若右括号还没有用完，则向右递归
            dfs02(curStr + ")", usedLeft, usedRight + 1, n);
        }

    }
}
