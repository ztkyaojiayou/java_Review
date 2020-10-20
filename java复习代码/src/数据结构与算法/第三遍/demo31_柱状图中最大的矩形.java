package 数据结构与算法.第三遍;

import java.util.Stack;

public class demo31_柱状图中最大的矩形 {
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        int len = heights.length;
        int[] arr = new int[len + 2];
        //在原数组的两头分别加一个0，便于计算
        for (int i = 1; i < len + 1; i++) {
            arr[i] = heights[i - 1];
        }
        //现在使用新数组
        int len_new = arr.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len_new; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                int cur = stack.peek();//易知，求得是栈顶元素对应的结果
                stack.pop();//处理完了当前栈顶元素就移除呀，并且这里要先移除，目的是找到左边第一个小于当前栈顶元素的元素的下标~
                int kuan = i - stack.peek() - 1;//宽，i表示的是右边第一个小于当前栈顶元素的元素的下标
                int height = arr[cur];//高，求的是以当前栈顶元素为高的面积
                res = Math.max(res, kuan * height);

            }
            //把当前元素的下标入栈
            stack.push(i);
        }
        return res;
    }
}
