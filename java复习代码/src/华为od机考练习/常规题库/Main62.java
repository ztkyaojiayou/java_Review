package 华为od机考练习.常规题库;

import java.util.Scanner;

/**
 * 62)查找输入整数二进制中1的个数--好像重复了
 * 方法：位运算
 *
 * @author :zoutongkun
 * @date :2022/7/27 10:06 下午
 * @description :
 * @modyified By:
 */
public class Main62 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int count = 0;
            while (n != 0) {
                count += n & 1;
                n >>= 1;
            }
            System.out.println(count);
        }
    }
}
