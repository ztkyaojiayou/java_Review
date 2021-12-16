package 数据结构与算法.第三遍;

public class demo105_递归之数值的整数次方 {
    public double Power(double a, int b) {
        int abs_b = Math.abs(b);//先一律按正数处理
        double res = method(a, abs_b);
        if (b < 0) {//若指数小于0，则取倒数即可
            return 1 / res;
        }
        return res;
    }

    //具体的递归方法
    private double method(double a, int b) {
        //递归结束的条件
        if (b == 0) {
            return 1;
        }
        if (a == 1.0) {
            return a;
        }
        //递归
        double temp = method(a, b / 2);
        temp *= temp;//再平方
        if (b % 2 == 1) {//若为奇数，则还要再乘以那个落单的
            return temp = temp * a;
        }
        return temp;
    }
}
