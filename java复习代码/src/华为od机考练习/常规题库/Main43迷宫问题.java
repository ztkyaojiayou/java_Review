package 华为od机考练习.常规题库;

/**
 * 43）迷宫问题--递归+回溯
 *
 * @author :zoutongkun
 * @date :2022/7/28 11:15 下午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main43迷宫问题 {
    //里面存放的是各个位置--使用一维数组存储
    public static List<int[]> path = new ArrayList<>();//搜索所有可能的路径，但
    public static List<int[]> best_path = new ArrayList<>();//最短路径

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        // 注意 while 处理多个 case
//        while (in.hasNextInt()) {
//            //每个用例之前，都要清空下路径
//            path.clear();
//            best_path.clear();
        int row = in.nextInt();
        int col = in.nextInt();
        //构造迷宫--二维数组
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        //深搜+回溯：从第一个位置开始走，可以往四个位置走
        dfs(0, 0, matrix);
        //打印结果
        for (int[] position : best_path) {
            System.out.println("(" + position[0] + "," + position[1] + ")");
//            }
        }
    }

    /**
     * 深搜+回溯：从第一个位置开始走，可以往四个位置走
     *
     * @param i
     * @param j
     * @param matrix
     */
    public static void dfs(int i, int j, int[][] matrix) {
        //1.终止条件
        //1.1越界了或撞墙了时，都终止--return
        if (i < 0 || i > matrix.length - 1 || j < 0 || j > matrix[0].length - 1 || matrix[i][j] == 1) {
            return;
        }
        //1.2找到终点了，也终止，--return
        //同时做如下处理：
        //1)将终点先添加至path中
        //2)同时，更新最短路径：即判断当前path和best_path中的元素大小，
        //若小于best_path中的元素，则更新best_path中的元素为当前path的元素（先clear后add）
        //也就是说这个题中的最短路径并不是直接求出来的，而是比较出来的，所以其实还是常规的递归题！！！
        if (i == matrix.length - 1 && j == matrix[0].length - 1) {
            //1.2.1添加终点
            path.add(new int[]{matrix.length - 1, matrix[0].length - 1});

            //2）再添加当前路径（因为题干说只有唯一的一条路径，
            // 因此，直接添加该最终到达了终点的路径即可--其他不行，因为没有到达终点）
            for (int[] path0 : path) {
                best_path.add(path0);
            }

//            //1.2.2遇到更短的路径，更新为当前更短的路径-本题没必要，因为就一条路径到终点
//            if (best_path.size() == 0 || path.size() < best_path.size()) {
//                //1）清空之前的路径
//                best_path.clear();
//                //2）再添加当前路径
//                for (int[] path0 : path) {
//                    best_path.add(path0);
//                }
//            }
            return;
        }
        //2.正常搜索时，分为如下几步
        //2.1标记走过的点，即把当前位置置为1
        matrix[i][j] = 1;
        //2.2添加到路径中--就是添加至“所有可能的路径”数组中，
        //而不是“最短路径”数组(这个是以前者为前提的）
        path.add(new int[]{i, j});
        //2.3以i j为中心，往上下左右四个位置搜索--递归搜索
        dfs(i - 1, j, matrix);
        dfs(i + 1, j, matrix);
        dfs(i, j - 1, matrix);
        dfs(i, j + 1, matrix);
        //3.回溯，即恢复到之前的状态
        //3.1将当前位置重新置为0
        matrix[i][j] = 0;
        //3.2移除path中的最后一个位置
        path.remove(path.size() - 1);
    }
}

