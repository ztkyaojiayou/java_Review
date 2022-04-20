package 数据结构与算法.离职后刷题.第四遍必会版;

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

        //要注意的是，这个递归题是先得到递归结果，然后再处理其他逻辑
        temp *= temp;//再平方
        if (b % 2 == 1) {//若为奇数，则还要再乘以那个落单的（因为是递归，即是从后往前计算的）
            return temp * a;
        }
        return temp;
    }


    //自写一遍
    public double Power02(double a, int b) {
        int abs_b = Math.abs(b);
        double res = method02(a, abs_b);
        if (b < 0) {
            return 1 / res;
        }
        return res;
    }

    private double method02(double a, int abs_b) {
        if (a == 1) {
            return 1;
        }
        if (abs_b == 0) {
            return 1;
        }
        double temp = method02(a, abs_b / 2);
        temp *= temp;
        if (abs_b % 2 == 1) {
            return temp * a;
        }
        return temp;
    }
}
