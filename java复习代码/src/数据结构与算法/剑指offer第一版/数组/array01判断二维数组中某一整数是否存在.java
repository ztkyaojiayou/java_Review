package 数据结构与算法.剑指offer第一版.数组;

/**
 * 刷题须知：
 * 在牛客网进行OJ线上编程时，对于<剑指Offer>这种有函数定义的题目，只需要完成函数，返回相关的值就可以啦
 * 不需要处理任何输入输出，不需要写测试案例（牛客后台会自动跑案例case），也不需要在函数里输出任何东西
 * 因此，在这个包下面的所有题目的代码都只提供核心方法，而没有写具体测试案例啦
 */

/**
 * （1）题目：在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列也都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。（简单）
 *
 * （2）方法：选取数组中右上角的数和目标值比较，再剔除一行或一列以缩小范围。
 *          1.若该数字等于要查找的数字，查找过程结束
 *          2.若该数字大于要查找的数字，剔除这个数字所在的列
 *          3.若该数字小于要查找的数字，剔除这个数字所在的行。
 *          4.也就是说如果要查找的数字不在数组的右上角，则每－次都在数组的查找范围中剔除一行或者一列，
 *          这样每一步都可以缩小查找的范围，直到找到要查找的数字，或者查找范围为空。
 *
 * （3）注解：二维数组 array[][]求长度的方法：（没有直接获取第二维长度的方法）
 *     1.array.length 第一维的长度
 *     2.array[0].length 第二维的长度（当然这个array数组中要有元素，不然取array[0]就会报错的
 *     （思路）先取数组中的一个元素array[0]，因为这个数组是二维的，所以取出来的元素还是一个（一维）数组，
 *           再取这个数组的长度，即为第二维的长度
 */
public class array01判断二维数组中某一整数是否存在 {
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
            {
                return true;//如果找到了就直接退出
            } else if(target>matrix[r][c])// 如果要找的数比找到的数大，说明要找的数在当前数的下边
            {
                r ++;// 行数加一，代表向下移动
            } else// 如果要找的数比找到的数小，说明要找的数在当前数的左边
            {
                c--;// 列数减一，代表向左移动
            }
        }
        return false;//若最终都没有找到，则返回false
    }
}
