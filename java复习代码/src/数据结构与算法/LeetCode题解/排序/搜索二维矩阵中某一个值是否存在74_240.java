package 数据结构与算法.LeetCode题解.排序;

/**
 * 74. 搜索二维矩阵（入门级）
 *
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。
 * 该矩阵具有如下特性：
 *     每行中的整数从左到右按升序排列。
 *     每行的第一个整数大于前一行的最后一个整数。
 *
 * 示例 1:
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 *
 * 示例 2:
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 */

/**
 * 思路解析：注意到输入的 m x n 矩阵可以视为长度为 m x n的有序数组。
 * 由于该数组的下标可以由下式方便地转化为原矩阵中的行和列 (我们当然不会真的去创建一个新数组) 。
 *     row = 下标 / 列数 ， col = 下标 % 列数。（务必记住这个对应关系）
 * 因此此题是一个标准二分查找算法的应用。
 *
 具体过程：
 （1）初始化左右序号：left = 0 和 right = m x n - 1。
 （2）While left < right :
     选取该数组最中间的序号作为中间序号: mid = (left + right) / 2。
     该序号对应于原矩阵中的 row = mid / n行 col = mid % n 列, 由此可以拿到中间元素mid_value。该元素将该数组（假想即可）分为两部分。
 （3）再比较 mid_value 与 target 的大小，以确定在哪一部分进行进一步的二分查找。

 注意：
 该题完全可以照搬下一题（即第240题）的代码，
 因为完全符合每一行都是递增序列，每一列也都是递增序列的特点，
 但执行效率远远没有二分查找高，因此不推荐使用。

 */
class solution74 {
        public boolean searchMatrix(int[][] matrix, int target) {
            //0.特判,即若数组为空时（有三种表示方法）则直接false，表示没找到
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
                return false;
            }
            //1.计算该矩阵的行数和列数
            int rows = matrix.length;//行数
            int cols = matrix[0].length;//列数

            //2.开始二分查找
            //2.1先定义二分查找的左右边界即中间索引及其值
            int left = 0, right = rows * cols - 1;
            int mid, mid_value;
            //2.2开始查找
            while (left <= right) {
                mid = (left + right) / 2;//中间索引
                //中间索引对应的值，可以直接由该矩阵的行和列表示出来，
                //而不需要真把该矩阵转化为一个一维数组，这是一个技巧问题。
                mid_value = matrix[mid / cols][mid % cols];
                //1）若当前中间值和目标值相等，则直接返回true
                if (target == mid_value) {
                    return true;
                }//2）否则，若目标值相等小于当前中间值，则缩小右边界至mid - 1，继续进行而非查找
                else if (target < mid_value){
                    right = mid - 1;
                } else//3）而若目标值大于当前中间值，则调整左边界至mid + 1，继续进行而非查找
                    left = mid + 1;
                }
            //3.若最终都没有找到，则返回false即可
            return false;
        }
    }

/**
 * 240. 搜索二维矩阵 II（进阶版）
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。
 * 该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下也是升序排列。
 *
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 */

/**
 * 思路解析：该题和剑指offer上的第一题相同，简单。
 *
 * 方法：选取数组中右上角的数和目标值比较，再剔除一行或一列以缩小范围。
 * 1.若该数字等于要查找的数字，查找过程结束
 * 2.若该数字大于要查找的数字，剔除这个数字所在的列
 * 3.若该数字小于要查找的数字，剔除这个数字所在的行。
 * 4.也就是说如果要查找的数字不在数组的右上角，则每－次都在数组的查找范围中剔除一行或者一列，
 *   这样每一步都可以缩小查找的范围，直到找到要查找的数字，或者查找范围为空。
 */
class solution240 {
    public boolean Find(int target,int[][] matrix){//array 待查找的数组,target 要查找的数
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)//数组为空时则直接false，表示没找到
            return false;//查找结果，true找到，false没有找到

        int rows = matrix.length;// 二维数组的行数
        int cols = matrix[0].length; //二维数组的列数

        //1.从最右上角的那个数开始对比/查找，这个数即为：array[0][cols-1]（注意：索引是从0开始的）
        int r = 0;// 起始开始的行号/索引（即为最右上角的那个数的所在行），当向下移动时，则r+1，但不能大于rows - 1（即为最大行的索引）
        int c = cols - 1;// 起始开始的列号/索引（即为最右上角的那个数所在的列），当向左移动时，则c-1，但不能小于0

        // 2.要查找的位置确保在数组之内，比较目标值target 与当前值 array[r][c] 的大小，确定应该像哪边移动
        while(r<= rows - 1&& c >=0){
            if(target == matrix[r][c])// array[r][c]即为array[0][cols-1]，即右上角那个数
                return true;//如果找到了就直接退出
            else if(target>matrix[r][c])// 如果要找的数比找到的数大，说明要找的数在当前数的下边
                r ++;// 行数加一，代表向下移动
            else// 如果要找的数比找到的数小，说明要找的数在当前数的左边
                c--;// 列数减一，代表向左移动
        }
        return false;//若最终都没有找到，则返回false
    }
}
