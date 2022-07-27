package 数据结构与算法.离职后刷题.第四遍必会版;

import java.util.ArrayList;
import java.util.List;

public class demo42_和为S的连续正数序列 {
    //暴力（最佳解法为滑动窗口，但不太懂）
    public List<ArrayList<Integer>> FindContinuousSequence(int S) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        //显然，只需遍历前S/2即可
        int cur_sum = 0;
        for (int i = 1; i < S / 2; i++) {
            for (int j = i; j < S; j++) {
                cur_sum += j;
                list.add(j);
                if (cur_sum > S) {
                    break;
                } else if (cur_sum == S) {
                    res.add(list);
                    //同时也要终止循环，开始下一组的寻找
                    break;
                }
            }
        }
        return res;
    }
}
