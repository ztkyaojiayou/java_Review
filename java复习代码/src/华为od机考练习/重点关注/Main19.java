package 华为od机考练习.重点关注;

/**
 * @author :zoutongkun
 * @date :2022/7/21 1:58 下午
 * @description :
 * @modyified By:
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 19)简单错误记录
 * 描述：
 * 开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。
 *
 * 关于LinkedHashMap，可参考如下链接：
 * https://blog.csdn.net/fenglolo/article/details/112614088
 * 即：可以实现取出顺序和插入顺序的一致性
 */
public class Main19 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        //因为要考虑顺序，因此使用LinkedHashMap，而非普通的hashMap
        Map<String, Integer> map = new LinkedHashMap<>();
        //1）这里使用hasNext来录入测试用例
        //即：一个字符一个字符录入
        while (sc.hasNext()) {
            //2）既然是一个字符一个字符录入，那么这里就需要使用两个函数来接收啦，而不在使用行的概念了
            //即：使用next方法录入第一个字符串（也是字符），
            //使用nextInt来接收第二个数字（也是字符）
            String str = sc.next();
            int lineNum = sc.nextInt();
//            //3）注意，其实还是可以使用获取行的方式，亲测可用，也即获取方式和判断方式没有必然联系
//            String[] s = sc.nextLine().split(" ");
//            String str = s[0];
//            int lineNum = Integer.parseInt(s[1]);
            //根据\分割，获取到题目要求的文件名
            //正则表达式中\需要使用\\\\（即四道杠）才能达到效果
            String[] filePathArr = str.split("\\\\");
            String fileName = filePathArr[filePathArr.length - 1];
            //只保留最后16位
            if (fileName.length() > 16) {
                fileName = fileName.substring(fileName.length() - 16);
            }
            //构造key
            String key = fileName + " " + lineNum;
            //构造value放入到HashMap中
            map.put(key,map.getOrDefault(key,0)+1);
//            int defaultValue = 1;
//            if (map.containsKey(key)) {
//                //若有，则累加即可
//                map.put(key, map.get(key) + 1);
//            } else {
//                //否则，置为默认值1
//                map.put(key, defaultValue);
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


