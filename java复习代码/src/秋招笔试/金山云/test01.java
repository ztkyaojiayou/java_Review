package 秋招笔试.金山云;

import java.util.Scanner;

public class test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        fi(n+1);
    }
    public static void fi(int n) {
        if(n==0) {
            return;
        }
        if(n==1) {
            System.out.println(1);
            return;
        }
        System.out.println(1);
        int[] p = new int[n];

        p[0]=0;
        p[1]=1;
        for(int i=2;i<n;i++) {
            p[i]=p[i-1]+p[i-2];
            for(int j=1;j<i;j++) {
                System.out.print(p[j]+" ");
            }
            for(int j=i;j>=1;j--) {
                System.out.print(p[j]+" ");
            }
            System.out.println();
        }
    }
}


