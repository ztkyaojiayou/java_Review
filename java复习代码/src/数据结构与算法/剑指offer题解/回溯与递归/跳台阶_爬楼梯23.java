package 数据结构与算法.剑指offer题解.回溯与递归;

/**
 * 题目描述（简单）
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */

/**
 * 思路解析：和矩形覆盖那道题完全相同，使用递归即可

 对于本题,前提只有 一次 1阶或者2阶的跳法。
 a.如果两种跳法，1阶或者2阶，那么假定第一次跳的是一阶，那么剩下的是n-1个台阶，跳法是f(n-1);
 b.假定第一次跳的是2阶，那么剩下的是n-2个台阶，跳法是f(n-2)
 c.由a\b假设可以得出总跳法为: f(n) = f(n-1) + f(n-2)
 d.然后通过实际的情况可以得出：只有一阶的时候 f(1) = 1 ,只有两阶的时候可以有 f(2) = 2
 e.可以发现最终得出的是一个斐波那契数列：
            | 1, (n=1)
    f(n) =  | 2, (n=2)
            | f(n-1)+f(n-2) ,(n>2,n为整数)

 注意：下面所列举的所有方法对斐波那契数列，矩形覆盖都有效。
 */
//方法一：递归
//面试别写型递推版实现，时间复杂度为O（2的n次幂) 。
class Solution23_1{
    public int JumpFloor(int target) {
        if(target < 1)
            return 0;
        else if(target==1)
            return 1;
        else if(target==2)
            return 2;
        else
            return JumpFloor(target-1)+JumpFloor(target-2);
    }
}
//方法2：动态规划（推荐）
class Solution23_2{
    int JumpFloor(int target) {
        //0.特判
        if(target == 1){
         return 1;
        }
        //1.定义一个dp数组，用于表示从起点到达第 i 个楼梯的路径总数
     int[] dp = new int[target + 1];// 多开⼀位，考虑起始位置
        //2.确定初始值
     dp[0] = 0;
     dp[1] = 1;
     dp[2] = 2;
     //3.再考虑一般情况
     for(int i = 3; i <= target; ++i) {
     dp[i] = dp[i - 1] + dp[i - 2];
    }
     //4.最后返回结果即可，即dp[target]
       return dp[target];
    }
}

//方法3：动态规划的优化版（强烈推荐）
//面试推荐型，自底向上型循环求解，时间复杂度为O(n) 。
//其实我们可以发现每次就用到了最近的两个数，所以我们可以只存储最近的两个数即可
//sum 存储第 n 项的值
//pre1 存储第 n-1 项的值
//pre2 存储第 n-2 项的值
class Solution23_3{
    //写法1：
    public int JumpFloor01(int target) {
        if(target <= 2){
            return target;
        }
        int pre2 = 1, pre1 = 2,sum = 0;
        for (int i = 3; i <= target; i++){
            sum = pre2 + pre1;//即当前值等于前两个值的和
            pre2 = pre1;//同时，把前第二个值置为前第一个值
            pre1 = sum;//把前第一个值置为刚才计算出来的sum值
        }
        //最后，返回sum即可
        return sum;
    }
}

//方法4：持续优化
//观察上一版发现，sum 只在每次计算第 n 项的时候用一下，其实还可以利用 sum 存储第 n-1 项，
//例如当计算完 f(5) 时 sum 存储的是 f(5) 的值，
//当需要计算 f(6) 时，f(6) = f(5) + f(4)，sum 存储的 f(5)，
//f(4) 存储在 one 中，由 f(5)-f(3) 得到
    class solution23_4{
        public int JumpFloor02(int target) {
            int sum_pre1 = 1, pre2 = 1;
            for (int i = 1; i < target; i++) {
                sum_pre1 = sum_pre1 + pre2;
                pre2 = sum_pre1 - pre2;
            }
            return sum_pre1;
        }
    }
