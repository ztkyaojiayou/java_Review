package 数据结构与算法.LeetCode题解.链表;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 84. 柱状图中最大的矩形(困难，没太懂）
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 *
 * 示例:
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 */
public class 柱状图中的最大矩形84 {
        public int largestRectangleArea(int[] heights) {
            int res = 0;
            Deque<Integer> stack = new ArrayDeque<>();
            int[] new_heights = new int[heights.length + 2];
            for (int i = 1; i < heights.length + 1; i++) {
                new_heights[i] = heights[i - 1];
            }
            //System.out.println(Arrays.toString(new_heights));
            for (int i = 0; i < new_heights.length; i++) {
                //System.out.println(stack.toString());
                while (!stack.isEmpty() && new_heights[stack.peek()] > new_heights[i]) {
                    int cur = stack.pop();
                    res = Math.max(res, (i - stack.peek() - 1) * new_heights[cur]);
                }
                stack.push(i);
            }
            return res;
        }
    }
