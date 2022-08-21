package 数据结构与算法.od机考.华为od机考练习.常规题库;

/**
 * 41）称砝码--利用给定的砝码可以称出的不同的重量数
 * 描述
 * 现有n种砝码，重量互不相等，分别为 m1,m2,m3…mn ；
 * 每种砝码对应的数量为 x1,x2,x3...xn 。
 * 现在要用这些砝码去称物体的重量(放在同一侧)，问能称出多少种不同的重量。
 * <p>
 * 注：称重重量包括 0
 *
 * @author :zoutongkun
 * @date :2022/7/25 11:30 上午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main41称砝码 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) { // 注意 while 处理多个 case
            //存放所有可能的结果，不用担心重复问题
            Set<Integer> resSet = new HashSet<>();
            //初始化为0
            resSet.add(0);
            //个数
            int n = sc.nextInt();
            //存放砝码的重量
            int[] weights = new int[n];
            for (int i = 0; i < n; i++) {
                weights[i] = sc.nextInt();
            }
            //存放砝码个数(均至少有一个）
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            //开始处理
            //遍历砝码--选砝码
            for (int i = 0; i < n; i++) {
                //存放当前砝码所能称的重量
                Set<Integer> curSet = new HashSet<>();
                //遍历当前砝码的个数
                for (int j = 1; j <= nums[i]; j++) {
                    //当为j个时所能称的重量
                    int curWeight = weights[i] * j;
                    //加入到当前set
                    curSet.add(curWeight);
                    //之前hashSet中所有的情况都是没有包含该种砝码的，
                    //所以我们现在需要将之前的所有情况都加上包含该种砝码的情况
                    for (Integer oldNum : resSet) {
                        curSet.add(oldNum + curWeight);
                    }
                }
                //将选当前砝码能称的所有重量的情况汇总，同时会去重
                resSet.addAll(curSet);
            }
            //打印
            System.out.println(resSet.size());
        }
    }
}

