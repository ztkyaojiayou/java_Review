package 数据结构与算法.od机考.华为od机考练习.常规题库;

/**
 * 29）字符串加解密
 * 描述
 * 对输入的字符串进行加解密，并输出。
 * <p>
 * 加密方法为：
 * 当内容是英文字母时则用该英文字母的后一个字母替换，同时字母变换大小写,如字母a时则替换为B；字母Z时则替换为a；
 * 当内容是数字时则把该数字加1，如0替换1，1替换2，9替换0；
 * 其他字符不做变化。
 * <p>
 * 解密方法为加密的逆过程。
 * 数据范围：输入的两个字符串长度满足1≤n≤1000  ，
 * 保证输入的字符串都是只由大小写字母或者数字组成
 *
 * @author :zoutongkun
 * @date :2022/7/23 9:38 下午
 * @description :
 * @modyified By:
 */

import java.util.Scanner;

/**
 * 解题思路，使用多分支判断将字符对应转换即可。
 * tips：大小写转换不用查ASCII码表，通过字符加减就能实现
 */
public class Main29字符串加解密 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //同样可以不用while
//        while(in.hasNext()){
        System.out.println(encodeMethod(sc.nextLine()));
        System.out.println(decodeMethod(sc.nextLine()));
//        }
    }

    /**
     * 加密函数
     *
     * @param code
     * @return
     */
    private static String encodeMethod(String code) {
        char[] strArr = code.toCharArray();    //将String对象转换为字符数组
        for (int i = 0; i < strArr.length; i++) {
            //1.当为小写字母时，此时z单独处理
            if (strArr[i] >= 'a' && strArr[i] < 'z')
                //关于大小写转换：
                //大小写转换不用查ASCII码表，通过字符加减就能实现
                //相关规律：
                //1）小写字母中a-z的ASCII码范围97-122;
                //2）大写的字母A-Z的ASCII码范围65-90;
                //3）小写字母的ASCII码要大于大写字母的ASCII码，且对应的大小写字母相差32
                //4）这里的strArr[i] - 'a'求出的是当前字符与字符a的差值，
                // 那么用这个差值加上A就相当于是求该字符对应的大写字母的ASCII码值（int），
                // 再加一这就是它后一个大写字符的ASCII码值了，易知也是个int型，因此还需要转为char型
                // 那么咋转呢？可以直接转，直接加上(char)即可，就很方便！！！
                // 参考：https://blog.csdn.net/WXS153322/article/details/123898165
                strArr[i] = (char) (strArr[i] - 'a' + 'A' + 1);//转换，也可以使用StringBuilder拼接
            else if (strArr[i] == 'z')
                //单独处理即可
                strArr[i] = 'A';
                //2.当为大写字母时，，此时Z单独处理
            else if (strArr[i] >= 'A' && strArr[i] < 'Z')
                //同理
                strArr[i] = (char) (strArr[i] - 'A' + 'a' + 1);
            else if (strArr[i] == 'Z')
                //同理，单独处理
                strArr[i] = 'a';
            else if (strArr[i] >= '0' && strArr[i] < '9')
                //3.当为数字时，此时9单独处理
                strArr[i] = (char) (strArr[i] + 1);
            else if (strArr[i] == '9')
                //也单独处理即可
                strArr[i] = '0';
        }
        //4.最后转为字符串
        //String.valueOf()--字符串数组转成对应的字符串,常用
        return String.valueOf(strArr);
        // //也可以使用new String(strArr）
//        return new String(strArr);
    }

    /**
     * 解密函数：逆过程
     *
     * @param password
     * @return
     */
    private static String decodeMethod(String password) {
        char[] t = password.toCharArray();
        for (int i = 0; i < t.length; i++) {
            //1.当为小写字母时，此时a单独处理
            if (t[i] > 'a' && t[i] <= 'z')
                t[i] = (char) (t[i] - 'a' + 'A' - 1);
            else if (t[i] == 'a')
                t[i] = 'Z';
                //2.当为大写字母时，此时A单独处理
            else if (t[i] > 'A' && t[i] <= 'Z')
                t[i] = (char) (t[i] - 'A' + 'a' - 1);
            else if (t[i] == 'A')
                t[i] = 'z';
                //3.当为数字时，此时0单独处理
            else if (t[i] > '0' && t[i] <= '9')
                t[i] = (char) (t[i] - 1);
            else if (t[i] == '0')
                t[i] = '9';
        }
        //4.最后转为字符串
        return String.valueOf(t);
    }
}
