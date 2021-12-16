package 数据结构与算法.第三遍;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class demo15_堆排序之最小的k个数 {
    //方法一：堆排序（推荐，常用于解决topK问题）
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] nums, int k) {
        //最小，所以用大顶堆，所以要降序，所以要重写比较器
        PriorityQueue<Integer> priQueue = new PriorityQueue<>(((o1, o2) -> o2 - o1));//降序
        for (int i = 0;i<nums.length;i++){
            priQueue.add(nums[i]);
            if (priQueue.size() > k){//只存k个数，多的就弹出
                priQueue.poll();
            }
        }
        //返回结果，把队列直接转化为list
        return new ArrayList<>(priQueue);
    }
}
