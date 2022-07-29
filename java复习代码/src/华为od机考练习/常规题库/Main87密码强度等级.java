package 华为od机考练习.常规题库;

import java.util.Scanner;

/**
 * 87) 密码强度等级
 * 直译即可
 * @author :zoutongkun
 * @date :2022/7/26 5:04 下午
 * @description :
 * @modyified By:
 */
public class Main87密码强度等级 {
        /**
         *  score 分数
         *  upCount 大写字母数目
         *  lowCount 小写字母数目
         *  numCount 数字数目
         *  sigCount 符号数目
         */
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
//            while(in.hasNextLine()){
                char[] ch = in.nextLine().toCharArray();
                int score = 0;

                //统计长度
                int len = ch.length;
                if(len <= 4) score += 5;
                else if(len >4 && len < 8) score += 10;
                else score += 25;

                //遍历获取大小写字母、数字、符号数目
                int upCount = 0;
                int lowCount = 0;
                int numCount = 0;
                int sigCount = 0;

                for (int i = 0; i < len; i++) {
                    if(Character.isUpperCase(ch[i])) ++upCount;
                    else if(Character.isLowerCase(ch[i])) ++lowCount;
                    else if(Character.isDigit(ch[i])) ++numCount;
                    else ++sigCount;
                }

                //字母分数
                if((upCount > 0 && lowCount == 0) || (upCount == 0 && lowCount > 0)) score += 10;
                else if(upCount > 0 && lowCount > 0) score += 20;
                else score += 0;

                //数字分数
                if(numCount == 1) score += 10;
                else if(numCount > 1) score += 20;
                else score += 0;

                //符号分数
                if(sigCount == 1) score += 10;
                else if(sigCount > 1) score += 25;
                else score += 0;

                //奖励分数
                if (numCount > 0 && upCount > 0 && lowCount > 0 && sigCount > 0) score += 5;
                else if(numCount > 0 && sigCount > 0 &&(upCount >0 || lowCount >0)) score += 3;
                else if(numCount > 0 &&(upCount >0 || lowCount >0)) score += 2;

                //评分
                if(score >= 90) System.out.println("VERY_SECURE");
                else if(score >= 80) System.out.println("SECURE");
                else if(score >= 70) System.out.println("VERY_STRONG");
                else if(score >= 60) System.out.println("STRONG");
                else if(score >= 50) System.out.println("AVERAGE");
                else if(score >= 25) System.out.println("WEAK");
                else System.out.println("VERY_WEAK");
//            }
        }
    }
