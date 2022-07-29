package 华为od机考练习.常规题库;

/**
 * 84）统计大写字母个数
 *
 * @author :zoutongkun
 * @date :2022/7/26 4:38 下午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main84统计大写字母个数 {
    public static void main(String[] args) {
        Scanner sr = new Scanner(System.in);
//        //若有输入，则返回true
//        while (sr.hasNext()) {
        int count = 0;
        String str = sr.nextLine();
        for (int i = 0; i < str.length(); i++) {
            //判断是否为大写字母
            if (Character.isUpperCase(str.charAt(i))) {
                //也可以使用如下原始方法判断
                //str.charAt(i)>='A'&&str.charAt(i)<='Z'
                count++;
            }
        }
        System.out.println(count);
//        }
//        sr.close();//若无输入，则释放hasNext()分配的内存
    }
}
