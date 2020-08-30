package 数据结构与算法.秋招笔试;

import java.util.Arrays;
import java.util.Scanner;

public class 广联达_排序 {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; ++i) {
                arr[i] = sc.nextInt();
            }
            System.out.println(sort(arr));
        }

        public static int sort(int[] arr) {
            int n = arr.length;
            int[] sortArr = Arrays.copyOf(arr, n);
            Arrays.sort(sortArr);
            int i = n - 1;
            int j = n - 1;
            while (i >= 0 && j >= 0) {
                if (arr[i] == sortArr[j]) {
                    i--;
                    j--;
                } else {
                    while (i >= 0 && arr[i] != sortArr[j]) {
                        i--;
                    }
                }
            }
            return j + 1;
        }
    }
