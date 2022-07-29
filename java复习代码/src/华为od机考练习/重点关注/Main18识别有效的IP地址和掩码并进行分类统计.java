package 华为od机考练习.重点关注;

/**
 * 18）识别有效的IP地址和掩码并进行分类统计
 * 很简单，直接翻译题意即可：
 * 1.先判断是否为有效的ip地址和子网掩码（务必注意：子网掩码也属于ip，因此也需要符合ip规则！！！）
 * 2.若是，则再对该ip进行分类：从分类规则来看，只需才看了那个首位往后判断即可
 * 3.同时判断是否为私网ip，也是只需从首位往后判断即可
 * @author :zoutongkun
 * @date :2022/7/21 12:52 上午
 * @description :
 * @modyified By:
 */

import java.util.Scanner;

public class Main18识别有效的IP地址和掩码并进行分类统计 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //存储IP地址和子网掩码
        //ipAndMask[0]:即为ip地址，ipAndMask[1]:即为子网掩码
        String[] ipAndMask;
        //存储ip地址
        String[] ipArr;
        //初始化
        int A = 0,B = 0,C = 0,D =0,E = 0,errIpOrMask = 0,privateIp = 0;

        //该题是多行输入，但是是一次性就录入的，使用hasNextLine方法判断是否有下一行
        //也可以使用BufferedReader，即：
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String str = null;
//        while ((str = br.readLine()) != null) {
//        //具体代码
//        }
        //只有当还有下一行才处理，因此使用while
        //若有，则再处理，使用nextLine方法获取下一行的具体值即可。
        //这里相当于是一行一行录入，每录入一行
        while (in.hasNextLine()) {
            /**
             * 2）同理：\\~即表示将字符串以~切开，得到一个字符串数组，这里即为两个元素，也即ip地址和子网掩码
             */
            ipAndMask = in.nextLine().split("\\~");
            //不太明白
            if(ipAndMask[0].equals("end")){
                break;
            }
            /**
             * 1）关于正则表达式中的：\\.
             * 在 Java 中正则表达式中则需要有两个反斜杠才能被解析为其他语言中的转义作用。
             * 也可以简单的理解在 Java 的正则表达式中，两个 \\ 代表其他语言中的一个 \，
             * 这也就是为什么表示一位数字的正则表达式是 \\d，而表示一个普通的反斜杠是 \\\\。
             * 所以Java正则表达式中匹配一个普通的反斜杠是\\\\。
             */
            //即将ip地址按照.切开成一个字符串数组，
            //数组中的各元素即为组成这个ip的四个数字！！！
            //ipAndMask[0]即表示ip地址
            ipArr = ipAndMask[0].split("\\.");
            //类似于【0.*.*.*】和【127.*.*.*】忽略，这个条件要放在最前面，否则错误掩码会统计多

            /**
             * 1. Mask 255.255.255.255 , 0.0.0.0 为非法。
             * 2. IP和Mask必须同时正确，才能被分类到A, B, C, D, E以及私有。
             * 3. IP和Mask同时错误时，只算一次错误
             * 4. 注意0.*.*.*以及127.*.*.*不属于任何类别。
             */
            if(ipArr[0].equals("0") || ipArr[0].equals("127")){
                continue;
                //跳过
            }
            //ipAndMask[1]即表示子网掩码
            if(!isValidMask(ipAndMask[1])){ //判断掩码是否有效
                //无效时
                errIpOrMask++;
                //System.out.println(ipAndMask[1]);//输出错误掩码
            }else{
                //有效时，再判断ip是否有效
                if(!isValidIp(ipAndMask[0])){
                    //也无效
                    errIpOrMask++;
                    //System.out.println(ipAndMask[0]);//输出错误ip
                }else{
                    //也有效，则再判断ip为哪个父类
                    //A
                    if(Integer.parseInt(ipArr[0])>=1 && Integer.parseInt(ipArr[0])<=126){
                        if(Integer.parseInt(ipArr[0])==10){
                            privateIp++;
                            A++;
                        }else{
                            A++;
                        }
                    }
                    //B
                    if(Integer.parseInt(ipArr[0])>=128 && Integer.parseInt(ipArr[0])<=191){
                        if(Integer.parseInt(ipArr[0])==172 && (Integer.parseInt(ipArr[1]) >=16 && Integer.parseInt(ipArr[1])<=31)){
                            privateIp++;
                            B++;
                        }else{
                            B++;
                        }
                    }
                    //C
                    if(Integer.parseInt(ipArr[0])>=192 && Integer.parseInt(ipArr[0])<=223){
                        if(Integer.parseInt(ipArr[0])==192 && Integer.parseInt(ipArr[1]) ==168){
                            privateIp++;
                            C++;
                        }else{
                            C++;
                        }
                    }
                }
                //D
                if(Integer.parseInt(ipArr[0])>=224 && Integer.parseInt(ipArr[0])<=239){
                    D++;
                }
                //E
                if(Integer.parseInt(ipArr[0])>=240 && Integer.parseInt(ipArr[0])<=255){
                    E++;
                }
            }
        }
        //输出结果
        System.out.println(A + " " + B + " " + C + " " + D + " " + E + " " + errIpOrMask + " " + privateIp);
    }

    /**
     * 判断是否为合法子网掩码
     * 规则:
     * 1.说法1：子网掩码为二进制下前面是连续的1，然后全是0。
     * 例如：255.255.255.32就是一个非法的掩码
     * （注意二进制下全是1或者全是0均为非法子网掩码，这里强调全部）
     *
     * 2.说法2：子网掩码是一串与 IP 地址长度相同的 32 比特数字，
     * 即每一位数字都是对应8位的二进制（不足则补零）。
     * 子网掩码其左边一半都是 1，右边一半都是 0。
     * 子网掩码为 1 的部分表示网络号，子网掩码为 0 的部分表示主机号。
     */
    public static boolean isValidMask(String mask){
        //务必注意：子网掩码也属于ip，因此也需要符合ip规则
        if(!isValidIp(mask)){
            return false;
        }
        String[] maskTable = mask.split("\\.");
        //将子网掩码转为32位2进制字符串--使用StringBuilder拼接
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<maskTable.length;i++){
            //Integer类中有静态方法（均为原地转换）：
            //toBinaryString（int i）：返回int变量的二进制表示的字符串。
            //toHexString（int i）：返回int变量的16进制字符串。
            //toOctalString（int i）：返回int变量的8进制表示字符串。
            //十进制转为二进制--255对应11111111
            maskTable[i] = Integer.toBinaryString(Integer.parseInt(maskTable[i]));
            //不足8位在前面补齐0
            if(maskTable[i].length() < 8){
                for(int j=0;j < 8- maskTable[i].length();j++){
                    //补完零
                    sb.append("0");
                }
                //再添加转换的2进制串
                sb.append(maskTable[i]);
            }else{
                //刚好8位直接添加，因为之前已经判断过ip的有效性，所以不可能超过8位
                sb.append(maskTable[i]);
            }
        }
        //再判断是否为合法的子网掩码
        //最后一个1在第一个0之前，有效，否则无效
        //lastIndexOf--目标元素所在的最后一个位置
        //indexOf--目标元素所在的第一个位置
        return sb.toString().lastIndexOf("1") < sb.toString().indexOf("0");
    }


    /**
     * 判断是否为合法ip--先拆分
     * 两个要求：
     * 1）长度为4位
     * 2）每一个值都只能在[0-255]之间
     */
    public static boolean isValidIp(String ip){
        //1.先根据.切分
        String[] ipTable = ip.split("\\.");
        //2.长度为4位
        //先返回不符合条件的，也即尽量早return--这条原则具有通用性！！！
        if(ipTable.length != 4){
            return false;
        }
        //3.每一个值都只能在[0-255]之间
        for(String s : ipTable){
            if(Integer.parseInt(s) < 0 || Integer.parseInt(s) > 255){
                return false;
            }
        }
        return true;
    }
}
