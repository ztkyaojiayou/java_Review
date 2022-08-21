package 数据结构与算法.od机考.华为od机考练习.重点关注;

/**
 * @author :zoutongkun
 * @date :2022/7/22 10:02 下午
 * @description :
 * @modyified By:
 */

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * HJ94 记票统计
 */
public class Main94_自写 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //务必注意：这里是一个测试用例里就有4行，我们只需按照顺序录入即可
        while (sc.hasNext()) {
            //投票人  voter
            int candidateNums = sc.nextInt();
            //无效投票统计
            int inValidCnt = 0;
            Map<String, Integer> map = new LinkedHashMap<>();
            //初始化map
            for (int i = 0; i < candidateNums; i++) {
                //逐次录入第二行，也即各个候选人的名字
                String candidateName = sc.next();
                map.put(candidateName, 0);
            }
            //处理投票
            //先录入投票人总数
            int voterNums = sc.nextInt();
            //再录入投票结构并存入map中
            for (int i = 0; i < voterNums; i++) {
                String voteRes = sc.next();
                //只要不是单独投的目标候选人，则都算无效的投票
                if (map.containsKey(voteRes)) {
                    map.put(voteRes, map.get(voteRes) + 1);
                } else {
                    inValidCnt++;
                }
            }
            //最后，打印结果即可--遍历map的key即可
            for (String key : map.keySet()) {
                //由于都是换行输出，因此就使用println即可
                System.out.println(key + " : " + map.get(key));
            }
            //最后输出非法投票数即可
            System.out.println("Invalid:" + inValidCnt);
        }
    }
}

