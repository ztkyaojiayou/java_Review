package 数据结构与算法.od机考.华为od机考练习.常规题库;

import java.util.Arrays;

/**
 * 14）字符串排序
 * @author :zoutongkun
 * @date :2022/7/23 6:41 下午
 * @description :
 * @modyified By:
 */
import java.util.*;

/**
 * 140）字符串排序--字典序（最常见的排序规则）
 * 描述
 * 给定 n 个字符串，请对 n 个字符串按照字典序排列。
 */

/**
 * 思路：使用Arrays.sort(strArr)即可
 */
class Main14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        //将字符串存入字符串数组中，然后排序，再输出即可
        String[] strArr = new String[size];
        //存入字符串数组
        for (int i = 0; i < size; i++) {
            strArr[i] = sc.next();
        }
        //stream可以更方便写代码，一行即可搞定
        Arrays.stream(strArr).sorted().forEach(System.out::println);
//        //排序
//        Arrays.sort(strArr);
//        //再输出
//        for (String str : strArr) {
//            System.out.println(str);
//        }

    }
}

class Main140 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        String[] strArr = new String[num];
        for (int i = 0; i < num; i++) {
            strArr[i] = sc.next();
        }
        //再排序
        Arrays.sort(strArr);
        //再输出
        for (String str : strArr) {
            System.out.println(str);
        }
    }
}
