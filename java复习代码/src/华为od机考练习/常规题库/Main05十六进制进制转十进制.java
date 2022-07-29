package 华为od机考练习.常规题库;

/**
 * 5）十六进制进制转十进制
 *
 * @author :zoutongkun
 * @date :2022/7/23 5:01 下午
 * @description :
 * @modyified By:
 */

import java.util.Scanner;

/**
 * 方法1：原始做法-推荐
 */
class Main05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            //注意：传入的是一个16进制的字符串
            String str = sc.nextLine();
            //因为十六进制的格式为ox开始，但这两个字母对转换成十进制无意义，
            //因此只需截取后面的字符串即可
            String num = str.substring(2);
            int result = 0;
            //即表示16^n：即16的n次方，
            //初始化为1，即16的0次方，也即个位上要乘以的因子
            int power = 1;
            //从后面往前面处理
            for (int i = num.length() - 1; i >= 0; i--) {
                char c = num.charAt(i);
                //对于每个字符，都有两种可能：字母或数字
                //当为数字时
                if (c >= '0' && c <= '9') {
                    //注意：c - '0'返回的是个int型
                    result += (c - '0') * power;
                } else if (c >= 'A' && c <= 'F') {
                    //当为字母时（注意：千万不要和ASCII码中的大写字母搞混！！！）
                    result += (c - 'A' + 10) * power;
                }
                //乘数因子也要相应调整，即16的n次方
                power *= 16;
            }
            System.out.println(result);
        }
    }
}


/**
 * 自写一遍
 */
class Main051 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            String tarStr = str.substring(2);
            //转换因子
            int power = 1;
            //结果
            int res = 0;
            //从后往前处理，即先处理个位
            for (int i = tarStr.length() - 1; i >= 0; i--) {
                char curChar = tarStr.charAt(i);
                //可能有两种情况：数字或字母
                //数字
                if (curChar >= '0' && curChar <= '9') {
                    res += (curChar - '0') * power;
                } else if (curChar >= 'A' && curChar <= 'F') {
                    //字母
                    res += (curChar - 'A' + 10) * power;
                }
                //更新转换因子
                power *= 16;
            }
            System.out.println(res);
        }
    }
}

/**
 * 方法2：直接使用api，不推荐
 */
class Main005 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine().substring(2);
            System.out.println(Integer.parseInt(line, 16));
        }
    }
}
