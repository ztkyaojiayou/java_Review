package 数据结构与算法.第二遍.双指针;

import java.util.ArrayList;

//双指针，经典，简单
public class 和为S的两个数 {
    public ArrayList<Integer> findS(int[] arr,int sum){
        ArrayList<Integer> res = new ArrayList<>();
        int i = 0,j = arr.length-1;
        while (i< j){
         int cur = arr[i] + arr[j];
         if (cur == sum){
             res.add(arr[i]);
             res.add(arr[j]);
             return res;
         }else if (cur < sum){
             i++;
         }else {
             j--;
         }
        }
        //若没有找到，则返回一个空list
        return new ArrayList<>();
    }
}
