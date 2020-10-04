package 秋招笔试.招银;

import java.util.Scanner;

public class test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //输入矩阵
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        int[] pathLength = {0};
        boolean[] visited = new boolean[n * n];
        for (int i = 0; i < visited.length; i++) {//赋值（默认值）
            visited[i] = false;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

            }
        }
        System.out.println(1);
    }
}
