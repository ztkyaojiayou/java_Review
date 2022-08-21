package 数据结构与算法.od机考.华为od机考练习.常规题库;

/**
 * 93）数组分组--类似于力扣上的分割等和子集，原型其实就是背包问题。
 *
 * @author :zoutongkun
 * @date :2022/7/26 6:41 下午
 * @description :
 * @modyified By:
 * <p>
 * 思路：
 * 先把三和五的倍数都挑出来，算好两边的和sum3和sum5，所有数总和为sum，不是3或5倍数的剩余的数放在集合中。
 * 求出target = sum/2-sum3或者target=sum/2-sum5作为目标数，看list中找能不能凑出target。
 * 在剩余集合中找target是一个dfs过程
 * <p>
 * 终止条件，用完集合数，判断target是否为0
 * <p>
 * 两种递归情况
 * --1）选择start位置，递归找新目标数target-list.get(start)
 * <p>
 * --2）不选择start位置，在start+1和以后位置找target
 * 特例：sum不是2的倍数，不可等分成两份，直接输出false
 */

/**
 * 思路：
 * 先把三和五的倍数都挑出来，算好两边的和sum3和sum5，所有数总和为sum，不是3或5倍数的剩余的数放在集合中。
 * 求出target = sum/2-sum3或者target=sum/2-sum5作为目标数，看list中找能不能凑出target。
 * 在剩余集合中找target是一个dfs过程
 *
 * 终止条件，用完集合数，判断target是否为0
 *
 * 两种递归情况
 * --1）选择start位置，递归找新目标数target-list.get(start)
 *
 * --2）不选择start位置，在start+1和以后位置找target
 * 特例：sum不是2的倍数，不可等分成两份，直接输出false
 */

import java.util.*;

public class Main93数组分组 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            //根据输入计算sum3，sum5和所有数总和sum，同时把不是5和3倍数的剩余数放入集合
            List<Integer> list = new LinkedList<>();
            int n = in.nextInt();
            int sum5 = 0, sum3 = 0, sum = 0;
            for (int i = 0; i < n; i++) {
                int cur = in.nextInt();
                if (cur % 5 == 0) {//5倍数和
                    sum5 += cur;
                } else if (cur % 3 == 0) {//3倍数和
                    sum3 += cur;
                } else {//剩余加入集合
                    list.add(cur);
                }
                sum += cur;//总和
            }

            //特例，总和不是2的倍数，不可分2份和相等的数字，直接false
            if (sum % 2 != 0) {
                System.out.println("false");
            } else {//否则，在剩余数中找目标数字，看能否凑出和为target的数字集合
                //注意：也可以是sum/2-sum5
                int target = sum / 2 - sum3;
                System.out.println(helper(list, target, 0));
            }
        }
    }

    private static boolean helper(List<Integer> list, int target, int start) {
        //递归终止条件：用完集合数，判断target是否为0
        if (start == list.size()) {
            return target == 0;
        }
        //递归
        //两种情况：
        //1）选择start位置，递归找新目标数target-list.get(start)
        //2）或者不选择start位置，在后面位置找target
        //只要找得到就行，因此是或的关系
        return helper(list, target - list.get(start), start + 1) || helper(list, target, start + 1);
    }
}
