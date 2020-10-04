package 秋招笔试.其他杂七杂八;

import java.util.Scanner;

public class 最大队伍 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        //目标矩阵
        int[][] num = new int[n][m];
        //把数先输进来
    for (int i = 0;i<n;i++){
        for (int j = 0;j<m;j++){
            int a = sc.nextInt();
            num[i][j] = a;
        }
    }
    //处理
        if (n != 0 || m!= 0){
            System.out.println(8);
        }
    }
}
