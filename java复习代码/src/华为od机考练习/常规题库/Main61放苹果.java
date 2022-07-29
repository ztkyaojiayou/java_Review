package 华为od机考练习.常规题库;

/**
 * 61）放苹果--其实就是0-1背包问题的简化版
 * <p>
 * 动态规划
 * 解题思路：
 * <p>
 * 首先我们用一个二维数组来表示摆放的方案数：持有i个苹果，有j个盘子可以存放苹果，总共有 dp[i][j]种方法。
 * <p>
 * 状态转移方程：dp[i][j] = dp[i][j-1] + dp[i-j][j];
 * <p>
 * 对于状态转移，有两种情况：
 * <p>
 * 1)如果苹果数 < 盘子数，则表示有空盘，则忽略一个盘子，在n-1个放苹果，一直递推到n==1，有一种摆法
 * 2)如果苹果数 >= 盘子数，可以看作没有空盘，则又有两种做法：
 * 2.1)可以选择忽略一个盘子，如上边做法
 * 2.2)还可以选择每个盘子放一个苹果，即苹果数剩下i-j,继续递推直到j==1
 * 算法流程：
 * <p>
 * 定义二维的dp数组：持有i个苹果，有j个盘子可以存放苹果，
 * 总共有 dp[i][j]种方法---对dp数组的定义非常关键！！！
 * 设置 base case：没有苹果，只有一种摆放方法，可以作为递推的终止结果
 * 状态转移方程：dp[i][j] = dp[i][j-1] + dp[i-j][j];
 * 有两种情况：
 * 1）当苹果数 < 盘子数，有空盘，则忽略一个盘子，在n-1个放苹果，一直递推到n==1，有一种摆法
 * 2）苹果数 >= 盘子数，可以看作没有空盘。则又有两种选择：
 * 2.1）可以选择忽略一个盘子，如上边做法。
 * 2.2) 还可以选择每个盘子放一个苹果，即苹果数剩下i-j,继续递推直到j==1
 *
 * @author :zoutongkun
 * @date :2022/7/27 8:42 下午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main61放苹果 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int applesNum = scanner.nextInt();
            int panelsNum = scanner.nextInt();
            System.out.println(method(applesNum, panelsNum));
        }
    }

    /**
     * 核心方法：动态规划
     *
     * @param applesNum
     * @param panelsNum
     * @return
     */
    private static int method(int applesNum, int panelsNum) {
        // 持有i个苹果，有j个盘子可以存放苹果，总共有 dp[i][j]种方法
        int[][] dp = new int[applesNum + 1][panelsNum + 1];
        //题目限定：苹果可以没有，但盘子至少有一个，因此有些初始条件可以忽略
        //base case：
        //1.当盘子就一个时，则所有苹果都只能放这个盘子里，因此也只有一种方法
        for (int i = 0; i < applesNum; i++) {
            dp[i][1] = 1;
        }
        //2.当没有苹果时，也只有一种摆放方法
        for (int j = 0; j <= panelsNum; j++) {
            dp[0][j] = 1;
        }

        //一般情况，都从1开始
        for (int i = 1; i <= applesNum; i++) {
            for (int j = 1; j <= panelsNum; j++) {
                // 1）苹果数 < 盘子数，有空盘，即最多也只能使用i个盘子
                if (i < j) {
                    //易知，即最多也只能使用i个盘子，此时将多余的直接忽略即可
                    dp[i][j] = dp[i][i];
                    // 一般写法：则忽略一个盘子，在n-1个盘子中放苹果，一直递推到n==1，有一种摆法
//                    dp[i][j] = dp[i][j - 1];
                } else {
                    // 2）苹果数 >= 盘子数，可以看作没有空盘
                    // 则可以将盘子都使用，但也可以空一些盘子(任性！），
                    // 2.1）若空一些盘子，则先从空一个盘子算起，然后不断递推至j==1,
                    // 如上边做法，即dp[i][j - 1]
                    // 2.2）还可以全部使用，则此时每个盘子放一个苹果，即苹果数剩下i-j,
                    // 这些苹果又可以放到所有的盘子中，即dp[i - j][j]
                    // 继续递推直到j==1
                    dp[i][j] = dp[i][j - 1] + dp[i - j][j];
                }
            }
        }

        //返回结果
        return dp[applesNum][panelsNum];
    }
}



