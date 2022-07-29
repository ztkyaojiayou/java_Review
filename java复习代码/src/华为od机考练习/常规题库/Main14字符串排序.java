package 华为od机考练习.常规题库;

import com.sun.org.apache.bcel.internal.generic.NEW;

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
 * 140 字符串排序
 */
class Main14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //将字符串存入字符串数组中，然后排序，再输出即可
        String[] strArr = new String[n];
        //存入字符串数组
        for (int i = 0; i < n; i++) {
            strArr[i] = sc.next();
        }
        //排序
        Arrays.sort(strArr);
        //再输出
        for (String str : strArr) {
            System.out.println(str);
        }
    }
}

class Main140{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        String[] strArr = new String[num];
        for (int i = 0;i<num;i++){
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
