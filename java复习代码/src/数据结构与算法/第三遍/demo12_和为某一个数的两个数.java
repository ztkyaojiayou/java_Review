package 数据结构与算法.第三遍;

import java.util.ArrayList;
import java.util.Arrays;

public class demo12_和为某一个数的两个数 {
    public ArrayList<Integer> twoSum(int[] arr, int sum){
        //先排序
        Arrays.sort(arr);
        //双指针
        int left = 0;
        int right = arr.length-1;
        ArrayList<Integer> list = new ArrayList<>();
        while (left<right){
            int cur_sum = arr[left] + arr[right];
            if (cur_sum == sum){
                list.add(arr[left]);
                list.add(arr[right]);
                return list;
            }else if (cur_sum < sum){
                left++;
            }else {
                right++;
            }
        }
        return new ArrayList<>();
    }
}
