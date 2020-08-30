package 数据结构与算法.剑指offer题解.第二遍.单调栈的应用;

import java.util.Stack;

public class 接雨水_单调栈版 {
    public int trap(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;//当前元素/高度的下标
        while (current < height.length) {
            //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()]; //取出要出栈的元素
                stack.pop(); //出栈
                if (stack.empty()) { // 栈空就跳出循环
                    break;
                }
                int distance = current - stack.peek() - 1; //两堵墙之前的距离。
                int min_hight = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min_hight - h);
            }
            //如果栈不空或者当前指向的高度小于栈顶高度就将当前墙/高度/元素入栈,同时指针右移
            stack.push(current);
            current++; //指针右移
        }
        return sum;
    }
}
