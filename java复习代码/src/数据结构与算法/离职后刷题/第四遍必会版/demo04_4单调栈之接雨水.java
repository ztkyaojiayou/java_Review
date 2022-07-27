package 数据结构与算法.离职后刷题.第四遍必会版;

import java.util.Stack;

/**
 * 单调栈思路：
 * 用栈来跟踪可能储水的最长的条形块。
 * 使用栈就可以在一次遍历内完成计算。
 * 我们在遍历数组时维护一个栈。如果当前的条形块小于或等于栈顶的条形块，
 * 我们将条形块的索引入栈，意思是当前的条形块被栈中的前一个条形块界定。
 * 如果我们发现一个条形块长于栈顶，我们可以确定栈顶的条形块被当前条形块和栈的前一个条形块界定，
 * 因此我们可以弹出栈顶元素并且累加答案到 ans 。
 * <p>
 * 参考链接：https://leetcode-cn.com/problems/trapping-rain-water/solution/dan-diao-zhan-jie-jue-jie-yu-shui-wen-ti-by-sweeti/
 */
public class demo04_4单调栈之接雨水 {
    public int trap(int[] height) {
        int sum = 0;
        int len = height.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                //用于求有效高度
                // 同时删除了该元素哦
                int cur_high = height[stack.pop()];
                if (stack.empty()) {
                    break;
                }
                //宽：这里就不是i-cur_high-1了，因为宽度是需要通过两个边界柱子来计算宽度，而不是当前柱子
                // （由于当前柱子已经出栈了，因此这里的stack.peek()就是左边界啦）
                int kuan = i - stack.peek() - 1;
                //两个边界中的矮柱子
                int min_height = Math.min(height[stack.peek()], height[i]);
                //“能接到水的”高（即有效高度） = 两个边界的较小值-当前高度
                int real_high = min_height - cur_high;
                //宽*高即为当前柱子可以接的雨水，再累加到sum上即可
                sum = sum + kuan * real_high;

            }
            stack.push(i);
        }
        return sum;
    }

    //自写一遍
    public int trap02(int[] height) {
        int sum = 0;
        int len = height.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                //用于求有效高度
                Integer curIndex = stack.peek();
                //算过的柱子就将它移除栈，之后的stack.peek()就是新的栈顶元素啦，用此元素可以计宽度！！！
                stack.pop();
                //若为空，则表示所有的柱子都已经计算完毕，于是退出即可
                if (stack.empty()) {
                    break;
                }
                //算宽度（此时需要先pop出当前元素，当然，pop是肯定的，处理完了就要出栈），
                // 此时的stack.peek()就是新的栈顶元素啦~
                int kuan = i - stack.peek() - 1;
                //算有效高度（合并成一步）
                int real_heigh = Math.min(height[i], height[stack.peek()]) - height[curIndex];
//                //算有效高度
//                int min_heigh = Math.min(height[i], height[stack.peek()]);
//                //再算实际高度：减去当前高度（因为当前高度以下的雨水已经计算过了）
//                int real_heigh = min_heigh - height[curIndex];
                //再算接的雨水
                sum += kuan * real_heigh;
            }
            //不管啥情况，对每个元素都入栈--存的是柱子的下标
            stack.push(i);
        }
        return sum;
    }

}
