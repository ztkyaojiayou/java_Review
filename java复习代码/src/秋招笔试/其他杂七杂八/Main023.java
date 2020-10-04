package 秋招笔试.其他杂七杂八;

import java.util.Scanner;

public class Main023 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        long c = sc.nextLong();
        long d = sc.nextLong();
        long[] nums = new long[]{a, b, c, d};
        if (a + b + c + d < 4) {
            System.out.print(-1);
            return;
        }
        long sum = 0;
        for (long num : nums) {
            sum += num;
        }

        long average = (sum / 4) + 1;
        int load;
        do {
            average--;
            load = 0;
            long aa = 0;
            for (long num : nums) {
                if (num <= average) {
                    load += (num - average);

                } else {
                    aa += (num - average);

                }
            }
            load += (aa >> 1);
        } while (load < 0);

        System.out.print(4*average);
    }
}
