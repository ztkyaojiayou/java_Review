package 华为od机考练习.常规题库;

import java.util.Scanner;

/**
 * 100)等差数列
 * 描述：
 * 等差数列 2，5，8，11，14。。。。
 * （从 2 开始的 3 为公差的等差数列）
 * 输出求等差数列前n项和
 * <p>
 * 解析：直接套公式即可
 *
 * @author :zoutongkun
 * @date :2022/7/28 7:08 下午
 * @description :
 * @modyified By:
 */
public class Main100等差数列 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
        // n：项数
        int length = sc.nextInt();
        // a1, an
        int first = 2, last = 3 * length - 1;
        System.out.println((first + last) * length / 2);
//        }
    }
}

