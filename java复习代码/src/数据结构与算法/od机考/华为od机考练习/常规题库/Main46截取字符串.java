package 数据结构与算法.od机考.华为od机考练习.常规题库;

import java.util.Scanner;

/**
 * 46）截取字符串
 * @author :zoutongkun
 * @date :2022/7/25 2:27 下午
 * @description :
 * @modyified By:
 */
public class Main46截取字符串 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int k = Integer.parseInt(sc.nextLine());
        //其实可以不用判断，因为题目明确说了不会超过size
        if (k>str.length()){
            System.out.println(str);
        }else {
            System.out.println(str.substring(0,k));
        }
    }
}
