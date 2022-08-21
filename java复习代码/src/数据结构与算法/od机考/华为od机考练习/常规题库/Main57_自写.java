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

public class Main57_自写 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //没有必要使用while
        String s1 = scan.next();
        String s2 = scan.next();
        //从个位先计算
        int tail1 = s1.length() - 1;
        int tail2 = s2.length() - 1;
        //进位
        int carry = 0;
        //用于拼接结果
        StringBuilder sb = new StringBuilder();
        while (tail1 >= 0 || tail2 >= 0) {
            //拿到要计算的两个数
            //注意：取的是字符，而不是int
            char num1 = tail1 >= 0 ? s1.charAt(tail1) : '0';
            char num2 = tail2 >= 0 ? s2.charAt(tail2) : '0';
            //计算--记得转为对应的十进制数再计算
            int sum = (num1 - '0') + (num2 - '0') + carry;
            //拆分为个位和进位，个位可以直接拼接到结果字符串中
            int gewei = sum % 10;
            sb.append(gewei);
            //更新进位
            carry = sum / 10;
            //用于计算下一位
            tail1--;
            tail2--;
        }
        if (carry == 1) {
            sb.append(carry);
        }
        //最后，只需逆序打印即可
        System.out.println(sb.reverse());
    }
}


