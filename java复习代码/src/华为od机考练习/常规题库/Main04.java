package 华为od机考练习.常规题库;

import java.io.IOException;
import java.util.Scanner;

/**
 * 4）字符串分割
 *
 * @author :zoutongkun
 * @date :2022/7/23 4:39 下午
 * @description :
 * @modyified By:
 */

public class Main04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str;
        while (sc.hasNext()) {
            str = sc.nextLine();
            // 当大于8位时，就截取（同时要更新成截取后的字符串）并打印
            while (str.length() > 8) {
                //截取前八个字符打印
                System.out.println(str.substring(0, 8));
                //并更新成剩余没打印的字符串
                str = str.substring(8);
            }
            //若长度小于8时，则按最坏的结果算，即str为空时，往后补8个0
            str = str + "00000000";
            System.out.println(str.substring(0, 8));
        }
    }
}

/**
 * 第二遍
 */
class Main004 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (str.length() > 8) {
            System.out.println(str.substring(0, 8));
            str = str.substring(8);
        }
        //当小于8时，在后面补零--直接补8个0，
        str = str + "00000000";
        //再截取前八个字符打印，这样即便长度最后为0或8也都包括了
        System.out.println(str.substring(0, 8));
    }
}