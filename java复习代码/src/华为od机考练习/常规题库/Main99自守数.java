package 华为od机考练习.常规题库;

/**
 * 99)自守数
 * 描述：
 * 自守数是指一个数的平方的尾数等于该数自身的自然数。
 * 例如：25^2 = 625，76^2 = 5776，9376^2 = 87909376。
 * 请求出n(包括n)以内的自守数的个数
 * <p>
 * 解析：两种方法
 * 1）方法1：使用现有的api：String中的endsWith() 方法：
 * 该方法用于测试字符串是否以指定的后缀结束。如果参数表示的字符序列是此对象表示的字符序列的后缀，则返回 true；否则返回 false。
 * 注意，如果参数是空字符串，或者等于此 String 对象（用 equals(Object) 方法确定），则结果为 true。
 * <p>
 * 2）方法2：直接比较
 *
 * @author :zoutongkun
 * @date :2022/7/28 4:31 下午
 * @description :
 * @modyified By:
 * <p>
 * 方法1：
 * <p>
 * 方法1：
 */

/**
 *方法1：
 */

import java.util.*;

/**
 * @Description 自守数
 *
 * @Author haixiaofei
 * @Date 2022/2/23 9:20
 **/
class Main99 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            //用于统计
            int cnt = 0;
            //一个一个判断
            for (int i = 0; i <= n; i++) {
                //将int转为对应的字符串：String.valueOf()
                String str = String.valueOf(i * i);
                String s = String.valueOf(i);
                //直接endsWith方法进行使用判断
                if (str.endsWith(s)) {
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }
}

/**
 *方法2：直接判断
 */
class Main990 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = 0;
        int num = sc.nextInt();
        //遍历，对一个一个数字进行处理
        for (int i = 0; i <= num; i++) {
            //先得到目标值
            String str = String.valueOf(i * i);
            String s = String.valueOf(i);
            //判断：即判断后i位是否等于i即可
            if (s.equals(str.substring(str.length() - s.length()))) {
                cnt++;
            }
            System.out.println(cnt);
        }
    }
}

