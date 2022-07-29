package 华为od机考练习.常规题库;

/**
 * 69）矩阵乘法
 *
 * @author :zoutongkun
 * @date :2022/7/29 3:27 下午
 * @description :
 * @modyified By:
 */

import java.util.Scanner;

public class Main69_自写 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //矩阵的行与列
        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();

        //构建矩阵
        //矩阵1-xy
        int[][] matrix1 = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                matrix1[i][j] = sc.nextInt();
            }
        }

        //矩阵2--yz
        int[][] matrix2 = new int[y][z];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < z; j++) {
                matrix2[i][j] = sc.nextInt();
            }
        }

        //结果矩阵--xz
        int[][] resMatrix = new int[x][z];
        //计算
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < z; j++) {
                for (int k = 0; k < y; k++) {
                    resMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        //打印结果--xz
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < z; j++) {
                if (j == z - 1) {
                    System.out.println(resMatrix[i][j]);
                    break;
                }
                System.out.print(resMatrix[i][j] + " ");
            }
        }

    }
}

