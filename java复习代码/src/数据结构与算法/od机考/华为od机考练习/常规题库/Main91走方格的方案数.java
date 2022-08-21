package 数据结构与算法.od机考.华为od机考练习.常规题库;


/**
 * 91）走方格的方案数
 * @author :zoutongkun
 * @date :2022/7/28 12:46 上午
 * @description :
 * @modyified By:
 */


import java.util.*;

public class Main91走方格的方案数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //行
        int m = sc.nextInt();
        //列
        int n = sc.nextInt();
        System.out.println(uniquePaths(m, n));
    }

    // 因为只需要求路径的条数，不涉及到具体的元素值（对比“最短路径的路径和”），
    // 因此无需给出具体的二维数组，只需给出行和列即可
    public static int uniquePaths(int m, int n) {//m是行，n是列
        //dp[i][j]就表示从起点到达位置(i,j)的总路径数
        int[][] dp = new int[m + 1][n + 1];
        //初始化，即边上的位置，其路径就一条呀！！！
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = 1;
        }
        //一般情况，先列(j)再行(i)
        for (int j = 1; j < n + 1; j++) {
            for (int i = 1; i < m + 1; i++) {
                //求得是总路径，因此不使用max函数呀！！！
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        //最后返回结果即可
        return dp[m][n];
    }

}
