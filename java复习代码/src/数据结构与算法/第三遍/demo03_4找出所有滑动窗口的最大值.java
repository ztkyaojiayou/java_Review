package 数据结构与算法.第三遍;

import java.util.LinkedList;

//使用双端队列即可
public class demo03_4找出所有滑动窗口的最大值 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        LinkedList<Integer> queue = new LinkedList<>();
        int[] res = new int[len - k + 1];
        for (int i = 0;i<len;i++){
            //若队列中有比当前元素小的元素，就把他们全部弹出，目的是保证队首元素最大，即所有元素从大到小排列
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]){
                queue.pollLast();
            }
            //添加的是下标，且添加到最后
            queue.addLast(i);
            //维持滑动窗口的长度为k，若大于k，则剔除队首元素
            if (i - queue.peek() >= k){
                queue.poll();
            }
            //当窗口长度为k时，此时就可以摘出这个窗口中的最大值存入res中了，易知最大值即为队首元素
            if (i+1 >= k){
                res[i+i-k] = nums[queue.peek()];
            }
        }
        return res;
    }
}
