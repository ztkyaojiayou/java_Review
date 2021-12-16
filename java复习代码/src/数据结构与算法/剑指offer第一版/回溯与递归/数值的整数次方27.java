package 数据结构与算法.剑指offer第一版.回溯与递归;

/**
 * 题目描述
 * 给定一个double类型的浮点数base和int类型的整数exponent。
 * 求base的exponent次方。
 *
 * 保证base和exponent不同时为0
 */

/**
 * 方法2：递归法（快速幂）（推荐）
 *
 * 即假设我们要求x^8 ，如果我们知道x^4 ，那么x^8 = (x^4)^2 ，
 * 所以x^n = (x^（n/2））^2，即可以先求x的4次方，再将该结果平方即可。
 *
 * 但是还有个小问题，如果n是偶数，那么上述没问题。
 * 如果n是奇数，那么x^n = (x^（n/2））^2 * x ， 比如x^7 = (x^3）^2 * x
 * 即先求x的3次方，再将该结果平方，最后再乘以那个落单的x即可
 */
class 数值的整数次方27_2 {
        public double Power(double base, int exponent) {
            //0.特判
            if(base==0.0&&exponent<0)
                return 0.0;

            //1.先对指数取绝对值，求出指数为正数时的值
            int abs_exponent=Math.abs(exponent);
            double result=pow(base,abs_exponent);
            //1.1若指数小于0，则结果就为其倒数（数学关系）
            if(exponent<0)
                return 1/result;
            //1.2若指数就为正数，则直接返回该结果即可
            return result;
        }

        //（核心）求一个数的整数次方的具体实现（切不可调用库函数，否则该题就失去了意义）
        public double pow(double base,int abs_exponent){
            //1.1递归终止的条件
            if(abs_exponent==0)
                return 1.0;
            if(base==1.0)
                return base;

            //1.2开始递归
            //1.2.1即先求x的(n/2)次方，再将该结果平方
            //（注意：n/2 可以看成是n的一半，但在java中，它更应该看成是一个取商操作，比如，当n为奇数7时，那么n/2就等于3，而不是3.5）
            double result=pow(base,abs_exponent>>1);//右移相当于除以2，
            result*=result;//再将该结果平方

            //1.2.2若指数为奇数，则通过上面的代码也已经求出前（n-1）/2次方啦，此时只需要再乘以那个落单的x（即这里的base）既可
            if((abs_exponent&1)==1)//这是是否为奇数的另一种判断方式，即只需使其与1按位与既可
                return result*base;

            //1.3最后，返回结果即可
            return result;
        }
    }

/**
 * 方法1：暴力方法（不推荐）
 * 很显然就是n个b相乘。循环n次即可。
 */
class Solution27_1 {
    public double Power(double base, int exponent) {
        double result = 1.0;//结果变量
        int n = exponent;//用于记录原指数，便于后面返回结果时判断其正负（因为若其为负数，则后面要将其转为正数）
        if (exponent < 0) {//不管指数是正数还是负数，先统一计算其为正数时的结果
            exponent = - exponent;
        }
        else if(exponent == 0) {//特判
            return 1;
        }
        //开始暴力求解，即疯狂相乘
        for (int i = 1; i <= exponent; i++) {
            result *= base;
        }
        //最后，返回结果，但要分两种情况：指数为正数还是负数
        return n < 0 ? 1 / result : result;
    }
}
