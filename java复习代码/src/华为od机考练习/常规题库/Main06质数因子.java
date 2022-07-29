package 华为od机考练习.常规题库;

import java.util.Scanner;

/**
 * 06）质数因子
 * 1. 本题的思路：将这个整数num除以从2开始的质数(如果num%某个质因子==0，则将num除以该质因子直到不能除尽；
 * 2. 假设一个数为n，它的平方根为q。如果n能被一个大于q的数整除，记为m（m>q），
 * 那么n也一定能被n/m整除，而n/m小于q。所有遍历质因子的时候，只需寻找[2,q]范围内的质因子即可。
 * 3.如果除到仍然没有能整除的数，则证明num本身为质数，直接打印num即可
 *
 * @author :zoutongkun
 * @date :2022/7/27 10:54 上午
 * @description :
 * @modyified By:
 */
class Main06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long num = scanner.nextLong();
        //只需取到平方根即可--
        long k = (long) Math.sqrt(num);
        for (long i = 2; i <= k; ++i) {
            while (num % i == 0) {
                System.out.print(i + " ");
                num /= i;
            }
        }
        System.out.println(num == 1 ? "" : num);
    }
}

/**
 * 自写一遍
 */
class Main060 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long tarNum = sc.nextLong();
        //取平方根
        long sqrtNum = (long) Math.sqrt(tarNum);
        //从2开始找
        for (int i = 2; i <= sqrtNum; i++) {
            //若能被当前质数除尽，说明该质数就是它的质因子之一，打印
            //同时继续用该数除
            while (tarNum % i == 0) {
                //使用print打印，不换行
                System.out.print(i + " ");
                //取商，作为新的被除数
                tarNum = tarNum / i;
            }
        }
        //除到最后，若最终的商为1，则表示所有的商都不是质数，全部打印即可
        //而若不为1，则表示最终的商就是个质数，因此直接打印该商即可，因为不能再被其他质数整除啦！！！
        System.out.println(tarNum == 1 ? "" : tarNum);
//        if (tarNum == 1) {
//            System.out.println();
//        } else {
//            System.out.println(tarNum);
//        }
    }
}
