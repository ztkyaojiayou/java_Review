package 数据结构与算法.LeetCode题解.栈;

import java.util.Stack;

/**
 * 1.栈中存放元素下标，从栈顶到栈底的顺序是从小到大；
 * 2.遍历过程中一旦发现高度大于栈顶元素，即出现凹槽，栈顶元素的凹槽底，
 * 栈顶第二个元素是凹槽左边的墙，遍历到的元素是凹槽右边的墙；
 * 3.如果遍历到的高度和栈顶元素的高度相等，则弹出栈顶元素并将当前元素入栈（因为计算宽度使用的是最右边的墙）；
 * 4.如果遍历到的元素高度小于栈顶元素的高度，则该元素入栈（维持从小到大的顺序）；
 * 5.当前凹槽雨水的体积为底乘高，高即min(height[i],height[栈顶第二元素])-height[栈顶元素]；
 * 底为i-栈顶第二元素-1（求的是中间宽度），累加求和即为最终结果。
 */

//整体套路和每日温度相同，不难！！！
public class 接雨水_单调栈版 {
    public int trap(int[] height) {
        int sum = 0;
        int len = height.length;
        //单调栈
        Stack<Integer> stack = new Stack<>();
        //遍历
        for (int i = 0; i < len; i++) {
            //如果栈不空并且当前指向的高度大于栈顶高度就可以接雨水啦！！！
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int h = height[stack.peek()]; //取出该元素（高度），并求该位置能积的雨水量
                stack.pop(); //出栈，因为该元素已经可以计算出不来了！！！
                if (stack.isEmpty()) { // 栈空就跳出循环
                    break;
                }
                //两堵墙之前的距离（易知：相当于每日温度中的后几天比当前温度高的值）
                int distance = i - stack.peek() - 1;
                //选两根柱子的较低高度
                int min_height = Math.min(height[stack.peek()], height[i]);
                //min_height - h：即能盛水的有效高度
                sum = sum + distance * (min_height - h);
            }
            //如果栈不空或者当前指向的高度小于栈顶高度就将当前墙/高度/元素入栈
            //存的是当前柱子所在的下标
            stack.push(i);
        }
        return sum;
    }


}
