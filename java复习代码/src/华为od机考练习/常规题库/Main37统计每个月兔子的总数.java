package 华为od机考练习.常规题库;

/**
 * 37）统计每个月兔子的总数
 *
 * @author :zoutongkun
 * @date :2022/7/27 3:35 下午
 * @description :
 * @modyified By:
 */

import java.util.Scanner;

/**
 * 推荐方法：斐波那契数列
 * 我们可以先来推导一个
 * 第一个月 只有1对
 * 第二个月 只有1对
 * 第三个月 原先的一对生出一对 共2对
 * 第四个月 最开始的一对又生出一对 共3对
 * 第五个月 第一对生一对，第二队到第三月 生一对，共5对
 * 第六个月 第一对生一对，第二对生一对，第三对生一对，共8对
 * 可以发现f(n) = f(n-1)+f(n-2)，即演变成了最最熟悉的场景，递归即可
 */
public class Main37统计每个月兔子的总数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        System.out.println(method(num));
    }

    public static int method(int num) {
        if (num == 1 || num == 2) {
            return 1;
        }
        return method(num - 1) + method(num - 2);
    }
}
