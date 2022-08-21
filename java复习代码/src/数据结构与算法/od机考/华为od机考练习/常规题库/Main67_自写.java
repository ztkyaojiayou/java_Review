package 数据结构与算法.od机考.华为od机考练习.常规题库;

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

import java.util.Scanner;

public class Main67_自写 {
    public static int[] nums = new int[4];
    public static boolean[] visited = new boolean[4];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //构建数组
        for (int i = 0; i < 4; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(dfsMethod(0, 0));
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
    private static boolean dfsMethod(int usedNum, int curSum) {
//终止条件
        if (usedNum == 4 && curSum == 24) {
            return true;
        }
        for (int i = 0; i < 4; i++) {
            //若当前元素还没使用过
            if (!visited[i]) {
                //标记，表示已使用当前元素
                visited[i] = true;
                //dfs做加减乘除运算
                boolean res = (curSum % nums[i] == 0) && (dfsMethod(usedNum + 1, curSum / nums[i]))
                        || dfsMethod(usedNum + 1, curSum + nums[i])
                        || dfsMethod(usedNum + 1, curSum - nums[i])
                        || dfsMethod(usedNum + 1, curSum * nums[i]);
                //再判断
                if (res) {
                    //说明找到了，返回true
                    return true;
                }
                //否则，回溯，即把这个位置重新置为false
                visited[i] = false;
            }
        }
        //若找完了都没找到，则也返回false
        return false;
    }
}



