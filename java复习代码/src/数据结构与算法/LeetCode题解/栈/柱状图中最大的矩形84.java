package 数据结构与算法.LeetCode题解.栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 84. 柱状图中最大的矩形（困难级别，理解了就不难）
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 *
 * 示例:
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 */

/**
 * 思路解析：
 * 首先，我们可以把问题转化为：找到以第i根柱子为最矮柱子所能延伸的最大面积是什么？
 * 易知，是以i 为中心，向左找第一个小于 heights[i] 的下标 left_i；
 * 向右找第一个小于 heights[i] 的下标 right_i，
 * 即最大面积为 heights[i] * (right_i - left_i -1)
 *
 * 所以，我们的问题就变成如何找下标 right_i 和 left_i？
 * 利用单调栈即可
 * 即维护一个单调递增的栈，保存各柱子对应的下标，就可以找到 left_i 和 right_i。
 * 我们遍历每个柱体，若当前的柱体高度大于等于栈顶柱体的高度，就直接将当前柱体入栈，
 * 否则若当前的柱体高度小于栈顶柱体的高度，说明当前栈顶的柱体找到了右边的第一个小于自身的柱体，
 * 那么就可以将栈顶柱体出栈来计算以其为高的矩形的面积了。
 *
 * 参考链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/zhu-zhuang-tu-zhong-zui-da-de-ju
 * -xing-by-leetcode-/（看其视频）
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/bao-li-jie-fa-zhan-by-liweiwei1419/
 */
public class 柱状图中最大的矩形84 {
        public int largestRectangleArea(int[] heights) {
            //1.定义一个结果变量，由于存储当前的最大的面积
            int res = 0;
            //2.定义一个栈，用于保存各柱子对应的下标
            Deque<Integer> stack = new ArrayDeque<>();

            //3.同时为了计算方便，定义一个新的数组，也是用于存储各柱子的高度，
            //只是在柱体数组的头和尾加了两个高度为 0 的柱体。
            int[] new_heights = new int[heights.length + 2];
            for (int i = 1; i < heights.length + 1; i++) {//拷贝原数组到新数组
                new_heights[i] = heights[i - 1];
            }

            //4.开始计算每一根柱子所能延伸的最大矩形的面积，但不一定是按照顺序来计算的，
            // 而是能计算的就先计算，这也是和暴力解法相区别的地方
            //（关键，务必理解，不难）具体过程为：先比较当前要入栈的柱子的高度是否小于栈顶元素所对应的柱子的高度，
            //若小于，就可以先计算栈顶元素所对应的柱子的最大面积，则先计算，
            //而若大于栈顶元素所对应的高度，则让当前柱子的下标入栈即可。
            for (int i = 0; i < new_heights.length; i++) {
                //4.1若当前柱子的高度小于栈顶元素的高度，此时就可以就计算出栈顶元素所对应的柱子所能勾勒出的最大面积了，
                //且此时当前柱子对应的下标i就充当了栈顶元素所对应的柱子的右边第一个比它小的位置啦，即上面分析中的right_i（关键）
                while (!stack.isEmpty() && new_heights[i] < new_heights[stack.peek()]) {
                    //4.1.1先弹出栈顶的元素（下标），用于求栈顶元素所对应的柱子的高度，即new_heights[cur]。
                    //（也因此易知，每次弹出的元素都是可以求出该元素对应的柱子所能延伸的最大面积的元素）
                    //此时注意：此时栈顶元素就变成了要求的柱子的左边第一个比它小的位置啦，即上面分析中的left_i（关键）
                    int cur = stack.pop();
                    //4.1.2于是，就可以求原栈顶位置所对应的柱子所能延伸的最大面积啦，
                    //易知其长为：i - stack.peek() - 1，高为：new_heights[cur]，
                    //每次计算出一个新的面积时，就和之前的面积进行比较并取较大值，如此反复，最终就可以得到最大面积
                    res = Math.max(res, (i - stack.peek() - 1) * new_heights[cur]);
                }
                //4.2若不满足循环体中的条件，就把各个柱子的下标入栈，
                //注意：由于上面的循环里面是在求面积，则易知，能入栈的下标意味着目前还求不出该下标所对应的柱子能延伸的最大面积的，
                //但可以辅助求栈中其他的元素所对应的柱子的最大面积
                stack.push(i);
            }

            //5.最后，返回最终的最大值即可
            return res;
        }
    }
