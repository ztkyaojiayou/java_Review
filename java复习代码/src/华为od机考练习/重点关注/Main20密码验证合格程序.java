package 华为od机考练习.重点关注;

/**
 20)密码验证合格程序
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
            //长度不能小于8位
            if (passWordsArr.length > 8) {
                //一个一个字符判断
                for (char c : passWordsArr) {
                    //数字
                    if (c >= '0' && c <= '9') {
                        digitalCnt = 1;
                    }
                    //大写字母
                    else if (c >= 'A' && c <= 'Z') {
                        uppLetterCnt = 1;
                    } else if (c >= 'a' && c <= 'z') {
                        lowLetterCnt = 1;
                    } else {
                        otherCharCnt = 1;
                    }
                    //只需要满足任意三个条件即可
                    if ((digitalCnt + uppLetterCnt + lowLetterCnt + otherCharCnt) >= 3) {
                        break;
                    }
                }
                boolean isValid = true;
                //再判断是否包含长度大于2的重复子串
                if ((digitalCnt + uppLetterCnt + lowLetterCnt + otherCharCnt) >= 3) {
                    //再遍历一次
                    for (int i = 0; i < passWords.length() - 3; i++) {
                        String s1 = passWords.substring(i, i + 3);
                        String s2 = passWords.substring(i + 3);
                        if (s2.contains(s1)) {
                            isValid = false;
                            System.out.println("NG");
                            //这里用break最好，即直接退出并处理下一个密码
                            break;
                        }
                    }
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
