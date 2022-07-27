package 华为od机考练习.重点关注;

/**
 * @author :zoutongkun
 * @date :2022/7/21 1:58 下午
 * @description :
 * @modyified By:
 */

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 19)简单错误记录
 * 描述：
 * 开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。
 */
public class Main19_自写 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> map = new LinkedHashMap<>();
        //改动1：可以使用hasNextLine()方法的
        //即一行一行得录入，每录入一行就处理一行，
        // 务必明确：while中的代码都是对每一行进行处理，而不是针对所有行！！！
        while (sc.hasNextLine()) {
            //改动2：那么这里也就需要使用nextLine来获取这一行的内容啦！！！
            String[] s = sc.nextLine().split(" ");
            String str = s[0];
            int lineNum = Integer.parseInt(s[1]);
            //根据\分割，获取到题目要求的文件名
            String[] filePathArr = str.split("\\\\");
            String fileName = filePathArr[filePathArr.length - 1];
            //只保留最后16位
            if (fileName.length() > 16) {
                fileName = fileName.substring(fileName.length() - 16);
            }
            String key = fileName + " " + lineNum;
            //放入到HashMap中--一行搞定
            map.put(key, map.getOrDefault(key, 0) + 1);
//            int defaultVal = 1;
//            if (map.containsKey(key)) {
//                map.put(key, map.get(key) + 1);
//            } else {
//                map.put(key, defaultVal);
//            }
        }
        //只输出最后八个元素
        int count = 0;
        for (String key : map.keySet()) {
            count++;
            //输出最后八个记录
            if (count > (map.keySet().size() - 8)) {
                //println本身就是换行输出
                System.out.println(key + " " + map.get(key));
            }
        }
    }
}


