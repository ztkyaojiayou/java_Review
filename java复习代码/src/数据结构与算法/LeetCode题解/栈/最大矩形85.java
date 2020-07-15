package 数据结构与算法.LeetCode题解.栈;

import java.util.Stack;

/**
 * 85. 最大矩形
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，
 * 找出只包含 1 的最大矩形，并返回其面积。
 *
 * 示例:
 * 输入:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * 输出: 6
 */

/**
 * 解题思路：
 * 将输入拆分成一系列的柱状图，每个柱状图代表一列的子结构。
 * 为了计算长方形的最大面积，我们仅仅需要计算每个柱状图中的最大面积并找到全局最大值
 *
 * 84题是基础，求算最大面积，采用分治的方式，分别计算三个区间的最大值。
 * 然后将每一行为底的数组，转成柱状图，
 * 用dp[列数]来保存以每一行作为底的柱子的高度，从row =0开始。
 * 如果当前(i,j)为底的值为‘1’，则dp[j] = 前一行的dp[j] + 1,即dp[j] += 1;
 *
 *  * [ dp[0] dp[1] dp[2] dp[3] dp[4]
 *  *   ["1",  "0",  "1",  "0",  "0"],
 *  *   ["1",  "0",  "1",  "1",  "1"],
 *  *   ["1",  "1",  "1",  "1",  "1"],
 *  *   ["1",  "0",  "0",  "1",  "0"]
 *  * ]
 *  （1）问题转化：
 *  把dp数组中的每一个元素dp[0],dp[1],dp[2],dp[3],dp[4]看成是一个柱子，
 *  其值就代表第84题中的柱子高度，而下标也就是84题中柱子所在的位置，于是问题就完美转化为了第84题。
 *  （2）而对于本题的算法则为：
 *  先计算第一行的dp所组成的柱状图，再计算第一行和第二行叠加的dp
 *  ，以此类推，最后计算所有行合并之后的dp
 */
public class 最大矩形85 {
        public int maximalRectangle(char[][] matrix) {
            //0.特判
            if (matrix.length == 0) {
                return 0;
            }
            //1.定义一个结果变量和一个dp数组
            int res = 0;
            // dp[]数组，用于存储84题中的柱状图高度(矩阵每列)
            int[] dp = new int[matrix[0].length];
            //2.开始遍历矩阵，每遍历一行，就连同上一行的结果取求此时的最大面积，直到遍历完所有的行
            for (int i = 0; i < matrix.length; i++) {//遍历行
                //2.1计算第i行的柱子高度（即1的个数，会叠加上一行对应的列的结果）
                for (int j = 0; j < matrix[0].length; j++) {//遍历列
                    if (matrix[i][j] == '1') {
                        dp[j] += 1;
                    } else {
                        dp[j] = 0;
                    }
                }
                //2.2再一行一行叠加求面积
                //即先计算把第i行作为底的柱子的最大面积，接着再计算把第一行和第二行合并之后的最大面积，
                //以此类推，最后再计算第一行至第四行合并之后的最大面积，每合并一行并计算后就和上一次的结果进行比较，
                //取较大者作为最终的结果，直到最后，就可以返回最大的面积值啦~
                res = Math.max(res, largestRectangleArea(dp));
            }
            //3.最后，返回最大值res即可
            return res;
        }

    //使用单调栈求柱状图中的最大面积,完全是照搬第84题的代码，即可以直接用
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        int[] copied_arr = new int[heights.length + 2];
        for (int i = 1; i < heights.length + 1; i++) {
            copied_arr[i] = heights[i - 1];
        }
        for (int i = 0; i < copied_arr.length; i++) {
            while (!stack.isEmpty() && copied_arr[stack.peek()] > copied_arr[i]) {
                int cur = stack.pop();
                res = Math.max(res, (i - stack.peek() - 1) * copied_arr[cur]);
            }
            stack.push(i);
        }
        return res;
    }

    //测试
    public static void main(String[] args) {
        char[][] array ={{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','1','0',
                '0'}} ;
        最大矩形85 test = new 最大矩形85();
        int result = test.maximalRectangle(array);
        System.out.println(result);
    }
}

