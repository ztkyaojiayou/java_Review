package 数据结构与算法.剑指offer题解.回溯与递归;

import java.util.Arrays;

/**
 * 题目描述：
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中任意一格开始，每一步可以在矩阵中间向左、右、上、下移动一格。
 * 如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 * 例如在下面的 3*4 的矩阵中包含一条字符串“bcced”的路径。
 * 但矩阵中不包含字符串“abcb”的路径，因为字符串的第一个字符 b 占据了矩阵中的第一行第二格子之后，
 * 路径不能再次进入这个格子。

 a b c e
 s f c s
 a d e e
 *
 *【思路解析】：
 *  这是一个可以用回朔法解决的典型题。
 *
 *  首先，在矩阵中任选一个格子作为路径的起点。
 *  假设矩阵中某个格子的字符为 ch，那么这个格子不可能处在路径上的第 i 个位置。
 *      如果路径上的第 i 个字符不是 ch，那么这个格子不可能处在路径上的第 i 个位置。
 *      如果路径上的第 i 个字符正好是 ch，那么往相邻的格子寻找路径上的第 i+1 个字符。
 *      除在矩阵边界上的格子之外，其他格子都有 4 个相邻的格子。
 *  重复这个过程知道路径上的所有字符都在矩阵中找到相应的位置。
 *
 *  由于回朔法的递归特性，路径可以被看成一个栈。
 *  当在矩阵中定位了路径中前 n 个字符的位置之后，在与第 n 个字符对应的格子的周围都没有找到第 n+1 个字符，
 *  这个时候只要在路径上回到第 n-1 个字符，重新定位第 n 个字符。
 *
 *  由于路径不能重复进入矩阵的格子，还需要定义和字符矩阵大小一样的布尔值矩阵，用来标识路径是否已经进入每个格子。
 *
 *  当矩阵中坐标为（row,col）的格子和路径字符串中下标为 pathLength 的字符一样时，
 *  从 4 个相邻的格子 (row,col-1),(row-1,col),(row,col+1) 以及 (row+1,col) 中去定位路径字符串中下标为 pathLength+1 的字符。
 *
 *  如果 4 个相邻的格子都没有匹配字符串中下标为 pathLength+1 的字符，表明当前路径字符串中下标为pathLength的字符在矩阵中的定位不正确，
 *  我们需要回到前一个字符 (pathLength-1)，然后重新定位。
 *
 *  一直重复这个过程，直到路径字符串上所有字符都在矩阵中找到合适的位置
 *
 *(注意：DFS，回溯，递归，动态规划，贪心，这几个算法似乎天然就有某种内在的联系，务必要理解好）
 *
 */
public class 矩阵中的路径75 {
    /**
     * @param matrix 输入矩阵
     * @param rows   矩阵行数
     * @param cols   矩阵列数
     * @param str    要搜索的字符串
     * @return 是否找到 true是，false否
     */
    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        //0.特判
        if (matrix == null || matrix.length != rows * cols || str == null || str.length < 1) {
            return false;
        }

        //1.定义一个表示字符是否被访问过的boolean型的一维数组，默认为false，表示没有访问过
        //（则需要把坐标转化为一个数，其实也可以直接定义一个二维数组的）
        boolean[] visited = new boolean[rows * cols];
        for (int i = 0; i < visited.length; i++) {//赋值（默认值）
            visited[i] = false;
        }
        //Arrays.fill(visited,false);//该数组填充默认值，更方便，常用

        //2.记录结果的数组，表示已经处理的str中字符个数，当该值等于目标字符串的长度时就说明查找成功
        int[] pathLength = {0};
        //3.以每一个点为起始，调用回溯函数进行深度优先搜索，只要沿着某一个点找到了就返回true
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasPathCore(matrix, rows, cols, str, visited, i, j, pathLength)) {
                    return true;
                }
            }
        }
        //4.若以每一个点进行深度优先搜索都没有找到的话，就返回false
        return false;
    }

    /**
     * 回溯/递归搜索算法（核心）
     * @param matrix     输入矩阵
     * @param rows       矩阵行数
     * @param cols       矩阵列数
     * @param str        要搜索的字符串
     * @param visited    访问标记数组
     * @param row        当前处理的行号
     * @param col        当前处理的列号
     * @param pathLength 已经处理的str中字符个数
     * @return 是否找到 true是，false否
     */
    private static boolean hasPathCore(char[] matrix, int rows, int cols, char[] str, boolean[] visited,
                                       int row, int col, int[] pathLength) {

        //0.定义一个路径是否存在的标志，默认false
        boolean hasPath = false;

        //1.回溯/递归结束的条件，即若str中的字符全部存在，就返回true
        if (pathLength[0] == str.length) {
            return true;
        }

        //2.从当前位置开始回溯，有前提：1）位置要合法（即不能越界），2）当前字符和目标字符相等，3）该位置没有被访问过
        if (row >= 0 && row < rows
                && col >= 0 && col < cols//1）位置要合法（即不能越界）
                && matrix[row * cols + col] == str[pathLength[0]]//2）当前字符和目标字符相等
                && !visited[row * cols + col]) {//3）该位置没有被访问过

            //2.1将当前位置标志为已访问过，同时使“已经处理的str中字符个数pathLength”加1
            visited[row * cols + col] = true;//把坐标转化成了一个数，便于处理（因为每个位置是惟一的，
            // 则通过该换算（相当于把矩阵拉直后该位置的位置）之后也必然是惟一的）
            pathLength[0]++;

            //2.2做选择，按左上右下进行回溯，只要有一个位置
            hasPath = hasPathCore(matrix, rows, cols, str, visited, row, col - 1, pathLength)//向左
                    || hasPathCore(matrix, rows, cols, str, visited, row - 1, col, pathLength)//向上
                    || hasPathCore(matrix, rows, cols, str, visited, row, col + 1, pathLength)//向右
                    || hasPathCore(matrix, rows, cols, str, visited, row + 1, col, pathLength);//向下

            //2.3如果四个方向都没有找到，就撤销该位置，
            //即把已经处理的str中字符个数减1，同时把该位置又重置为false，以便下次还可以访问
            if (!hasPath) {
                pathLength[0]--;
                visited[row * cols + col] = false;
            }

        }
        //4.最后，返回结果
        return hasPath;
    }
}
