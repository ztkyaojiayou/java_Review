package 数据结构与算法.离职后刷题.第四遍必会版;

/**
 * (1)先判断是否为丑数（非常简单）
 * 丑数定义：就是只包含质因数 2、3 和 5 的正整数。
 * 思路：根据丑数的定义，我们只需要对输入进行分情况讨论即可：
 * 如果 n 不是正整数（即小于等于 0）：必然不是丑数，直接返回 false。
 * 如果 n 是正整数：我们对 nn 执行 2 3 5 的整除操作即可，
 * 直到 n 被除干净，如果 n 最终为 1 说明是丑数，否则不是丑数。
 * 注意：2 3 5 先除哪一个都是可以的，因为乘法本身具有交换律。
 */
class demo09_5判断是否为丑数 {
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        //最后判断其值是否为1即可
        return n == 1;
    }
}

/**
 * (2)再求丑数序列（也不难）
 */
public class demo09_6三指针之求丑数序列 {
    public int GetUglyNumber_Solution(int N) {
        if (N < 6) {
            return N;
        }
        //三指针，先都指向第一个丑数，也即下面的res[p2]等
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;
        //用于存储所有丑数（共N个）
        int[] res = new int[N];
        res[0] = 1;//第一个丑数
        //通过循环，求每一个丑数
        for (int i = 1; i < N; i++) {
            //关键代码，找这三个指针构成的丑数的最小值作为当前位置的丑数
            res[i] = Math.min(res[p2] * 2, Math.min(res[p3] * 3, res[p5] * 5));
            //根据条件移动指针，即看当前这个丑数是由哪个指针贡献的，就让它向前移一位
            if (res[i] == res[p2] * 2) {
                p2++;
            } else if (res[i] == res[p3] * 3) {
                p3++;
            } else if (res[i] == res[p5] * 5) {
                p5++;
            }
        }
        return res[N - 1];
    }

    //自写一遍
    public int GetUglyNumber_Solution02(int N) {
        if (N < 6) {
            return N;
        }
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;
        int[] res = new int[N];
        res[0] = 1;
        for (int i = 1; i < N; i++) {
            res[i] = Math.min(res[p2] * 2, Math.min(res[p3] * 3, res[p5] * 5));
            if (res[i] == res[p2] * 2) {
                p2++;
            } else if (res[i] == res[p3] * 3) {
                p3++;
            } else if (res[i] == res[p5] * 5) {
                p5++;
            }
        }
        return res[N - 1];
    }
}
