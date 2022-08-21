package 数据结构与算法.od机考.华为od机考练习.常规题库;

import java.util.Map;

/**
 * 08）合并表记录
 * @author :zoutongkun
 * @date :2022/7/27 12:13 下午
 * @description :
 * @modyified By:
 */
import java.util.*;

/**
 * 8)合并表记录
 * 描述
 * 数据表记录包含表索引index和数值value（int范围的正整数），
 * 请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，
 * 输出按照index值升序进行输出。
 * <p>
 * 解析：使用TreeMap：因为它会按自然顺序排序
 */
class Main08合并表记录 {
    public static void main(String agv[]) {
        Scanner sc = new Scanner(System.in);
        int tableSize = sc.nextInt();
        //  Map<Integer, Integer> map = new HashMap<>(tableSize);
        //因为需要按照index排序，因此需要使用TreeMap（测试用例好像没有考虑这个，使用HashMap也能过，离谱）
        Map<Integer, Integer> map = new TreeMap<>();
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
