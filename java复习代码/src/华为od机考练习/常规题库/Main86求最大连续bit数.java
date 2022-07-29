package 华为od机考练习.常规题库;

/**
 * 86）求最大连续bit数--
 * 类比：15）求int型正整数在内存中存储时1的个数
 * <p>
 * java直接用位运算&，用当前数字和1做“&”操作，如果结果是1，
 * 说明此时的二进制第一位为1，然后右移一位，直至数字为0。
 * 注意java的>>是有符号右移，也就是说，负数用>>右移的话，会在左侧补1而不是0，
 * 这就会影响最终对1的计数。所以这里我们要使用无符号右移>>>。
 * 另外题目的意思是我们需要接收一个byte数字，然而我们提交时第一个没通过的测试用例为200。
 * 很显然，byte的取值范围为-128 ~ 127，题目有些莫名其妙，直接用int来接收是没有问题的。
 *
 * @author :zoutongkun
 * @date :2022/7/27 11:39 下午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main86求最大连续bit数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int num = sc.nextInt();
            int maxLen = 0;
            int cnt = 0;
            //移动32次之后，num就全为0啦
            //所以才有了这个while判断
            //其实就相当于for(int i =0;i<32;i++)
            while (num != 0) {
                //若num的二进制中的该位为1
                if ((num & 1) == 1) {
                    //则累计长度，
                    cnt++;
                    //同时更新最大长度
                    maxLen = Math.max(maxLen, cnt);
                } else {
                    //否则即该位为0，则将长度置为0，重新计算
                    cnt = 0;
                }
                //无符号右移，处理num二进制中的下一位
                num >>>= 1;
            }
            System.out.println(maxLen);
        }
    }
}
