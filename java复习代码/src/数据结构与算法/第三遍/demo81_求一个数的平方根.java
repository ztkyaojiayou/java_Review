package 数据结构与算法.第三遍;

public class demo81_求一个数的平方根 {
    /**
     * 如果一个数 i，i * i 小于等于 n，（i + 1） * （i + 1）大于 n，
     * 那么这个数就是 n 的平方根。
     * 从 1 开始遍历即可。
     */
    public int sqrt (int n) {
        if (n <= 0) return 0;
        int i = 1;
        for (i = 1; i <= n; i ++) {
            if (i * i <= n && (i + 1) * (i + 1) > n) {
                break;
            }
        }
        return i;
    }
}
