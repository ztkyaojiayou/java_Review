package 华为od机考练习.常规题库;

/**
 * 31）单词倒排
 *
 * @author :zoutongkun
 * @date :2022/7/24 11:58 下午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main31 {
    public static void main(String[] args) {
        Scanner ac = new Scanner(System.in);
        //题目中的测试用例就一个，即是一个一个测试的，因此可以不用写while
        while (ac.hasNextLine()) {
            String str = ac.nextLine();
            // 匹配非字母的字符进行分割
            String[] strArr = str.split("[^A-Za-z]");
            StringBuilder res = new StringBuilder();
            // 逆序添加分割完的单词
            for (int i = strArr.length - 1; i >= 0; i--) {
                //易知，这里会使得最后一个字符串后还会加上一个空格，
                // 但无所谓，在最后多输出一个空格没什么的呀！
//                //若就是不想加这个空格，则也可以判断一下
//                if (res.toString().length() == strArr.length-1){
//                    res.append(strArr[i]);
//                }
                res.append(strArr[i]).append(" ");
            }

            System.out.println(res);
        }
    }
}

/**
 *
 */
class Main311 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strArr = sc.nextLine().split("[^a-zA-Z]");
        StringBuilder sb = new StringBuilder();
        //反着输出
        for (int i = strArr.length - 1; i >= 0; i--) {
            sb.append(strArr[i]).append(" ");
        }
        System.out.println(sb);
    }

}
