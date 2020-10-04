package 数据结构与算法.第三遍;

import java.util.ArrayList;
import java.util.Arrays;

public class demo12_和为某一个数的两个数 {
    public ArrayList<Integer> twoSum(int[] arr, int sum){
        //先排序
        Arrays.sort(arr);
        //双指针
        int i = 0;
        int j = arr.length-1;
        ArrayList<Integer> list = new ArrayList<>();
        while (i<j){
            int cur_sum = arr[i] + arr[j];
            if (cur_sum == sum){
                list.add(arr[i]);
                list.add(arr[j]);
                return list;
            }else if (cur_sum < sum){
                i++;
            }else {
                j++;
            }
        }
        return new ArrayList<>();
    }
}
