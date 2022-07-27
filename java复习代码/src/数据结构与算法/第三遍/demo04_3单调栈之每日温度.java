package 数据结构与算法.第三遍;

import java.util.Stack;

/**
 * 739. 每日温度
 * 根据每日 气温 列表，请重新生成一个列表，
 * 对应位置的输出是需要再等待多久温度才会升高到超过该日的天数。
 * 如果之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
 * 你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 提示：气温 列表长度的范围是 [1, 30000]。
 * 每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 * @author zoutongkun
 */
public class demo04_3单调栈之每日温度 {
    public int[] dailyTemperatures(int[] num) {
        int len = num.length;
        int[] res = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            //一旦不为空，且当前温度大于栈顶温度，就可以计算出栈顶温度这一天的值了
            while (!stack.isEmpty() && num[i] > num[stack.peek()]) {
                int curIndex = stack.peek();
                //即易知，求得是栈顶元素对应的结果
                res[curIndex] = i - curIndex;
                //计算完毕了就删除该栈顶元素
                stack.pop();
            }
            //压入的是当前温度对应的下标，只要比当前栈顶温度低的全部压入
            //每次计算的也是栈顶温度那一天的值（但要注意的是，易知并不一定是按顺序的！！！）
            stack.push(i);
        }
        return res;
    }

    //自写一遍
    public int[] dailyTemperatures02(int[] num) {
        int length = num.length;
        int[] res = new int[length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0;i<length;i++){
            while (!stack.isEmpty() && num[i] > num[stack.peek()]){
                Integer curIndex = stack.peek();
                res[curIndex] = i-curIndex;
                stack.pop();
            }

            //若小于，则正常入栈--存下标，便于计算
            stack.push(i);
        }
        return res;
    }
}
