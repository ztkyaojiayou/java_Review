package 数据结构与算法.od机考.华为od机考练习.常规题库;

/**
 * 57）高精度整数相加--大数（字符串）相加
 * <p>
 * tips：
 * 1）for循环从右到左(低位到高位)相加。
 * 2）需要转为10进制进行相加，也就是char1 - '0' = 实际十进制数。
 *
 * @author :zoutongkun
 * @date :2022/7/25 3:15 下午
 * @description :
 * @modyified By:
 */

import java.util.Scanner;

public class Main57高精度整数相加 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String s1 = scan.next();
            String s2 = scan.next();
            String res = add(s1, s2);
            System.out.println(res);
        }
    }

    /**
     * //两个字符串整数相加（也即大数相加的方法--似乎常问）
     * @param s1
     * @param s2
     * @return
     */
    private static String add(String s1, String s2) {
        StringBuilder res = new StringBuilder();
        int tail1 = s1.length() - 1;
        int tail2 = s2.length() - 1;
        //进位：默认每一位相加都有进位，只是其值可能为0
        int carry = 0;
        //从两个人字符串最后一位开始相加
        while (tail1 >= 0 || tail2 >= 0) {
            //没有了就用0代替
            char c1 = tail1 >= 0 ? s1.charAt(tail1--) : '0';
            char c2 = tail2 >= 0 ? s2.charAt(tail2--) : '0';
            //开始计算这个对应位的值：两个数字与进位相加
            //注意：需要转为10进制进行相加，也就是char1 - '0' = 实际十进制数
            int sum = (c1 - '0') + (c2 - '0') + carry;
            //余数添加进结果--对计算结果取余即可--%
            res.append(sum % 10);
            //更新进位--对计算结果取商即可--/
            carry = sum / 10;
        }

        //计算到最后一位时若还有进位，则直接拼接到最后即可
        if (carry == 1) {
            res.append(carry);
        }
        //最后反转该字符串即为结果--因为我们是从头开始拼接的
        return res.reverse().toString();
    }
}

