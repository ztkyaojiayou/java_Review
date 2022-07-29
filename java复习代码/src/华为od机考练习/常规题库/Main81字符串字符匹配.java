package 华为od机考练习.常规题库;

/**
 * 81）字符串字符匹配
 * 描述：判断短字符串S中的所有字符是否在长字符串T中全部出现。
 *
 * @author :zoutongkun
 * @date :2022/7/26 4:26 下午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main81字符串字符匹配 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //需要加个标志位，否则输出不好处理
        boolean flag = false;
//        while (in.hasNextLine()) {
        String shortStr = in.nextLine();
        String longStr = in.nextLine();
        //长字符串存set中
        Set<Character> set = new HashSet<>();
        for (char ch : longStr.toCharArray()) {
            set.add(ch);
        }
        //再遍历短字符串并判断即可
        for (char ch : shortStr.toCharArray()) {
            if (!set.contains(ch)) {
                flag = true;
                System.out.println(false);
                break;
            }
        }
        //只有当flag还为false时才表示全部匹配，才打印true
        if (!flag) {
            System.out.println(true);
        }

//        }
    }
}
