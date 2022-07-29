package 华为od机考练习.常规题库;

/**
 * 67）24点游戏算法--dfs、递归、回溯
 * <p>
 * 具体流程：
 * 1）通过递归+dfs，首先定义递归函数功能：当前已经使用了数组的u个数字，前面数字的运算结果为tmp，返回是否运算结果为24
 * 2）确定递归终止条件：已经使用了数组四个元素，同时结果为24，满足题意
 * 3）通过加减乘除四种运算，加减乘除当前数字num[i]，不断递归运算，直到已经使用了数组四个元素，同时结果为24，满足题意
 *
 * @author :zoutongkun
 * @date :2022/7/29 2:25 下午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main67二十四点游戏算法 {
    static int[] nums = new int[4];
    static boolean[] visit = new boolean[4];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String[] a = in.nextLine().split(" ");
            for (int i = 0; i < 4; i++) {
                nums[i] = Integer.parseInt(a[i]);
            }
            System.out.println(dfs(0, 0));
        }

    }

    /**
     * 递归函数（有回溯）
     * curSum是前面n个数字运算结果，usedNum表示已经使用了多少个数字
     * 返回：使用四个数字能否通过加减乘除运算后得到目标和24，若能则为true，否则为false
     *
     * @param usedNum
     * @param curSum
     * @return
     */
    static boolean dfs(int usedNum, float curSum) {
        // 递归终止条件：已经使用了数组四个元素，同时结果为24，满足题意
        if (curSum == 24 && usedNum == 4) {
            return true;
        }

        for (int i = 0; i < 4; i++) {
            if (!visit[i]) {
                visit[i] = true;
                // 加减乘除当前数字num[i]
                // 疑问：这里没有考虑括号吧？
                // 答：其实是有考虑的，有括号也无非是计算顺序不一样而已呀，其本质还是一个四则运算呀
                // 注意在做除法时，需要能整除
                boolean res = ((curSum % nums[i] == 0) && dfs(usedNum + 1, curSum / nums[i]))
                        || dfs(usedNum + 1, curSum + nums[i])
                        || dfs(usedNum + 1, curSum - nums[i])
                        || dfs(usedNum + 1, curSum * nums[i]);

                //若找到，则返回true
                if (res) {
                    return true;
                }
                // 否则，回溯，即将该位置重新置为false
                visit[i] = false;
            }
        }
        return false;
    }
}



