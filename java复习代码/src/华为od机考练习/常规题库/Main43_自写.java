package 华为od机考练习.常规题库;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author :zoutongkun
 * @date :2022/7/28 11:46 下午
 * @description :
 * @modyified By:
 */
public class Main43_自写 {
    //需要在最外面声明，否则在方法中识别不了
    //定义list来存储到达过的位置--存一维数组
    public static List<int[]> path = new ArrayList<>();
    //定义best_path来存储达到终点的路径位置
    public static List<int[]> best_path = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        //构造矩阵
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        //开始递归+回溯
        method(0, 0, matrix);
        //输出
        for (int[] position : best_path) {
            System.out.println("(" + position[0] + "," + position[1] + ")");
        }
    }

    /**
     * 递归+回溯
     *
     * @param curRow
     * @param curCol
     * @param matrix
     */
    private static void method(int curRow, int curCol, int[][] matrix) {
        //终止条件1--越界，撞墙
        if (curRow < 0 || curRow > matrix.length - 1 || curCol < 0 || curCol > matrix[0].length - 1 || matrix[curRow][curCol] == 1) {
            //终止
            return;
        }
        //终止条件2--到达了终点(能到这一步说明当前位置肯定为0，否则在上一步就已经终止啦，因此不用再加matrix[curRow][curCol] == 0了
        if (curRow == matrix.length - 1 && curCol == matrix[0].length - 1) {
            //将该位置先加入path
            path.add(new int[]{curRow, curCol});
            //此时path中的路径已经是所求的了，那么能不能在结果中直接输出该path呢？
            //答：不能，因为这个递归方法到这儿还并没有结束，之后还会继续回溯+递归，也即该path的值是会继续改变的
            //因此当走到终点时，需要立马将该path的值保存下来，然后再输出即可，
            //同时，由于题干说了，只有一条到达终点的路径，因此递归方法能到这一步也就这一次，也因此就不存在所谓最短路径了
            //只需使用一个新的list保存该值即可
            for (int[] position : path) {
                best_path.add(position);
            }
            //也终止
            return;
        }

        //常规情况，开始递归，回溯
        //标记该位置并添加至path
        matrix[curRow][curCol] = 1;
        path.add(new int[]{curRow, curCol});
        //四处搜搜
        method(curRow, curCol - 1, matrix);//上
        method(curRow, curCol + 1, matrix);//下
        method(curRow - 1, curCol, matrix);//左
        method(curRow + 1, curCol, matrix);//右
        //回溯/回撤--也是两步
        matrix[curRow][curCol] = 0;
        path.remove(path.size() - 1);
    }
}
