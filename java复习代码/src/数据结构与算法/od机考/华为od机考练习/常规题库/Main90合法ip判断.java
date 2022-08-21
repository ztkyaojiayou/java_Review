package 数据结构与算法.od机考.华为od机考练习.常规题库;

/**
 * 90）合法ip判断
 * @author :zoutongkun
 * @date :2022/7/26 5:54 下午
 * @description :
 * @modyified By:
 */

/**
 * 解题思路：把所有可能出现的错误，通过 if-else 语句排除掉
 * 初始 字符串分割之后，一定要分成4部分（即长度为4），否则直接返回
 * 分割后的字符串不能为空，否则直接返回
 * 分割后的字符串不能包含 除数字以外的任何字符，否则直接返回
 * 在 IPv4 中，对于每一部分的数字，都不能大于255或者为负数
 * 别忘了，对于每一部分，不能有 前导0，即不能有 255.002.255.12 这种情况！！！
 */
import java.util.*;

public class Main90合法ip判断 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        //先全部输出no的情况
        String[] strArr = sc.nextLine().split("\\.");
        if (strArr.length != 4) {
            System.out.println("NO");
            return;
        }
        for (String str : strArr) {
            if ("".equals(str)) {
                System.out.println("NO");
                return;
            }
            //当前字符串对应的数字
            int curNum = 0;
            try {
                //直接转，此时，对于前导0，会被字段忽略，即字符串006转换完后是数字6，这一点很重要！！！
                //但由于可能是字符串，此时就会报异常，
                //而这种情况也是不符合ip地址规范的，
                //因此只需捕获该异常并处理即可--很妙！
                curNum = Integer.parseInt(str);
            } catch (Exception e) {
                System.out.println("NO");
                return;
            }
            if (curNum < 0 || curNum > 255) {
                System.out.println("NO");
                return;
            }
            //前导0的判断，切记！！！
            String s = String.valueOf(curNum);
            if (String.valueOf(curNum).length() != str.length()) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}

