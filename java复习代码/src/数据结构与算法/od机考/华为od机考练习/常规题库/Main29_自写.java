package 数据结构与算法.od机考.华为od机考练习.常规题库;

/**
 * 29）字符串加解密
 *
 * @author :zoutongkun
 * @date :2022/7/23 9:38 下午
 * @description :
 * @modyified By:
 */

import java.util.Scanner;

/**
 * 正常的解题思路，使用多分支判断将字符对应转换即可。
 * tips：大小写转换不用查ASCII码表，通过字符加减就能实现
 */
public class Main29_自写 {
    public static void main(String[] args) {
//        //关于拼接字符、字符串、数字的相关细节demo--非常清楚了
        //易知，只要不是字符之间的运算，在拼接时都是正常拼接，不需要考虑转换的问题
        StringBuilder sb = new StringBuilder();
        char c = '6';
        sb.append(c);//6
        sb.append(9);//9
        sb.append("9");//9
        sb.append('9');//9
        sb.append(9 - 1);//8
        sb.append("9-1");//9-1(即不会进行运算，就是一个字符串！！！）
        sb.append(c - 1);//53
        //这才是符合要求的，即拼接的是ASCII码为53对应的字符，即5
        sb.append((char) (c - 1));//5
        System.out.println(sb);//最终结果：69989-1535
        Scanner sc = new Scanner(System.in);
        //加密
        System.out.println(encodeMethod(sc.nextLine()));
        //解密
        System.out.println(decodeMethod(sc.nextLine()));
    }

    /**
     * 加密函数
     *
     * @param code
     * @return
     */
    private static String encodeMethod(String code) {
        int len = code.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char curChar = code.charAt(i);
            if (curChar >= 'a' && curChar < 'z') {
                //关于拼接字符和对应ASCII码中的细节问题剖析
//                char c = '6';
//                System.out.println(c);
//                StringBuilder sb = new StringBuilder();
//                sb.append(c);
//                //注意:这里的减法就不是6-1了，而是6对应的ASCII码之间的减法了，也即54-1 = 53，
//                //那么得到的结果也是ASCII码中的值，此时若是直接拼接的是则并不是对应的字符了，而是其ASCII码
//                //因此这里拼接的值是54-1 = 53，而并不是6-1 = 5！！！
//                sb.append(c-1);
//                //这才是符合要求的，即拼接的是ASCII码为53对应的字符，即5
//                sb.append((char) (c-1));
//                System.out.println(sb);
                sb.append((char) (curChar - 'a' + 'A' + 1));
            } else if (curChar == 'z') {
                sb.append("A");
            } else if (curChar >= 'A' && curChar < 'Z') {
                sb.append((char) (curChar - 'A' + 'a' + 1));
            } else if (curChar == 'Z') {
                sb.append("a");
            } else if (curChar >= '0' && curChar < '9') {
                //同理，curChar+1也是字符对应的ASCII码+1，也需要再转为对应的char型
                sb.append((char) (curChar + 1));
            } else if (curChar == '9') {
                sb.append('0');
                //以下两种形式也都行
//                sb.append("0");
//                sb.append(0);
            }
        }
        //转为字符串
        return sb.toString();
    }

    /**
     * 解密函数
     *
     * @param password
     * @return
     */
    private static String decodeMethod(String password) {
        int len = password.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char curChar = password.charAt(i);
            if (curChar > 'a' && curChar <= 'z') {
                sb.append((char) (curChar - 'a' + 'A' - 1));
            } else if (curChar == 'a') {
                sb.append("Z");
            } else if (curChar > 'A' && curChar <= 'Z') {
                sb.append((char) (curChar - 'A' + 'a' - 1));
            } else if (curChar == 'A') {
                sb.append("z");
            } else if (curChar > '0' && curChar <= '9') {
                //即便是数字0-9，也还需要转为对应的char型
                sb.append((char) (curChar - 1));
            } else if (curChar == '0') {
                sb.append(9);
            }
        }
        return sb.toString();
    }
}
