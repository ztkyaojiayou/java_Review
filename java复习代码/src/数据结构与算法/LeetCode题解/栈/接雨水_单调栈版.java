package 数据结构与算法.LeetCode题解.栈;

import java.util.Stack;

public class 接雨水_单调栈版 {
    public int trap(int[] height) {
        int sum = 0;
        int len = height.length;
        //单调栈
        Stack<Integer> stack = new Stack<>();
        //遍历
      for (int i = 0;i<len;i++){
            //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.empty() && height[i] > height[stack.peek()]) {
                int h = height[stack.peek()]; //取出该元素，并求该位置能积的雨水量
                stack.pop(); //出栈
                if (stack.empty()) { // 栈空就跳出循环
                    break;
                }
                int distance = i - stack.peek() - 1; //两堵墙之前的距离。
                int min_height = Math.min(height[stack.peek()], height[i]);
                sum = sum + distance * (min_height - h);
            }
            //如果栈不空或者当前指向的高度小于栈顶高度就将当前墙/高度/元素入栈
            stack.push(i);
        }
        return sum;
    }

}
