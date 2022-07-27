package 华为od机考练习.常规题库;

import java.util.HashMap;
import java.util.Map;

/**
 * 08）合并表记录
 * @author :zoutongkun
 * @date :2022/7/27 12:13 下午
 * @description :
 * @modyified By:
 */
import java.util.*;

public class Main08 {
    public static void main(String agv[]) {
        Scanner sc = new Scanner(System.in);
        int tableSize = sc.nextInt();
        Map<Integer, Integer> map = new HashMap<>(tableSize);
        for (int i = 0; i < tableSize; i++) {
            int key = sc.nextInt();
            int value = sc.nextInt();
            //若存在，则合并value即可
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + value);
            } else {
                map.put(key, value);
            }
        }
        //遍历输出key和value-使用println换行输出
        for (Integer key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }
//        //或使用entry遍历
//        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            System.out.println(entry.getKey()+" "+entry.getValue());
//        }
    }
}
