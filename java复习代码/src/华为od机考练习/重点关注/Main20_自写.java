package 华为od机考练习.重点关注;

import java.util.Scanner;

/**
 * @author :zoutongkun
 * @date :2022/7/21 10:37 下午
 * @description :
 * @modyified By:
 */
public class Main20_自写 {
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
            if(passWordsArr.length > 8){
                //一个一个字符判断
                for(char c : passWordsArr){
                    //数字
                    if(c >='0' && c <='9'){
                        digitalCnt = 1;
                    }
                    //大写字母
                    else if(c >='A' && c<='Z'){
                        uppLetterCnt = 1;
                    }else if(c >='a' && c<='z'){
                        lowLetterCnt = 1;
                    }else{
                        otherCharCnt = 1;
                    }
                    //只需要满足任意三个条件即可
                    if((digitalCnt + uppLetterCnt + lowLetterCnt + otherCharCnt)>=3){
                        break;
                    }
                }
                boolean isValid = true;
                //再判断是否包含长度大于2的重复子串
                if((digitalCnt + uppLetterCnt + lowLetterCnt + otherCharCnt)>=3){
                    //再遍历一次
                    for(int i= 0;i<passWords.length()-3;i++){
                        String s1 = passWords.substring(i,i+3);
                        String s2 = passWords.substring(i+3);
                        if(s2.contains(s1)){
                            isValid = false;
                            System.out.println("NG");
                            break;
                        }
                    }
                    if(isValid){
                        System.out.println("OK");
                        continue;
                    }

                } else {
                    System.out.println("NG");
                    continue;
                }

            }else{
                System.out.println("NG");
                continue;
            }

        }
    }

    }
