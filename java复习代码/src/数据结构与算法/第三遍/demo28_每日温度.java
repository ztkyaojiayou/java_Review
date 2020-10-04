package 数据结构与算法.第三遍;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Stack;

public class demo28_每日温度 {
    public int[] dailyTemperatures(int[] num) {
        int len = num.length;
        int[] res = new int[len];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i<len;i++){
            if (!stack.isEmpty() && num[i] > num[stack.peek()]){
                int curIndex = stack.peek();
                stack.pop();
                res[curIndex] = i - curIndex;
            }
            stack.push(i);
        }
        return res;
    }
}
