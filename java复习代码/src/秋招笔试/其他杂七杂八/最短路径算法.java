package 秋招笔试.其他杂七杂八;

import java.util.Scanner;

class Main02{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] arr = new int[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                arr[i][j] = sc.nextInt();
                if (arr[i][j] == -1) {
                    arr[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int[] min_time = method(arr);
        for (int i = 1; i < 6; i++) {
            System.out.println(min_time[i]);
        }
    }

    public static int[] method(int[][] weight) {
        int maxTime = Integer.MAX_VALUE;
        int N = 6;
        int[] min_time = new int[N];
        boolean[] isVisited = new boolean[N];

        for (int i = 0; i < N; i++) {
            min_time[i] = weight[0][i];
        }
        isVisited[0] = true;


        for (int i = 0; i < N; i++) {
            int min = maxTime;
            int k = 0;
            for (int j = 0; j < N; j++) {
                if (!isVisited[j] && min_time[j] < min) {
                    min = min_time[j];
                    k = j;
                }
            }
            isVisited[k] = true;
            for (int j = 0; j < N; j++) {
                if (!isVisited[j] && weight[k][j] != maxTime) {
                    if (min_time[j] >= weight[k][j] + min_time[k]) {
                        min_time[j] = weight[k][j] + min_time[k];
                    }

                }
            }
        }
        return min_time;
    }
}

