package 数据结构与算法.算法.动态规划;

/**
 * 程序员必备的算法之（3）动态规划算法之（2）三角形的最小路径和
 */
public class MinTriangle{
    private static int[][] triangle = {
            {2, 0, 0, 0},
            {3, 4, 0, 0},
            {6, 5, 7, 0},
            {4, 1, 8, 3}
    };
    public static int traverse() {
        int ROW = 4;
        int[] mini = triangle[ROW - 1];
        // 从倒数第二行求起，因为最后一行的值本身是固定的
        for (int i = ROW - 2; i >= 0; i--) {
            for (int j = 0; j < triangle[j].length - 1; j++) {
                mini[j] = triangle[i][j] + Math.min(mini[j], mini[j+1]);
            }
        }
        return mini[0];
    }

    public static  void main(String[] args)  throws Throwable {
        int minPathSum = traverse();
        System.out.println("sum = " + minPathSum);
    }

}
