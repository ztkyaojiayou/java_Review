package 华为od机考练习.常规题库;

/**
 * 96）表示数字
 * @author :zoutongkun
 * @date :2022/7/26 8:16 下午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main96 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < str.length(); ) {
                // 若发现了数字
                if (Character.isDigit(str.charAt(i))) {
                    //先拼接*
                    res.append('*');
                    //再把这个子数字字符串全部拼接
                    while (i < str.length() && Character.isDigit(str.charAt(i))) {
                        res.append(str.charAt(i));
                        i++;
                    }
                    //表示这个连续的数字结束了，因此在其后也拼接一个*
                    res.append('*');
                } else {
                    //若不是数字，则直接拼接上即可
                    res.append(str.charAt(i));
                    i++;
                }
            }
            System.out.println(res);
        }
    }
}