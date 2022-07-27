package 数据结构与算法.离职后刷题;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 * @author :zoutongkun
 * @date :2022/5/7 5:31 下午
 * @description :
 * @modyified By:
 */
public class Demo1 {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> method(int[] nums){
        List<Integer> list = new ArrayList<>();
        method01(nums,list,0);
        return res;
    }

    private void method01(int[] nums, List<Integer> list, int start) {
        if (list.size() == nums.length){
            res.add(list);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (i>1 && nums[i]== nums[i-1]){
                continue;
            }
            list.add(nums[i]);
            method01(nums,list,start+1);
            //list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> res = new Demo1().method(nums);
//        for (List<Integer> re : res) {
//            for (Integer integer : re) {
//                System.out.println(integer);
//            }
//        }
        System.out.println(res);
    }
}
