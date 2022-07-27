package 华为od机考练习.重点关注;

/**
 * 33)整数与IP地址间的相互转换
 *
 * @author :zoutongkun
 * @date :2022/7/22 1:29 上午
 * @description :
 * @modyified By:
 */

import java.util.Scanner;

public class Main33_自写 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            if (str.contains(".")) {
                //1）即表示传入的是ip地址
                System.out.println(ip2NumMethod(str));
            }else {//不能没有else！！！
                //2)当为长整型时
                long num = Long.parseLong(str);
                System.out.println(num2IpMethod(num));
            }

        }
    }

    /**
     * 长整型转ip
     *
     * @param num
     * @return
     */
    private static String num2IpMethod(Long num) {
        //先转为二进制字符串--使用的是Long中的方法
        //Long中的toBinaryString方法需要传入long型
        String wholeStr2 = Long.toBinaryString(num);
        //理论上应该是32位，但可以没有，先补零
        while (wholeStr2.length() < 32) {
            wholeStr2 = "0" + wholeStr2;
        }
        //再转为ip--使用stringBuffer
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            //每八位截取--截取后为二进制字符串
            String subStr = wholeStr2.substring(8 * i, 8 * i + 8);
            //再需要转为十进制字符串--则需要先转为十进制的int型
            // 本质依然是由字符串转为int型，因此使用Integer的方法--parseInt
            //再由int转为String--也是使用Integer的方法--toString
            //易知，整体转换逻辑为：String-int-String，而这两步转换在Integer中的都有对应的方法
            String str10 = Integer.toString(Integer.parseInt(subStr, 2));
            if (i == 3) {
                sb.append(str10);
                //必须break呀，不然又会走到下面的语句了！！！
                break;
            }
            sb.append(str10).append(".");
        }
        return sb.toString();
    }


    /**
     * IP转数字
     *
     * @param ip
     * @return
     */
    private static Long ip2NumMethod(String ip) {
        //先变为字符串数组
        String[] strArr = ip.split("\\.");
        //再一个一个拼接
        StringBuilder sb = new StringBuilder();
        for (String s : strArr) {
            //Integer中的toBinaryString需要传入的是int型
            String str2 = Integer.toBinaryString(Integer.parseInt(s));
            //可能没8位，补零
            while (str2.length() < 8) {
                str2 = "0" + str2;
            }
            sb.append(str2);
        }
        //此时就是二进制字符串了，现在再转为10进制字符串
        return Long.parseLong(sb.toString(), 2);
    }
}

