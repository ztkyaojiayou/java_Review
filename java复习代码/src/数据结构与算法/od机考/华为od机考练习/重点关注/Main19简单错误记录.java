package 数据结构与算法.od机考.华为od机考练习.重点关注;

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

/**
 * 19)简单错误记录
 * 描述：
 * 开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。
 * 处理：
 * 1、 记录最多8条错误记录，循环记录，最后只用输出最后出现的八条错误记录。
 *     对相同的错误记录只记录一条，但是错误计数增加。最后一个斜杠后面的带后缀名的部分（保留最后16位）
 *     和行号完全匹配的记录才做算是“相同”的错误记录。
 * 2、 超过16个字符的文件名称，只记录文件的最后有效16个字符；
 * 3、 输入的文件可能带路径，记录文件名称不能带路径。
 *    也就是说，哪怕不同路径下的文件，如果它们的名字的后16个字符相同，也被视为相同的错误记录
 * 4、循环记录时，只以第一次出现的顺序为准，后面重复的不会更新它的出现时间，仍以第一次为准
 *
 * <p>
 * 关于LinkedHashMap，可参考如下链接：
 * https://blog.csdn.net/fenglolo/article/details/112614088
 * 即：可以实现取出顺序和插入顺序的一致性
 */
public class Main19简单错误记录 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        //因为要考虑顺序，因此使用LinkedHashMap，而非普通的hashMap
        //注意：这个顺序是指插入顺序和取出顺相同（就list的性质），
        //它并不会像TreeMap一样在内部进行自然排序！！！
        Map<String, Integer> map = new LinkedHashMap<>();
        //1）这里使用hasNextLine来录入测试用例
        //即：一行一行录入
        //注意：但使用while+hasNextLine时，若想终止输入，按回车键是无效的，需要按command+D才能结束！！！
        while (sc.hasNextLine()) {
            //2）既然是一个字符一个字符录入，那么这里就需要使用两个函数来接收啦，而不再使用行的概念了
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
            map.put(key, map.getOrDefault(key, 0) + 1);
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
        //遍历每一个元素，但是只有当为最后八个元素时，才输出，
        //通过使用一个count变量来计数，其实就相当于遍历时的索引，妙啊
        for (String key : map.keySet()) {
            //相当于遍历时的索引
            count++;
            //输出最后八个记录（且当size小于8时，则该式恒成立，直接全部输出，妙呀！）
            if (count > (map.keySet().size() - 8)) {
                //println本身就是换行输出
                System.out.println(key + " " + map.get(key));
            }
        }
    }
}


