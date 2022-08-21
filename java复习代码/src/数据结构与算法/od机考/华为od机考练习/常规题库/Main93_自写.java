package 数据结构与算法.od机考.华为od机考练习.常规题库;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 务必掌握
 *
 * @author :zoutongkun
 * @date :2022/7/26 7:01 下午
 * @description :
 * @modyified By:
 */


public class Main93_自写 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int sum5 = 0, sum3 = 0, sum = 0;
        List<Integer> listOtherNum = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            if (num % 3 == 0) {
                sum3 += num;
            } else if (num % 5 == 0) {
                sum5 += num;
            } else {
                listOtherNum.add(num);
            }
            sum += num;
        }
        if (sum % 2 != 0) {
            System.out.println(false);
        } else {
            int tarSum = sum / 2 - sum3;
            //走递归查找
            System.out.println(method(listOtherNum, 0, tarSum));
        }

    }

    /**
     * 递归查找方法
     * @param list
     * @param index
     * @param target
     * @return
     */
    public static boolean method(List<Integer> list, int index, int target) {
        if (index == list.size()){
            return target == 0;
        }
        return method(list,index+1,target-list.get(index)) || method(list,index+1,target);
    }
}
