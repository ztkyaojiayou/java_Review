package 华为od机考练习.常规题库;

/**
 * 41）称砝码--利用给定的砝码可以称出的不同的重量数
 *
 * @author :zoutongkun
 * @date :2022/7/25 11:30 上午
 * @description :
 * @modyified By:
 */

import java.util.*;

public class Main41 {
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
                    //当为j个时所能称的数量
                    int curNum = weights[i] * j;
                    //加入到当前set
                    curSet.add(curNum);
                    //之前hashSet中所有的情况都是没有包含该种砝码的，
                    //所以我们现在需要将之前的所有情况都加上包含该种砝码的情况
                    for (Integer oldNum : resSet) {
                        curSet.add(oldNum + curNum);
                    }
                }
                //将这次产生的新情况，与之前的情况进行汇总，去重
                resSet.addAll(curSet);
            }
            //打印
            System.out.println(resSet.size());
        }
    }
}

