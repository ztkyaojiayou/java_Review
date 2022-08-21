package 数据结构与算法.od机考.华为od机考练习.重点关注;

/**
 * 30)字符串合并处理
 * 思路其实很简单，就是按照题意一步一步实现，感觉主要就是api要熟悉
 *
 * @author :zoutongkun
 * @date :2022/7/24 4:23 下午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main30_自写 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.next();
            /**
             *  1.第一步：字符串合并
             */
            str = str + sc.next();
            /**
             *  2.第二步：字符串奇偶排序--使用两个list分别存储奇数和偶数
             */
            int length = str.length();
            List<Character> evenList = new ArrayList<>();
            List<Character> oddList = new ArrayList<>();
            //2.1先分别存储
            for (int i = 0; i < length; i++) {
                if (i % 2 == 0) {
                    evenList.add(str.charAt(i));
                } else {
                    oddList.add(str.charAt(i));
                }
            }
            //2.2再分别对奇数和偶数进行排序
            Collections.sort(evenList);
            Collections.sort(oddList);
            //2.3再重新拼接成新排序后的字符串
            //2.3.1使用StringBuilder构造
            StringBuilder sb = new StringBuilder();
            //第一个位置的索引为0，因此是偶数list中的第一个元素
            for (int i = 0; i < evenList.size(); i++) {
                sb.append(evenList.get(i));
                //拼接一个偶数位置的元素时，同时拼接一个奇数位置的元素
                if (i < oddList.size()) {
                    sb.append(oddList.get(i));
                }
            }
            //2.3.2再转为字符串
            String sortedStr = sb.toString();
            /**
             *  3.第三步：再转换字符--对排序后的字符串中的'0'~'9'、'A'~'F'和'a'~'f'字符，需要进行转换操作
             *  具体要求为：对以上需要进行转换的字符所代表的十六进制用二进制表示并倒序，
             *  然后再转换成对应的十六进制大写字符（注：字符 a~f 的十六进制对应十进制的10~15，大写同理，也即在16进制中，大小写不区分，因此可以统一按照大写处理）
             */
            //3.1开始遍历每一个字符
            for (int i = 0; i < length; i++) {
                //注意：它是16进制（题目要求）
                char curChar16 = sortedStr.charAt(i);
                int intFromChar;
                //3.1.1若为数字：即0-9
                if (Character.isDigit(curChar16)) {
                    //转为对应的数字
                    intFromChar = curChar16 - '0';
                    //一个一个先打印--使用print而非println
                    System.out.print(curIntTo16Str(intFromChar));
                    //3.1.2若为字母，且为'A'~'F'和'a'~'f'中的字符时，则也需要转换，而对于其他字母或字符则无需转换！！！
                    //根据题意，在16进制中，不区分大小写，因此可以统一按照大写处理
                } else if (Character.isLetter(curChar16) && (Character.toUpperCase(curChar16) <= 'F')) {
                    //转为对应的数字
                    intFromChar = Character.toUpperCase(curChar16) - 'A' + 10;
                    //一个一个先打印--使用print而非println
                    System.out.print(curIntTo16Str(intFromChar));
                } else {
                    //3.1.3其他字符则直接打印，无需再处理/转换
                    System.out.print(curChar16);
                }
            }
            //最后换个行即可
            System.out.println();
        }
    }

    /**
     * 将当前要进一步处理的字符对应的数字转为16进制字符串
     *
     * @param intFromChar
     * @return
     */
    public static String curIntTo16Str(int intFromChar) {
        //转为二进制字符串-四位
        String binaryStr = Integer.toBinaryString(intFromChar);
        int initLength = binaryStr.length();
        //若没有四位，则补齐--在前面补零
        while (binaryStr.length() < 4) {
            binaryStr = 0 + binaryStr;
        }
        //翻转该二进制字符串
        String revertBinaryStr = new StringBuilder(binaryStr).reverse().toString();
        //再将二进制字符串转为10进制int型--使用Integer.parseInt(str, 2)
        //再将10进制int型转为16进制字符串--使用Integer.toHexString(int num)：将十进制数转化为16进制，并返回16进制String字符串。
        //Integer.valueOf一般会使用Integer.parseInt替代--将String型的字符串转为int，若非数字，则会转换异常。
        String hexStr = Integer.toHexString(Integer.parseInt(revertBinaryStr, 2)).toUpperCase(Locale.ROOT);
        return hexStr;
    }
}

