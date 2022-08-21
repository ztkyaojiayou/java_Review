package 数据结构与算法.od机考.华为od机考练习.常规题库;

/**
 * 61）放苹果--其实就是0-1背包问题的简化版
 *
 * @author :zoutongkun
 * @date :2022/7/27 8:42 下午
 * @description :
 * @modyified By:
 */

import java.util.Scanner;

public class Main61_自写 {
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
        //定义dp数组，多一个长度，因为存在没有苹果的情况
        int[][] dp = new int[applesNum + 1][panelsNum + 1];
        //初始化
        //没有苹果时
        for (int j = 1;j<=panelsNum;j++){
            dp[0][j] = 1;
        }
        //盘子只有一个时
        for (int i = 0;i<applesNum;i++){
            dp[i][1] = 1;
        }

        //一般情况
        for (int i = 1;i<=applesNum;i++){
            for (int j = 1;j<panelsNum;j++){
                if (i>j){
                    //苹果大于盘子时，盘子可用可不用，则递推到j-1
                    dp[i][j] = dp[i-j][j] + dp[i][j-1];
                }else {
                    //苹果小于等于盘子时，此时最多也只能用到i个盘子
                    dp[i][j] = dp[i][i];
                }
            }
        }
        return dp[applesNum][panelsNum];
    }
}



