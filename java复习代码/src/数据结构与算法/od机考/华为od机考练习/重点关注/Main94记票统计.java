package 数据结构与算法.od机考.华为od机考练习.重点关注;

/**
 * 94)记票统计
 * @author :zoutongkun
 * @date :2022/7/22 10:02 下午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main94记票统计 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //务必注意：这里是一个测试用例里就有4行，我们只需按照顺序录入即可
        while (sc.hasNext()) {
            //使用hasMap存储候选人及其所得票数
            //但要注意到题目的输出规律，
            //应该是需要按照输入顺序输出，易知就不能用普通的hashmap了，
            //而应该要选择linkedHashmap，保证输入输出的顺序性！！！
            Map<String, Integer> map = new LinkedHashMap<>();
            //记录无效票数
            int invalid = 0;
            //录入第一个int型--候选人数
            int n = sc.nextInt();
            //初始化map
            for (int i = 0; i < n; i++) {
                map.put(sc.next(), 0);
            }
            //投票人数
            int m = sc.nextInt();
            for (int i = 0; i < m; i++) {
                //录入每个投票人的投票结果
                String key = sc.next();
                //判断是否为合法投票，若是，则对应的候选人的票数加1
                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                } else {
                    //否则算作是非法投票
                    invalid++;
                }
            }
            //输出结果
            //1.候选人及选票数
            for (String key : map.keySet()) {
                System.out.println(key + " : " + map.get(key));
            }
            //2.非法投票数
            System.out.println("Invalid" + " : " + invalid);
        }
        sc.close();
    }
}

