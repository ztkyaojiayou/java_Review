package 数据结构与算法.LeetCode题解.栈;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 739. 每日温度
 * 根据每日 气温 列表，请重新生成一个列表，
 * 对应位置的输出是需要再等待多久温度才会升高超过该日的天数。
 * 如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
 * 你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。
 * 每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */

/**
 * 思路解析：使用单调栈
 * 本题使用逆序遍历，为什么呢？
 * 因为正常遍历思路，遍历到当前天，你无法知道后面几天的温度情况。
 * 逆序遍历，后面几天的温度情况已经知晓，很容易得到经过几天后的温度比今天温度高。
 *
 * 思路： 逆序遍历，把每天温度保存下来，存放在一个单调递减(其实是非递增)的栈中，
 * 即后面进入的元素值应当要小于栈顶元素的值（这里就表示该天的后面有大于它的温度的日子）
 * 如果不满足这个要求，则需要将栈顶元素依次弹出，直至重新满足要求为止。
 * 不满足要求的情况即为，该天的温度比栈顶这天的温度要高，而栈顶元素即为栈中最小的元素，
 * 因此此时还不能确定栈中其它元素（即该天后面的其他日子）是否也都小于该天的温度，
 * 此时，只需要把栈中的元素一个一个弹出并比对即可，即每比较一个就删除一个，
 * 若存在一个大于该天温度的日子，则可以表明栈中剩下的温度所对应的日子也都大于该天的温度，
 * 则此时就可以计算出结果啦
 * 重新满足要求后，则栈顶元素及其他元素都为后面比当前天温度高的日子，
 * 但我们只需要计算第一个比当前温度高的日子即可，也就是此时的栈顶元素。
 * 而如果在逐个弹出并比对的过程中，栈空了，那么很明显，
 * 就说明后面的日子中没有一天是比当前天温度还要高的，返回0即可。
 *
 * 代码
 * 上面讲栈中存放的是当前天的温度，实际上存放的当前天温度对应的数组下标，
 * 有了数组下标就也有了当前天的温度。
 * 因为要计算隔了几天后温度比当前天温度高，存放数组下标更合适。
 */
public class 每日温度739 {
        public int[] dailyTemperatures(int[] num) {
            int length = num.length;
            int[] ans = new int[length];
            Deque<Integer> stack = new LinkedList<Integer>();
            for (int i = 0; i < length; i++) {
                //若栈不为空，且当前温度大于栈顶元素对应的温度，则此时就可以计算出栈顶元素对应的温度的情况
                while (!stack.isEmpty() && num[i] > num[stack.peek()]) {
                    int prevIndex = stack.pop();//取出栈顶元素对应的下标，同时出栈该元素，因为该元素已经被处理了呀！
                    ans[prevIndex] = i - prevIndex;//求出再过多少天就可以看到比自己高的温度了
                }
                //而若栈为空，且当前温度小于栈顶元素对应的温度，则把当前温度对应的下标存入栈中
                stack.push(i);
            }
            //返回结果即可
            return ans;
        }
    }
