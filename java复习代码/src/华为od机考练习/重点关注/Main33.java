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

public class Main33 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            //这里是按照单个字符串录入的，其实和使用nextLine没啥差别，不用太纠结
            String str = sc.next();
            //1）当录入的字符串中有冒号时，即为ip转整数
            if (str.contains(".")) {
                System.out.println(ip2num(str));
            } else {
                //2）否则为整数转ip
                System.out.println(num2ip(Long.parseLong(str)));
            }
        }
    }

    /**
     * ip转整数
     *
     * @param ip
     * @return
     */
    public static long ip2num(String ip) {
        //先切割成字符串数组
        String[] iip = ip.split("\\.");
        //用于构建转换后的二进制字符串
        StringBuilder sb = new StringBuilder();
        //就四位--那么当每一位都转化为8位二进制时，易知共有32位
        for (int i = 0; i < 4; i++) {
            //变成数字
            int num = Integer.parseInt(iip[i]);
            //转换为二进制字符串--8位
            String num2 = Integer.toBinaryString(num);
            //若小于8位，则补零
            while (num2.length() < 8) {
                num2 = "0" + num2;
            }
            //拼接--最终为32位的二进制
            sb.append(num2);
        }
        //由二进制转为对应的10进制整数--直接使用api
        //Long.parseLong(String s,int n)，s 这是一个包含long表示要解析的字符串,n 是进制数，
        // 它是将第一个参数用第二个参数进制来表示，如果不写第二个参数的话默认是十进制。
        return Long.parseLong(sb.toString(), 2);
    }

    /**
     * 整数转ip
     *
     * @param num
     * @return
     */
    public static String num2ip(long num) {
        //转换为2进制的字符串--而不是整数
        String wholeStr2 = Long.toBinaryString(num);
        //由上述可知，此时由于是相反的操作，则易知当转为二进制时应该有32位
        //于是，要是少于32位，则也需要在前面补零
        while (wholeStr2.length() < 32) {
            wholeStr2 = "0" + wholeStr2;
        }
        //用于存储转换后的ip地址
        String[] ans = new String[4];
        for (int i = 0; i < 4; i++) {
            //拆分
            String str2 = wholeStr2.substring(8 * i, 8 * i + 8);
            //转化为10进制的字符串--先将二进制的字符串转为对应的10进制整型，再转为字符串
            String str10 = Integer.toString(Integer.parseInt(str2, 2));
            ans[i] = str10;
        }
        //拼接--使用jion
        return String.join(".", ans);
    }
}

