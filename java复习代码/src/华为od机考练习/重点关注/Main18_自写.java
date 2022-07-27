package 华为od机考练习.重点关注;

/**
 * @author :zoutongkun
 * @date :2022/7/21 1:16 下午
 * @description :
 * @modyified By:
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.*;

public class Main18_自写 {
    public static void main(String[] args) throws IOException {
//初始化ip和子网掩码--使用字符串数组存
        String[] ipAndMaskArr;
        String[] ipArr;
        String[] maskArr;
        //再定义结果变量
        int A = 0,B = 0,C = 0,D = 0,E = 0,errorIpOrMask = 0,privateIp = 0;
        //输入
        Scanner in = new Scanner(System.in);
        //开始处理--因为是多行，因此一行一行处理，使用while即可
        while(in.hasNextLine()){
            //去掉~，获取到对应的ip和子网掩码
            ipAndMaskArr = in.nextLine().split("\\~");
            //同理，再获取对应的ip和子网掩码--去掉.
            ipArr = ipAndMaskArr[0].split("\\.");
            maskArr = ipAndMaskArr[1].split("\\.");
            //开始判断
            //1.先判断该ip是否为0或者127开头，若是，则直接跳过
            if("0".equals(ipArr[0]) || "127".equals(ipArr[0])){
                continue;
            }
            //2.再判断该行的子网掩码是否合法--单独抽出一个方法吧
            if(!isValidMask(maskArr)){
                errorIpOrMask++;
            }else{
                //否则，再判断ip是否合法
                if(!isValidIp(ipArr)){
                    //若也不合法
                    errorIpOrMask++;
                }else{
                    //则表示合法--此时再按ip分类--从高位往低位判断即可
                    //A
                    if(Integer.parseInt(ipArr[0]) >=1 && Integer.parseInt(ipArr[0]) <=126){
                        //再看是否为私网地址
                        if(Integer.parseInt(ipArr[0]) == 10){
                            A++;
                            privateIp++;
                        }else{
                            A++;
                        }
                    }

                    //B
                    if(Integer.parseInt(ipArr[0]) >=128 && Integer.parseInt(ipArr[0]) <= 191){
                        //再看是否为私网地址
                        if(Integer.parseInt(ipArr[0]) == 172 && (Integer.parseInt(ipArr[1]) >=16 && Integer.parseInt(ipArr[1]) <=31)){

                            B++;
                            privateIp++;
                        }else{
                            B++;
                        }
                    }

                    //C
                    if(Integer.parseInt(ipArr[0]) >=192 && Integer.parseInt(ipArr[0]) <= 223){
                        //再看是否为私网地址
                        if(Integer.parseInt(ipArr[0]) == 192 && Integer.parseInt(ipArr[0]) == 168){
                            C++;
                            privateIp++;
                        }else{
                            C++;
                        }
                    }

                    //D
                    if(Integer.parseInt(ipArr[0]) >=224 && Integer.parseInt(ipArr[0]) <= 239){
                        D++;
                    }

                    //E
                    if(Integer.parseInt(ipArr[0]) >=240 && Integer.parseInt(ipArr[0]) <= 255){
                        E++;
                    }

                }
            }

        }
        System.out.println(A + " " + B + " " + C + " " + D + " " + E + " " + errorIpOrMask + " " + privateIp);


    }


    //判断子网掩码是否合法--传入的已经是一个去掉了.的字符串数组了
    public static boolean isValidMask(String[] maskArr){
        //1.首先它也应当是一个ip地址--又再抽出一个方法专门用于判断是否为合法的ip地址
        if (!isValidIp(maskArr)){
            //若不是，直接返回false
            return false;
        }else{
            //否则，继续判断
            //先转为二进制--使用StringBuilder拼接
            StringBuffer sb = new StringBuffer();
            //一个一个字符转
            for(String s : maskArr){
                s = Integer.toBinaryString(Integer.parseInt(s));
                //可能没有8位，此时在前面补零即可
                if(s.length() < 8){
                    for(int j = 0;j<8-s.length();j++){
                        sb.append("0");
                    }
                    //再拼接原字符串
                    sb.append(s);
                }else{
                    //直接拼接
                    sb.append(s);
                }
            }
            //至此拼接完毕，只需判断该sb字符串是不是其左边一半都是 1，右边一半都是 0即可
            //使用lastIndexOf和indexOf判断即可--需要先将sb转为字符串
            String sbStr = sb.toString();
            return sbStr.lastIndexOf("1") < sbStr.indexOf("0");
        }
    }

    //判断是否为合法的ip地址
    public static boolean isValidIp(String[] ipArr){
//长度只能为4位
        if(ipArr.length != 4){
            return false;
        }
        for(int i = 0;i<ipArr.length;i++){
            //但凡有一个数不在0-255之间则为false
            if(Integer.parseInt(ipArr[i]) < 0 || Integer.parseInt(ipArr[i]) > 255){
                return false;
            }
        }
        return true;
    }
}

