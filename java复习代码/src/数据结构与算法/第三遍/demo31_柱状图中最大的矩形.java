package 数据结构与算法.第三遍;

import java.util.Stack;

public class demo31_柱状图中最大的矩形 {
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        int len = heights.length;
        int[] arr = new int[len + 2];
        //在原数组的两头分别加一个0，便于计算
        for (int i = 1;i<len+1;i++){
            arr[i] = heights[i-1];
        }
        //现在使用新数组
        int len_new = arr.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i<len_new;i++){
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]){
                int cur = stack.pop();
                int kuan = i - stack.peek()-1;
                int height = arr[cur];
                res = Math.max(res,kuan * height);
            }
            stack.push(i);
        }
        return res;
    }
}
