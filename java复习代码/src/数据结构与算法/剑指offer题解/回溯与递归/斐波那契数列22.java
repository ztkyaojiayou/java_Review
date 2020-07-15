package 数据结构与算法.剑指offer题解.回溯与递归;

/**
 * 题目描述
 * 大家都知道斐波那契数列，现在要求输入一个整数n，
 * 请你输出斐波那契数列的第n项（从0开始，第0项为0，第1项是1）。
 * n<=39
 */

/**
 * 思路解析：很简单，和爬楼梯一样。
 */
//方法1：递归（不推荐，写法简单，但执行效率很慢）
class 斐波那契数列22_1 {
    int Fibonacci(int n) {
        //1.递归结束的条件。也即前两项的值
        if (n==0 || n==1) return n;
        //2.对于其他项，使用递归即可
        return Fibonacci(n-1) + Fibonacci(n-2);
    }
}

//方法2：动态规划（推荐）
class Solution22_2 {
    public int Fibonacci(int n) {
        //1.定义一个dp数组
        int dp[] = new int[40];//比题给的n的最大值还多一位，考虑起始位置
        //2.确定初始值
        dp[0] = 0;
        dp[1] = 1;
        //3.开始计算一般情况，使用状态方程即可
        for(int i=2;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        //4.最后，返回结果即可
        return dp[n];
    }
}
//方法3：动态规划的优化（强烈推荐）
//其实我们可以发现每次就用到了最近的两个数，所以我们可以只存储最近的两个数即可
//sum 存储第 n 项的值
//pre1 存储第 n-1 项的值
//pre2 存储第 n-2 项的值
class solution22_3{
    int Fibonacci(int n) {
        if (n == 0 || n == 1) return n;
        int pre2 = 0, pre1 = 1, sum = 0;
        for (int i=2; i<=n; ++i) {
            sum = pre2 + pre1;
            pre2 = pre1;
            pre1 = sum;
        }
        return sum;
    }
}

//方法4：持续优化
//观察上一版发现，sum 只在每次计算第 n 项的时候用一下，其实还可以利用 sum 存储第 n-1 项，
//例如当计算完 f(5) 时 sum 存储的是 f(5) 的值，
//当需要计算 f(6) 时，f(6) = f(5) + f(4)，sum 存储的 f(5)，
//f(4) 存储在 pre2 中，由 f(5)-f(3) 得到.
class Solution22_4 {
    public int Fibonacci(int n) {
        //0.特判
        if(n == 0){
            return 0;
        }else if(n == 1) {
            return 1;
        }
        //1.初始值
        int sum_pre1 = 1;//该变量即表示第n项的和sum，也表示它的前一项的值pre1
        int pre2 = 0;//存储第n-2项的值
        //2.计算其他值
        for(int i=2;i<=n;i++){
            sum_pre1 = sum_pre1 + pre2;
            pre2 = sum_pre1 - pre2;
        }
        //3.最终返回结果即可
        return sum_pre1;
    }
}