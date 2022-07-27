package 华为od机考练习.常规题库;

import java.util.Scanner;

/**
 * 22)汽水瓶
 *
 * @author :zoutongkun
 * @date :2022/7/27 1:43 下午
 * @description :
 * @modyified By:
 */
public class Main22 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            int bottle = sc.nextInt();
            //当输入为0时，则终止输入--break
            if (bottle == 0) {
                break;
            }
            //直接使用当只要有两个空瓶时，就可以借一个空瓶，兑到一瓶，喝完后刚好还给老板即可
            //相当于每两个空瓶就可以换一瓶水
            System.out.println(bottle / 2);
        }
    }
}
