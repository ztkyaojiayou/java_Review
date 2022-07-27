package 华为od机考练习.常规题库;

import java.util.Scanner;

/**
 * 53）杨辉三角的变形
 * 纯找规律：只有n为1，2时，没有出现偶数，剩下的按照2 3 2 4的规律每四行循环一次。
 * 具体为：
 * n	（对4求余的结果）%4	res
 * 1、2          /           -1
 * 4、8、12……	0	         3
 * 5、9、13……	1或3	     2
 * 6、10、14 ……	2	         4
 *
 * @author :zoutongkun
 * @date :2022/7/27 5:04 下午
 * @description :
 * @modyified By:
 */
public class Main53 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        //对4取余，再分类输出
        int res = num % 4;
        if (num == 1 || num == 2) {
            System.out.println(-1);
        } else if (res == 1 || res == 3) {
            System.out.println(2);
        } else if (res == 2) {
            System.out.println(4);
        } else if (res == 0) {
            System.out.println(3);
        }
    }
}
