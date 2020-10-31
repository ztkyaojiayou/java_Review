package 数据结构与算法.第三遍;

public class demo105_递归_卢瑟夫环_圆圈中最后剩下的数 {
    public int LastRemaining_Solution(int n, int m) {//n为人数，m为随机指定的一个数
        //特判
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return 0;
        }
        int res = 0;

        //一般情况
        for (int i = 2; i <= n; i++) {
            //这个迭代公式是关键，注意，这里的i其实就是题给的n，
            //只不过这里是先从2开始迭代计算，直到n
            res = (res + m) % i;
        }
        return res;
    }
}
