package 数据结构与算法.剑指offer题解.位运算;

/**
 * 题目：输入一个整数，输出该数二进制表示中 1 的个数。
 *
 * 【解】典型的位运算
 */

public class BitOperation026一个数的二进制中1的个数 {
    //方法一（推荐）：易知，n 每和 (n-1) 相与(&)一次，1就减一，因此可以每与一次，就加1，直到为0
    public int NumberOf1(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt++;//所以cnt就记录着 n一共被减了多少次1，即n的二进制表示中1的个数
            n &= (n - 1);
        }
        return cnt;
    }

    /**
     * 方法二：
     * 自然想法：一个int有32位，那n的二进制的每一位都跟 00000000 ... ... 00000001（即1的二进制）与一下，
     *          结果为1，则cnt++
     *          否则 cnt 不变
     */
    private static int numberOfOne02(int n) {
        int cnt = 0;
        for (int i = 0; i < 32; i++) {  //Java语言规范中，int整形占四个字节，总计32位
            cnt += (n & 1);
            n = n >> 1;
        }
        return cnt;
    }
}
