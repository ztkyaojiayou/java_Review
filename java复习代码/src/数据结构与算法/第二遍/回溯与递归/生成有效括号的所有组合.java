package 数据结构与算法.第二遍.回溯与递归;

import java.util.ArrayList;
import java.util.List;

public class 生成有效括号的所有组合 {
    public List<String> generateKuoHao(int n){
        ArrayList<String> res = new ArrayList<>();
        dfs("",0,0,n,res);
        return res;
    }

//递归,left和right表示已经使用过的括号数
    private void dfs(String curStr, int left, int right, int n, ArrayList<String> res) {
        //递归结束的条件
        if (left == n && right == n){
            res.add(curStr);
            return;
        }
        //剪枝
        if (left < right){
            return;
        }
        if (left < n){
            dfs(curStr + "(",left+1,right,n,res);
        }
        if (right < n){
            dfs(curStr+")",left,right+1,n,res);
        }
    }

}
