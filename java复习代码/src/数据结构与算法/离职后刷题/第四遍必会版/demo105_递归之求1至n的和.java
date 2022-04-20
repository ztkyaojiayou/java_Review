package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo105_递归之求1至n的和 {
    public static int Sum_Solution(int n) {
        //从n往下加
        int sum = n;
        boolean flag = (n > 1) && (sum = sum + Sum_Solution(n - 1)) > 0;
        return sum;
    }

    //自写一遍
    public static int Sum_Solution02(int n) {
        int sum = n;
        boolean flag = (n > 1) && (sum += Sum_Solution02(n - 1)) > 0;
        return sum;
    }


    public static void main(String[] args) {
        int res = demo105_递归之求1至n的和.Sum_Solution(8);
        System.out.println(res);
    }
}
