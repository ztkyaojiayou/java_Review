package 数据结构与算法.第三遍;

public class demo09_6三指针之求丑数序列 {
    public int GetUglyNumber_Solution(int N) {
        if (N < 6) {
            return N;
        }
        //三指针，先都指向第一个丑数，也即下面的res[p2]等
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;
        int[] res = new int[N];
        res[0] = 1;//第一个丑数
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
