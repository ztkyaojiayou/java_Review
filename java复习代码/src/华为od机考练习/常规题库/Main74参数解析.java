package 华为od机考练习.常规题库;

/**
 * 74）参数解析
 *
 * @author :zoutongkun
 * @date :2022/7/26 1:59 下午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main74参数解析 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            StringBuilder sb = new StringBuilder();
            List<String> list = new ArrayList();
            //用于标记当前字符是否为引号--flag
            boolean hasQuotation = false;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '"') {
                    // 遇到引号就将flag置为true
                    // 具体：遇到第一个引号时flag为true，第二个引号时flag为false
                    hasQuotation = !hasQuotation;
                    //继续遍历下一个字符
                    continue;
                }

                //如果c是空格 ,且flag为false时,即没有引号 或已经是第二个引号结束
                if (c == ' ' && !hasQuotation) {
                    //往集合中添加当前拼接到的字符串
                    list.add(sb.toString());
                    //置空，重新遍历下一个字符
                    sb = new StringBuilder();
                } else {
                    //即 不是引号 也不是第二种逻辑，拼接即可--一般情况
                    sb.append(c);
                }
            }
            //最后遍历结束 没有空格或引号,需要再加上sb
            list.add(sb.toString());
            //打印出数组的长度,即几个命令
            System.out.println(list.size());
            //打印出具体的命令
            for (String s1 : list) {
                System.out.println(s1);
            }
        }
    }
}

