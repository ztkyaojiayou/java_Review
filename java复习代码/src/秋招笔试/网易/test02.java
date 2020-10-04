package 秋招笔试.网易;

import java.util.Arrays;
import java.util.Scanner;

public class test02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int res = test02.method(str);
        System.out.println(res);
    }
    public static int method(String s) {
        int res = 0;
        int length = s.length();
        int prefixSum = 0;
        int[] firstOccur = new int[32];
        Arrays.fill(firstOccur, -1);
        for(int i=0; i<length; i++) {
            if(s.charAt(i) == 'a') prefixSum ^= 1;
            if(s.charAt(i) == 'b') prefixSum ^= 1<<1;
            if(s.charAt(i) == 'c') prefixSum ^= 1<<2;
            if(s.charAt(i) == 'x') prefixSum ^= 1<<3;
            if(s.charAt(i) == 'y') prefixSum ^= 1<<4;
            if(prefixSum == 0) res = i+1;
            if(firstOccur[prefixSum] != -1) res = Math.max(res, i-firstOccur[prefixSum]);
            else firstOccur[prefixSum] = i;
        }
        return res;
    }
}
