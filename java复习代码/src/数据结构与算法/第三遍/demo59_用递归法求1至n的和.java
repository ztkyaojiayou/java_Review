package 数据结构与算法.第三遍;

public class demo59_用递归法求1至n的和 {
    public int Sum_Solution(int n) {
        //从n往下加
        int sum = n;
        boolean flag = (n>1)&& (sum = sum + Sum_Solution(n-1))>0;
        return sum;
    }
}
