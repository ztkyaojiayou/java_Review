package 数据结构与算法.od机考.华为od机考练习.常规题库;

import java.util.*;

/**
 * @author :zoutongkun
 * @date :2022/7/26 10:16 下午
 * @description :
 * @modyified By:
 */
public class Main102_自写 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Map<Character, Integer> map = new HashMap<>();
        for (int i =0;i<str.length();i++){
            char curChar = str.charAt(i);
            map.put(curChar,map.getOrDefault(curChar,0)+1);
        }

        //塞入list
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        //排序
        //当要自定义排序规则时，可以不用 Collections.sort()排序，就有list自己的排序方法即可！！！
        list.sort(((o1, o2) -> {
            //重写排序规则
            if (!o1.getValue().equals(o2.getValue())) {
                return o2.getValue() - (o1.getValue());
            } else {
                return o1.getKey() - o2.getKey();
            }
        }));
        //再输出即可
        for (Map.Entry<Character, Integer> entry : list) {
            System.out.print(entry.getKey());
        }
        System.out.println();
    }
}
