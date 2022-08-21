package 数据结构与算法.od机考.华为od机考练习.重点关注;

import java.io.IOException;
import java.util.Scanner;

/**
 * 17）坐标移动
 * 开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。
 * 从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。
 * 测试用例：A10;S20;W10;D30;X;A1A;B10A11;;A10;
 *
 * @author :zoutongkun
 * @date :2022/7/21 12:21 上午
 * @description :
 * @modyified By:
 */
public class Main17坐标移动 {
    public static void main(String[] args) throws IOException {
        //若加while这一行则一次性可以输入多个测试案例，若不加，则一次性只能输入一个测试用例
//        while ((reader.readLine()) != null) {
        Scanner sc = new Scanner(System.in);
        String[] strArr = sc.nextLine().split(";");
        int x = 0;
        int y = 0;
        for (String str : strArr) {
            //为空时跳过
            if (str == null || str.trim().length() == 0) {
                continue;
            }

            int num;
            try {
                //获取后面的数字--要移动的值）
                String numStr = str.substring(1);
                if ("".equals(numStr)) {
                    continue;
                }
                //字符串转对应的数字，若不是数字则会转换失败，很妙
                num = Integer.parseInt(numStr);
            } catch (Exception e) {
                continue;
            }

            //获取第一个字符（方位）并判断
            String positionChar = str.substring(0, 1);
            //过滤掉非目标字符
            if (!("A".equals(positionChar) || "D".equals(positionChar) || "W".equals(positionChar) || "S".equals(positionChar))) {
                continue;
            }
            switch (positionChar) {
                case "W":
                    y += num;
                    break;
                case "S":
                    y -= num;
                    break;
                case "A":
                    x -= num;
                    break;
                case "D":
                    x += num;
                    break;
                default:
                    break;
            }
        }
        System.out.println(x + "," + y);
//        }
    }

}
