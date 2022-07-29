package 华为od机考练习.常规题库;

import java.util.Scanner;

/**
 * 71）字符串通配符
 * 描述：实现如下2个通配符：
 * *：匹配0个或以上的字符（注：能被*和?匹配的字符仅由英文字母和数字0到9组成，下同）
 * ？：匹配1个字符
 * <p>
 * 注意：匹配时不区分大小写。
 *
 * @author :zoutongkun
 * @date :2022/7/26 12:59 下午
 * @description :
 * @modyified By:
 */


/**
 * 思路：将输入的正则字符串替换为java的正则字符串
 */
public class Main71字符串通配符 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        while (sc.hasNextLine()) {
        //按照题意，忽略大小写--统一换成小写即可
        String regx = sc.nextLine().toLowerCase();
        String tarStr = sc.nextLine().toLowerCase();
        //做相应的替换--注意：需要转义字符
        //注意可能有多个*的情况，因此写成*{1,}，还可以简写为\*+
        //但不知为何，[0-9A-Za-z]换成\w却无法通过全部测试case，感觉是牛客编译器的问题
        regx = regx.replaceAll("\\*{1,}", "[0-9A-Za-z]*");
        regx = regx.replaceAll("\\?", "[0-9A-Za-z]{1}");
        //使用matches函数判断是否匹配
        System.out.println(tarStr.matches(regx));
//        }
    }
}

