package 数据结构与算法.第三遍;


import java.util.ArrayList;
import java.util.List;

public class demo104_递归之生成有效括号的所有组合 {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();
        dfs("",0,0,n,res);
        return res;
    }

    //递归算法
    private void dfs(String curStr, int left, int right, int n, ArrayList<String> res) {
        //递归结束的条件/出口
        if (left == n && right == n){
            res.add(curStr);
            return;
        }
        //剪枝
        if (left > right){
            return;
        }
        //一般情况
        if (left < n){
            dfs(curStr + "(",left+1,right,n,res);
        }
        if (right < n){
            dfs(curStr + ")",left,right+1,n,res);
        }
    }
}
