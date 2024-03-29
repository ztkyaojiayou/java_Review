package 数据结构与算法.LeetCode题解.动态规划;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 * //定义一个整型数组:3行4列
 * int a[][] = new int[3][4];
 * //获取行数---3行
 * int lenY = a.length;
 * //获取列数---4列
 * int lenX = a[0].length;
 */
//典型的动态规划题
    //标准的写法：（要掌握）
public class 路径和最短的路径的和64 {
    public int minPathSum(int[][] nums) {
        //1.先获取原矩阵数组的行和列，用于构建新二维数组
        int row = nums.length;//行
        int col = nums[0].length;//列
        if (row == 0 || col == 0) {//特判
            return 0;
        }
        //2.再定义一个和原矩形规格相同的二维数组，
        // 其中的dp[i][j]表示从nums[0][0]到nums[i][j]的路径和最小的路径的和，即为所求（重点）
        int[][] dp = new int[row][col];

        //3.再确定初始/边界状态
        //3.1可以直接确定左上角第一个元素
        dp[0][0] = nums[0][0];
        //3.2再看第一列
        for (int i = 1; i < row; i++) {//第一列
            dp[i][0] = dp[i - 1][0] + nums[i][0];
        }
        //3.3再看第一行
        for (int j = 1; j < col; j++) {//第一行
            dp[0][j] = dp[0][j - 1] + nums[0][j];
        }

        //4.解决一般情况，即利用状态方程转化为初始/边界状态求解（关键）
        //两层for循环，先行后列
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                //该状态方程的意义是到达位置（i，j）处的路径和最短的路径的和可以由
                // “到达其左边位置的最短路径和到达其上方位置的最短路径的较小值+当前遍历值”表示
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + nums[i][j];//由于是求最短路径，因此需要取最小值
            }
        }
        return dp[row - 1][col - 1];//返回最右下角的位置的值，即为所求（数组的索引是从0开始的）
    }

    //写法2：妙在不需要额外新建一个二维数组，而是直接在原矩阵数组上修改，思路是一样的（要理解)
class Solution64 {
        public int minPathSum(int[][] grid) {
            //注意：获取一个二维数组的行数和列数的写法
            for(int i = 0; i < grid.length; i++) {//遍历行
                for(int j = 0; j < grid[0].length; j++) {//遍历列
                    //先找初始/边界情况，用于求解大问题
                    if(i == 0 && j == 0) continue;
                    else if(i == 0) {
                        grid[i][j] = grid[i][j - 1] + grid[i][j];//第一行的情况，是一个确定的值
                    } else if(j == 0) {
                        grid[i][j] = grid[i - 1][j] + grid[i][j];//第一列的情况，也是一个确定的值
                    }
                        //再看一般情况，要列状态方程（即递归/递推方程，这是关键）
                        //关键是理解好这个grid[i][j]的含义：即到达位置[i][j]的路径的最小值，之后则只需令该位置为最右下角的位置即可
                    else {
                        grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                    }
                }
            }
            return grid[grid.length - 1][grid[0].length - 1];//返回最右下角的位置的值，最终会通过初始值来得到结果，即为所求（数组的索引是从0开始的）
        }
    }
}