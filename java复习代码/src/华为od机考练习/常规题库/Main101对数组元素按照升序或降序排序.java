package 华为od机考练习.常规题库;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 101）输入整型数组和排序标识，对其元素按照升序或降序进行排序
 * 入门级
 *
 * @author :zoutongkun
 * @date :2022/7/28 2:49 下午
 * @description :
 * @modyified By:
 */

public class Main101对数组元素按照升序或降序排序 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            //接收数组长度
            int n = sc.nextInt();
            //创建数组
            int[] arr = new int[n];

            //数组填入
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            //接收排序标识
            int flag = sc.nextInt();
            //数组排序
            Arrays.sort(arr);

            //正序输出
            if (flag == 0) {
                for (int i = 0; i < arr.length; i++) {
                    System.out.print(arr[i] + " ");
                }
            } else {//逆序输出
                for (int i = arr.length - 1; i >= 0; i--) {
                    System.out.print(arr[i] + " ");
                }
            }
        }
    }
}

