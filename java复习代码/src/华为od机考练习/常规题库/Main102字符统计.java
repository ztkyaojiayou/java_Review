package 华为od机考练习.常规题库;

/**
 * 102）字符统计
 * 做法参考--借助HashMap或者TreeMap
 * Java中有很多集合，例如HashMap或者自带排序功能的TreeMap等，这里给出使用HashMap的方法。
 * 1）遍历字符串，将字符：字符出现次数存入HashMap中
 * 2）将HashMap放入ArrayList中， 并对其进行排序，重写排序规则。
 * 3）遍历ArrayList，打印输出
 *
 * @author :zoutongkun
 * @date :2022/7/26 9:10 下午
 * @description :
 * @modyified By:
 */

import java.io.IOException;
import java.util.*;

public class Main102字符统计 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char curChar = str.charAt(i);
            //统计字符出现次数
            map.put(curChar, map.getOrDefault(curChar, 0) + 1);
        }
        //把map放入list中，即list中存的就是一个一个的键值对
        //注意：传的并不是map本身，而是它的键值对，也即本质是个set集合！！！
        List<Map.Entry> list = new ArrayList<>(map.entrySet());
        //重写排序规则
        Collections.sort(list, (o1, o2) -> {
            //如果字符出现次数不同 按照出现次数从高到底--倒序
            if (o1.getValue() != o2.getValue()) {
//                Object val1 = o2.getValue();
//                Object val2 = o1.getValue();
                //这里强转是因为返回的是Object，与ASC码倒是无关，下同，但可以不转，亲测可用！！！
                return (int) (o2.getValue()) - (int) (o1.getValue());
            } else {//若次数相同则对比ASCII的大小 按照升序排列--升序
//                Object key1 = o1.getKey();
//                Object key2 = o2.getKey();
                return (char) (o1.getKey()) - (char) (o2.getKey());
            }
        });
        //最后打印输出
        for (Map.Entry entry : list) {
            System.out.print(entry.getKey());
        }
        //最后换个行吧（不换也可以--亲测）
        System.out.println();
    }
}


