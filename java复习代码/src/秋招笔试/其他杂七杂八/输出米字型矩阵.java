package 秋招笔试.其他杂七杂八;

import java.util.Scanner;

public class 输出米字型矩阵 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //目标矩阵
        int[][] num1 = new int[n][n];
        //把数先输进来
        int[] num2 = new int [7];
        for (int i = 0;i<7;i++){
            int a = sc.nextInt();
            num2[0] = a;
        }
        //对角线
        for (int i = 0;i<n;i++){
            num1[i][i] = 0;
        }
        for (int j =n-1;j>0;j--){
            num1[j][j] = 0;
        }
        //for (int i = 0;i<n;i++){
        //    for (int j = 1;j<n;j++){
        //        num1[i][j] = 2;
        //    }
        //
        //}
        num1[0][1] =2;
        num1[0][2] =1;
        num1[1][0] =3;
        num1[1][3] =8;
        num1[2][0] =4;
        num1[2][3] =7;
        num1[3][1] =5;
        num1[3][2] =6;
        for (int i = 0;i<n;i++){
            for (int j = 0;j<n;j++){
                    System.out.print(num1[i][j] + " ");
            }
            System.out.println();
        }
//        //为偶数时
//        if (n % 2 == 0){
////            for (int i = 0,j = 1;i<(n/2-1);i++){
////                while (j<(n/2)){
////                    num1[i][j] = 2;
////                    j++;
////                }
////                j = 0;
////                j++;
////
//////num1[i][j] = 2;
//////j++;
////            }
//
//
//
//        }else {
//            //为奇数时
//        }

        //for (int i = 0,j = 0;i<n;i++,j++){
        //    while (i<n/2){
        //        num1[i][j] = 2;
        //        j++;
        //    }
        //    while (i>= n/2){
        //        i = n/2;
        //
        //    }
        //
        //}
    }
}
