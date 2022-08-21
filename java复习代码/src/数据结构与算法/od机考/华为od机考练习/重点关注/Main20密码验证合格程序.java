package 数据结构与算法.od机考.华为od机考练习.重点关注;

/**
 * 20)密码验证合格程序
 * 描述
 * 密码要求:
 * 1.长度超过8位
 * 2.包括大小写字母.数字.其它符号,以上四种至少三种
 * 3.不能有长度大于2的包含公共元素的子串重复 （注：其他符号不含空格或换行）
 *
 * @author :zoutongkun
 * @date :2022/7/21 9:08 下午
 * @description :
 * @modyified By:
 */

import java.util.*;


public class Main20密码验证合格程序 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            //获取
            String passWords = sc.nextLine();
            char[] passWordsArr = passWords.toCharArray();
//            //先定义各条件的boolean类型--这个方案不太好，因为只需要满足任意三个条件即可
//            boolean isDigital = false;
//            boolean isUppLetter = false;
//            boolean isLowLetter = false;
//            boolean isOtherChar = false;
            //改成数字型判断
            int digitalCnt = 0;
            int uppLetterCnt = 0;
            int lowLetterCnt = 0;
            int otherCharCnt = 0;
            //用于标记其是否包括大小写字母.数字.其它符号,以上四种至少三种
            boolean flag = false;
            //1）长度不能小于8位
            if (passWordsArr.length > 8) {
                //2）再一个一个字符判断
                //2.1判断其是否为数字、大小写字母和其他字母，更新对应的cnt值
                for (char curChar : passWordsArr) {
                    //数字
                    if (Character.isDigit(curChar)) {
                        digitalCnt = 1;
                    }
                    //大写字母
                    else if (Character.isUpperCase(curChar)) {
                        uppLetterCnt = 1;
                    } else if (Character.isLowerCase(curChar)) {
                        //小写字母
                        lowLetterCnt = 1;
                    }
                    //其他字符
                    else {
                        otherCharCnt = 1;
                    }
                    //2.1判断--只需要满足任意三个条件即可
                    if ((digitalCnt + uppLetterCnt + lowLetterCnt + otherCharCnt) >= 3) {
                        //将该标记位置为true
                        flag = true;
                        break;
                    }
                }
                boolean isValid = true;
                //再判断是否包含长度大于2的重复子串，则只需长度为3的字符串就行啦
                //（因为大于3的字符串重复的话，那么该字符串的前三个字符肯定也重复呀）
                if (flag) {
                    //再遍历一次，每次都取三个字符组成的字符串，再和剩下的字符串比较，判断是否包含即可
                    for (int i = 0; i < passWords.length() - 3; i++) {
                        String s1 = passWords.substring(i, i + 3);
                        String s2 = passWords.substring(i + 3);
                        //判断是否包含即可，若包含，则重复呀！
                        if (s2.contains(s1)) {
                            isValid = false;
                            System.out.println("NG");
                            //这里用break最好，即直接退出并处理下一个密码
                            break;
                        }
                    }
                    //判断
                    if (isValid) {
                        System.out.println("OK");
                        //这里使用break或continue都行，因为每次都是处理一个密码
                        //退出并处理下一个密码
                        break;
                    }

                } else {
                    System.out.println("NG");
                    //这里也使用break或continue都行
                    break;
                }

            } else {
                System.out.println("NG");
                //这里也使用break或continue都行
                break;
            }

        }
    }
}
