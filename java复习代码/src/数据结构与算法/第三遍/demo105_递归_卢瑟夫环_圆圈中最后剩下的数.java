package 数据结构与算法.第三遍;

/**
 * 使用递归
 * 返回的是最终脱颖而出的孩子的下标
 *
 * @author zoutongkun
 */
public class demo105_递归_卢瑟夫环_圆圈中最后剩下的数 {
    //写法1：推荐
    public int LastRemaining_Solution01(int n, int m) {//n为人数，m为随机指定的一个数
        if (n == 0)     /* 特殊输入的处理 */ {
            return -1;
        }
        if (n == 1)     /* 递归返回的条件 */ {
            return 0;
        }
        return (LastRemaining_Solution01(n - 1, m) + m) % n;
    }


    //自写一遍
    //n为人数，m为随机指定的一个数
    public int LastRemaining_Solution001(int n, int m) {
        //此时返回空
        if (n == 0) {
            return -1;
        }
        //此时就返回他本人
        if (n == 1) {
            return 0;
        }
        //一般情况，递归
        return (LastRemaining_Solution001(n - 1, m) + m) % n;
    }

    //写法2：
    public int LastRemaining_Solution02(int n, int m) {//n为人数，m为随机指定的一个数
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
