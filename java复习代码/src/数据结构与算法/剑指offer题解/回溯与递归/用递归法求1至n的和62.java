package 数据结构与算法.剑指offer题解.回溯与递归;

/**
 * 题目描述
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */

/**
 * 思路解析：放心，简单的方法都是不符合要求的，这里就不讲啦
 * 因此可以使用递归的思想来解，但是普通的递归也是不符合要求的，
 * 因为在判断终止条件时还是需要用到if关键字，于是我们必须在此基础上优化。
 * 先还是讲一下普通的递归方法：
 * 用f(n)表示求1-n的和。
 * 易知递推公式为：f(n) = f(n-1) + n
 * 递归终止条件为：f(1) = 1
 */

/**
 * 常规递归（不符合题意）
 */
class 用递归法求1至n的和62_1 {
    //普通的递归方法，不符合要求
    public int Sum_Solution(int n) {
        //递归终止的条件
        if (n == 1) return n;
        //利用递归求和
        return n + Sum_Solution(n-1);
    }
}

/**
 * 方法二：递归变形(完美）
 * 如果我们把方法三种的if换成别的，就可以了。
 * if (n==1) return 1;
 * 也就是说如果n==1,需要终止递归，所以我们想到了逻辑与&&连接符。
 * A&&B，表示如果A成立则执行B，否则如果A不成立，不用执行B
 * 因此我们可以这样。在n>1的时候，执行递归函数。
 */
class solution62_2{
    public int Sum_Solution(int n) {
        boolean flag = (n>1)&&((n+=Sum_Solution(n-1))>0);//即只有n大于1时才执行后面的递归函数（逻辑与的性质）
        return n;//最后，返回结果即可
    }
}

/**
 * 方法一：求和公式的位处理（完美）
 * 易知求和公式为（n*n+n)/2，但由于不能使用除法，因此可以使用位处理，
 * 理论依据为：n除以2相当于n向右移一位，即n>>1，问题便迎刃而解。
 */
class Solution62_3 {
    public int Sum_Solution(int n) {
        int sum = (int) (Math.pow(n,2) + n);//先求n*n+n
        return sum>>1;//再通过位运算将其右移一位即可，即除以2。
    }
}
