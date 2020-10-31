package 数据结构与算法.第三遍;

import java基础.多态.A;

import java.util.ArrayList;
import java.util.List;

public class demo07_3找出数组中只出现一次的两个数字 {
    //方法：边添加边删除
    public int[] FindNumsAppearOnce01(int[] arr) {
    //结果数组
        int[] res = new int[2];
        List<Integer> list = new ArrayList<>();
        for (int i = 0;i<arr.length;i++){
            if (list.contains(arr[i])){//并不加进去，此时只是说明该元素是重复元素，于是把已经在list中的该元素删除即可，这一点很重要
                list.remove(new Integer(arr[i]));//删除该元素，若直接为arr[i]的话，则是删除该索引所在的元素，而不是该元素本身，务必注意
            }else {
                list.add(arr[i]);
            }
        }

        //再到list中查找目标数即可（易知，其实此时的list中也只剩下了那两个目标数了）
        for (int i = 0;i<list.size();i++){
            res[i] = list.get(i);
        }

        return res;

    }
}
