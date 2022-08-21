package 数据结构与算法.od机考.华为od机考练习.常规题库;

/**
 * 60)查找组成一个偶数最接近的两个素数
 *
 * @author :zoutongkun
 * @date :2022/7/27 7:39 下午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main60查找组成一个偶数最接近的两个素数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int num = sc.nextInt();
            //如num=10, 遍历:5,6,7,8
            // 从最接近的两个中位数开始处理判断
            for (int i = num / 2; i < num - 1; i++) {
                //因为是从中间处理的，因此只要找到了和为num的两个素数，
                //那么他们的差值就是最小的啦，直接就可以break啦
                if (isPrime(i) && isPrime(num - i)) {
                    //换行输出
                    System.out.println(num - i);
                    System.out.println(i);
                    //或者也可以使用println+\n以换行来一次性输出
//                System.out.println((num - i) + "\n" + i);
                    break;
                }
            }

        }
    }

    // 判断是否素数
    public static boolean isPrime(int num) {
        //只需从2开始，判断到它的平方根即可
        for (int i = 2; i <= Math.sqrt(num); i++) {
            //能被其他数整除就说明不是素数，直接返回false
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

