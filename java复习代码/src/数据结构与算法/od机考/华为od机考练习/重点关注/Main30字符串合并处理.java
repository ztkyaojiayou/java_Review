package 数据结构与算法.od机考.华为od机考练习.重点关注;

/**
 * 30)字符串合并处理
 * 描述
 * 按照指定规则对输入的字符串进行处理。
 * <p>
 * 详细描述：
 * <p>
 * 第一步：将输入的两个字符串str1和str2进行前后合并。
 * 如给定字符串 "dec" 和字符串 "fab" ， 合并后生成的字符串为 "decfab"
 * <p>
 * 第二步：对合并后的字符串进行排序，要求为：下标为奇数的字符和下标为偶数的字符分别从小到大排序。
 * 这里的下标的意思是字符在字符串中的位置。
 * 注意排序后在新串中仍需要保持原来的奇偶性。例如刚刚得到的字符串“decfab”，
 * 分别对下标为偶数的字符'd'、'c'、'a'和下标为奇数的字符'e'、'f'、'b'进行排序
 * （生成 'a'、'c'、'd' 和 'b' 、'e' 、'f'），再依次分别放回原串中的偶数位和奇数位，新字符串变为“abcedf”
 * <p>
 * 第三步：对排序后的字符串中的'0'~'9'、'A'~'F'和'a'~'f'字符，需要进行转换操作。
 * 转换规则如下：
 * 对以上需要进行转换的字符所代表的十六进制用二进制表示并倒序，然后再转换成对应的十六进制大写字符
 * （注：字符 a~f 的十六进制对应十进制的10~15，大写同理）。
 * 如字符 '4'，其二进制为 0100 ，则翻转后为 0010 ，也就是 2 。转换后的字符为 '2'。
 * 如字符 ‘7’，其二进制为 0111 ，则翻转后为 1110 ，对应的十进制是14，转换为十六进制的大写字母为 'E'。
 * 如字符 'C'，代表的十进制是 12 ，其二进制为 1100 ，则翻转后为 0011，也就是3。转换后的字符是 '3'。
 * 根据这个转换规则，由第二步生成的字符串 “abcedf” 转换后会生成字符串 "5D37BF"。
 *
 * @author :zoutongkun
 * @date :2022/7/24 4:23 下午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main30字符串合并处理 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.next();
            // 1.第一步：字符串合并
            str = str + sc.next();
            // 2.第二步：字符串奇偶排序--使用两个list分别存储奇数和偶数
            int length = str.length();
            List<Character> even = new ArrayList<>();
            List<Character> odd = new ArrayList<>();
            //2.1先分别存储
            for (int i = 0; i < length; i++) {
                if (i % 2 == 0) {
                    even.add(str.charAt(i));
                } else {
                    odd.add(str.charAt(i));
                }
            }
            //2.2再分别对奇数和偶数进行排序
            Collections.sort(even);
            Collections.sort(odd);
            //2.3再重新拼接成新排序后的字符串
            //2.3.1使用StringBuilder构造
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < even.size(); i++) {
                sb.append(even.get(i));
                //有奇数时才拼接
                if (i < odd.size()) {
                    sb.append(odd.get(i));
                }
            }
            //2.3.2再转为字符串
            String sortedStr = sb.toString();
            // 第三步：再转换字符--对排序后的字符串中的'0'~'9'、'A'~'F'和'a'~'f'字符，需要进行转换操作
            //具体要求为：对以上需要进行转换的字符所代表的十六进制用二进制表示并倒序，
            //然后再转换成对应的十六进制大写字符（注：字符 a~f 的十六进制对应十进制的10~15，大写同理，也即在16进制中，大小写不区分，因此可以统一按照大写处理）
            //3.1开始遍历每一个字符
            for (int i = 0; i < length; i++) {
                //注意：它是16进制（题目要求）
                char curChar16 = sortedStr.charAt(i);
                int intFromChar = 0;
                //3.1.1若为数字：即0-9
                if (Character.isDigit(curChar16)) {
                    //转为对应的数字
                    intFromChar = curChar16 - '0';
                    //3.1.2若为字母，且为'A'~'F'和'a'~'f'中的字符时，则也需要转换，而对于其他字母或字符则无需转换！！！
                    //根据题意，在16进制中，不区分大小写，因此可以统一按照大写处理
                } else if (Character.isLetter(curChar16) && (Character.toUpperCase(curChar16) <= 'F')) {
                    //转为对应的数字
                    intFromChar = Character.toUpperCase(curChar16) - 'A' + 10;
                } else {
                    //3.1.3其他字符则直接打印，无需再处理/转换
                    System.out.print(curChar16);
                }
                //转为二进制字符串-四位
                String binaryStr = Integer.toBinaryString(intFromChar);
                int initLength = binaryStr.length();
                //若没有四位，则补齐--在前面补零
                if (binaryStr.length() < 4) {
                    for (int j = 0; j < 4 - initLength; j++) {
                        binaryStr = 0 + binaryStr;
                    }
                }
                //翻转该二进制字符串
                String revertBinaryStr = new StringBuilder(binaryStr).reverse().toString();
                //再将二进制字符串转为10进制int型--使用Integer.parseInt(str, 2)
                //再将10进制int型转为16进制字符串--使用Integer.toHexString(int num)：将十进制数转化为16进制，并返回16进制String字符串。
                //Integer.valueOf一般会使用Integer.parseInt替代--将String型的字符串转为int，若非数字，则会转换异常。
                String hexStr = Integer.toHexString(Integer.parseInt(revertBinaryStr, 2)).toUpperCase(Locale.ROOT);
                //一个一个先打印--使用print而非println
                System.out.print(hexStr);
            }
            //最后换个行即可
            System.out.println();
        }
    }
}

