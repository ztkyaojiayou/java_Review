package 数据结构与算法.第三遍;

import java.util.ArrayList;
import java.util.List;

public class demo42_和为S的连续正数序列 {
    //暴力
    public List<ArrayList<Integer>> FindContinuousSequence(int S) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1;i<S/2;i++){//显然，只需遍历前S/2即可
            int cur_sum = 0;
            for (int j = 1;j<S;j++){
                cur_sum += j;
                list.add(j);
                if (cur_sum > S){
                    break;
                }else if (cur_sum == S){
                    res.add(list);
                }
            }
        }
        return res;
    }
}
