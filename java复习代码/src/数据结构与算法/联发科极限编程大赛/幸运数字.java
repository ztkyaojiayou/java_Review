package 数据结构与算法.联发科极限编程大赛;

import java基础.多态.A;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * 在我们的日常生活中，八是一个幸运数字。如果一个数字的每一个数字都是8，那么它就被认为是一个幸运数字。
 * 老师现在给了Lili一个正整数L，希望她能找出8有几位是L的倍数，并问最小的位数，使其为幸运数字。
 * 丽丽觉得很困难，你能帮她吗?
 * 输入
 * 输入包含多个测试用例集。
 * 每一组测试用例占用一行并包含一个整数L。
 * 当输入用例L=0时，输入终止，不需要处理用例。
 * 数据范围:1≤L≤2∗109
 * 输出
 * 每组测试用例都有一行输出。
 * 结果是“情况1:”+一个整数N，它表示满足条件的最不幸运的数字的位数。
 */
//public class 幸运数字 {
    class Main {
        public static void main(String args[]) {
            new Main().start();
        }

        long l, m;
        BigInteger M;

        long gcd(long va, long vb) {
            return va == 0 ? vb : gcd(vb % va, va);
        }

        BigInteger power(BigInteger a, long b) {
            if (b == 0) {
                return BigInteger.ONE;
            }
            if ((b & 1l) == 0) {
                return power(a.multiply(a).mod(M), b >> 1);
            }
            return a.multiply(power(a, b - 1)).mod(M);
        }

        long phi(long v) {
            long n = v;
            if ((v & 1) == 0) {
                n >>= 1;

                while ((v & 1) == 0)
                    v >>= 1;
            }
            for (int i = 3; i * i <= v; i += 2) {
                if (v % i == 0)
                    n -= n / i;
                while (v % i == 0)
                    v /= i;
            }
            if (v != 1) {
                n -= n / v;
            }
            return n;
        }

        void start() {

            Scanner in = new Scanner(System.in);

            BigInteger TEN = BigInteger.valueOf(10);
            int cas = 1;
            while (true) {
                l = in.nextLong();

                if (l == 0) {
                    break;
                }

                m = 9l * l / gcd(l, 8);
                M = BigInteger.valueOf(m);
                m = phi(m);
                long ans = 0;
                long i = 1;
                for (; i * i <= m; i++) {
                    if ((m % i) != 0)
                        continue;

                    if (power(TEN, i).intValue() == 1) {
                        ans = i;
                        break;
                    }
                }

                if (ans == 0) {
                    i--;
                    for (; i >= 1; i--) {
                        if ((m % i) != 0)
                            continue;
                        if (power(TEN, m / i).intValue() == 1) {
                            ans = m / i;
                            break;
                        }
                    }
                }

                System.out.println("Case " + cas++ + ": " + ans);
            }

        }
    }
