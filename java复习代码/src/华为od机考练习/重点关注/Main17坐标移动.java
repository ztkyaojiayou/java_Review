package 华为od机考练习.重点关注;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //若加while这一行则一次性可以输入多个测试案例，若不加，则一次性只能输入一个测试用例
//        while ((reader.readLine()) != null) {
        //readLine:读一行
            String[] in = reader.readLine().split(";");
            int x = 0;
            int y = 0;
            for (String str : in) {
                //为空时跳过
                if (str == null || str.trim().length() == 0) {
                    continue;
                }

                //获取后面的数字
                String lastStr = str.substring(1);
                if ("".equals(lastStr)) {
                    continue;
                }
                int val;
                try {
                    val = Integer.parseInt(lastStr);
                } catch (Exception e) {
                    continue;
                }

                //获取第一个字符并判断
                String firstChar = str.substring(0, 1);
                //过滤掉非目标字符
                if (!("A".equals(firstChar) || "D".equals(firstChar) || "W".equals(firstChar) || "S".equals(firstChar))) {
                    continue;
                }
                switch (firstChar) {
                    case "W":
                        y += val;
                        break;
                    case "S":
                        y -= val;
                        break;
                    case "A":
                        x -= val;
                        break;
                    case "D":
                        x += val;
                        break;
                    default:
                        break;
                }
            }
            System.out.println(x + "," + y);
//        }
    }

}
