package 数据结构与算法.第三遍;

import java.util.Stack;

/**
 * 单调栈思路：
 * 用栈来跟踪可能储水的最长的条形块。
 * 使用栈就可以在一次遍历内完成计算。
 * 我们在遍历数组时维护一个栈。如果当前的条形块小于或等于栈顶的条形块，
 * 我们将条形块的索引入栈，意思是当前的条形块被栈中的前一个条形块界定。
 * 如果我们发现一个条形块长于栈顶，我们可以确定栈顶的条形块被当前条形块和栈的前一个条形块界定，
 * 因此我们可以弹出栈顶元素并且累加答案到 ans 。

 */
public class demo29_接雨水 {
    public int trap(int[] height){
        int sum = 0;
        int len = height.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0;i<len;i++){
            while (!stack.isEmpty() && height[i] > height[stack.peek()]){
                int cur_high = height[stack.pop()];
                if (stack.empty()){
                    break;
                }

                int kuan = i-stack.peek()-1;//宽
                int min_height = Math.min(height[stack.peek()],height[i]);//矮柱子
                int real_high = min_height-cur_high;//“能接到水的”高（即有效高度）
                sum =sum + kuan * real_high;//宽*高即为当前柱子可以接的雨水，再累加到sum上即可

            }
            stack.push(i);
        }
        return sum;
    }

}
