package 数据结构与算法.第三遍;

import java.util.Stack;

/**
 * 739. 每日温度
 * 根据每日 气温 列表，请重新生成一个列表，
 * 对应位置的输出是需要再等待多久温度才会升高到超过该日的天数。
 * 如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
 * 你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。
 * 每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */
public class demo04_3单调栈之每日温度 {
    public int[] dailyTemperatures(int[] num) {
        int len = num.length;
        int[] res = new int[len];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i<len;i++){
            if (!stack.isEmpty() && num[i] > num[stack.peek()]){
                int curIndex = stack.peek();
                //即易知，求得是栈顶元素对应的结果
                res[curIndex] = i - curIndex;
                stack.pop();//计算完毕了就删除该栈顶元素
            }
            //压入的是当前温度对应的下标
            stack.push(i);
        }
        return res;
    }
}
