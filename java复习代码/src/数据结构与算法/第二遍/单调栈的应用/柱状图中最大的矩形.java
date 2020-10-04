package 数据结构与算法.第二遍.单调栈的应用;

import java.util.Stack;

/**
 * 使用栈
 */
public class 柱状图中最大的矩形 {
public int MaxJuXin(int[] height){
    int res = 0;
    int len = height.length;
    int[] new_height = new int[len + 2];
    //在原数组两端添两个0，便于计算
    for (int i=1;i<height.length+1;i++){
        new_height[i] = height[i-1];
    }
    Stack<Integer> stack = new Stack<>();
    for (int i = 0;i<new_height.length;i++){
        while (!stack.isEmpty() && new_height[i] < new_height[stack.pop()]){
            int cur = stack.pop();
            res = Math.max(res,(i-stack.peek()-1) * new_height[cur]);
        }
    }
    return res;
}
}
