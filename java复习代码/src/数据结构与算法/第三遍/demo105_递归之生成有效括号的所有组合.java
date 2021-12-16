package 数据结构与算法.第三遍;


import java.util.ArrayList;
import java.util.List;

public class demo105_递归之生成有效括号的所有组合 {
    ArrayList<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
       //直接调用递归函数即可
        dfs("", 0, 0, n);
        return res;
    }

    /**
     * 递归算法
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
        //剪枝,若使用过的左括号大于右括号，则说明肯定无法组成有效括号
        if (userd_left > userd_right) {
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
}
