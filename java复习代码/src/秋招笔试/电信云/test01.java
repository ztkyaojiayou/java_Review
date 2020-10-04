package 秋招笔试.电信云;

import java.util.Scanner;

public class test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int res = method(a);
        System.out.println(res);
    }

    public static int method(int index) {
        if (index <= 6)
            return index;

        int p2=0,p3=0,p5=0;
        int[] result = new int[index];
        result[0] = 1;
        for(int i=1; i < index; i++){

            result[i] = Math.min(result[p2]*2, Math.min(result[p3]*3, result[p5]*5));
            if(result[i] == result[p2]*2)p2++;//为了防止重复
            if(result[i] == result[p3]*3)p3++;
            if(result[i] == result[p5]*5)p5++;
        }
        return result[index-1];
    }
}
