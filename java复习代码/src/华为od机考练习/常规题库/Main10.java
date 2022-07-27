package 华为od机考练习.常规题库;

/**
 *
 * @author :zoutongkun
 * @date :2022/7/23 5:01 下午
 * @description :
 * @modyified By:
 */

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 方法1：原始做法-推荐
 */
public class Main10 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
//        //（1）关于string与int的互转
//        int str1 = (int) str;//这肯定不行
//        int tarInt = Integer.parseInt(str);
//        int tarInt2 =Integer.valueOf(str).intValue();
//        int tarInt3 = Integer.valueOf(str);

//        //（2）Integer与String的互转
//        //1)Integer转String
//        Integer num = 12;
//        //转为str
//        String s = Integer.toString(num);
//        //转为为二进制str
//        String s1 = Integer.toBinaryString(num);
//        //转为十进制str
//        String s2 = Integer.toString(num, 10);
//        //2)String转Integer
//        Integer integer = Integer.valueOf(str);

        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            // （3）关于int与char类型间的相互转化
            // 1）int类型转char类型，将数字加一个‘0’，并强制类型转换为char即可。
            //    int number = 9;
            //    char cNumber= (char) (number+'0');
            // 2）char类型装int类型，将字符减一个‘0’即可。
            //    char cNumber2='3';
            //    int number2=cNumber2-'0';
            if ((str.charAt(i) - '0') > 127) {
                continue;
            }
            set.add(str.charAt(i));
        }
        System.out.println(set.size());
    }
}


/**
 * 自写一遍
 */
class Main100 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Set<Character> set = new HashSet<>();
        //遍历字符串，使用charAt--不一定非得先把字符串转为字符数组的!!!
        for (int i = 0;i<str.length();i++){
            if ((str.charAt(i) - '0')>127){
                continue;
            }
            set.add(str.charAt(i));
        }
        System.out.println(set.size());
    }
}

