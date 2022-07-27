package 华为od机考练习.重点关注;

/**
 * @author :zoutongkun
 * @date :2022/7/22 11:43 下午
 * @description :
 * @modyified By:
 */
import java.util.*;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main95 {
    public static String[] ten = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
    public static String[] power = {"万","亿"};
    public static String[] daiwei = {"元","角","分","整"};
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String[] s = in.nextLine().split("\\.");//分割为整数部分和小数部分
            if(s[1].equals("00")){
                System.out.println("人民币" + solveZheng(Double.parseDouble(s[0])) + "元整");
            }else if(s[0].equals("0")){
                System.out.println("人民币" + solveXiao(s[1]));
            }else{
                System.out.println("人民币" + solveZheng(Double.parseDouble(s[0])) + "元" + solveXiao(s[1]));
            }
        }
    }
    public static String solveXiao(String s2){
        StringBuilder sb = new StringBuilder();
        int jiao = Integer.parseInt(s2.substring(0,1));
        int fen = Integer.parseInt(s2.substring(1,2));
        if(jiao!=0){
            sb.append(ten[jiao]);
            sb.append("角");
        }
        if(fen!=0){
            sb.append(ten[fen]);
            sb.append("分");
        }
        return sb.toString();
    }
    public static String solveZheng(double zheng){
        StringBuilder sb = new StringBuilder();
        int pow = 0;
        while((int)zheng != 0){
            if(pow!=0){
                sb.append(power[pow-1]);
            }
            int temp = (int)(zheng % 10000);
            //个位
            int gewei = temp % 10;
            int shiwei = (temp / 10) % 10;
            int baiwei = (temp/100) % 10;
            int qianwei = (temp/1000) % 10;
            if(gewei!=0){
                sb.append(ten[gewei]);
            }
            //十位
            if(shiwei!=0){
                sb.append("拾");
                if(shiwei!=1){
                    sb.append(ten[shiwei]);
                }
            }else{
                if(gewei != 0 && (temp>99 || (int)zheng > 10000)){
                    sb.append(ten[0]);
                }
            }
            //百位
            if(baiwei!=0){
                sb.append("佰");
                sb.append(ten[baiwei]);
            }else{
                if(shiwei != 0 && (temp>999 || (int)zheng > 10000)){
                    sb.append(ten[0]);
                }
            }
            if(qianwei!=0){
                sb.append("仟");
                sb.append(ten[qianwei]);
            }else{
                if(baiwei != 0 && (int)zheng > 10000){
                    sb.append(ten[0]);//
                }
            }
            zheng /= 10000;
            pow++;
            if(pow>2){
                pow=1;
            }
        }
        return sb.reverse().toString();
    }
}

