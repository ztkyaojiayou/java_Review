package 华为od机考练习.常规题库;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 09）提取不重复的整数
 *
 * @author :zoutongkun
 * @date :2022/7/28 12:52 下午
 * @description :
 * @modyified By:
 */
public class Main09 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //虽然是数字，但咱们完全可以把它当字符串处理
        String str = sc.next();
        //先反转
        String reversedStr = new StringBuilder(str).reverse().toString();
        //比如case：12234234
        //去重--使用set，边add边拼接，只有能添加进去的，我们才拼接，两全其美，妙！
        // （这个思路之前使用过，务必掌握）
        Set<Character> set = new HashSet<>();
        //拼接
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < reversedStr.length(); i++) {
            char curChar = reversedStr.charAt(i);
            if (set.add(curChar)) {
                //直接拼接
                sb.append(curChar);
            }
        }
        //直接输出
        System.out.println(sb);

    }
}
