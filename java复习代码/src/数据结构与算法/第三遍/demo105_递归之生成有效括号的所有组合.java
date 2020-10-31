package 数据结构与算法.第三遍;


import java.util.ArrayList;
import java.util.List;

public class demo105_递归之生成有效括号的所有组合 {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();
        dfs("",0,0,n,res);
        return res;
    }
    /**
     * 递归算法
     * @param curStr
     * @param userd_left
     * @param userd_right
     * @param n
     * @param res
     */
    private void dfs(String curStr, int userd_left, int userd_right, int n, ArrayList<String> res) {
        //递归结束的条件/出口
        if (userd_left == n && userd_right == n){
            res.add(curStr);
            return;
        }
        //剪枝
        if (userd_left > userd_right){
            return;
        }
        //一般情况
        if (userd_left < n){
            dfs(curStr + "(",userd_left+1,userd_right,n,res);
        }
        if (userd_right < n){
            dfs(curStr + ")",userd_left,userd_right+1,n,res);
        }
    }
}
