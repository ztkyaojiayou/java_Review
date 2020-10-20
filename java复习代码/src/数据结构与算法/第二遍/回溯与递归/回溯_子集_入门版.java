package 数据结构与算法.第二遍.回溯与递归;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//入门版：元素不重复
public class 回溯_子集_入门版 {
   List<List<Integer>> res = new ArrayList<>();

   public List<List<Integer>> subSets(int[] nums){
       List<Integer> path = new ArrayList<>();
       backtrack(nums,path,0);
       return res;
   }

    private void backtrack(int[] nums, List<Integer> path, int start) {
        res.add(new ArrayList<>(path));
        if (path.size() == nums.length){
            return;
        }
        for (int i = start;i<nums.length;i++){
            path.add(nums[i]);
            backtrack(nums,path,i+1);
            path.remove(path.size()-1);
        }
    }

}

class 子集_进阶版{
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subSets(int[] nums){
        //先排序
        Arrays.sort(nums);
        List<Integer> path = new ArrayList<>();
        backtrack(nums,path,0);
        return res;
    }

    private void backtrack(int[] nums, List<Integer> path, int start) {
        res.add(new ArrayList<>(path));
        if (path.size() == nums.length){
            return;
        }
        for (int i = start;i<nums.length;i++){
            //去重，同一层只保留第一一个相同的元素，后面相同的元素全部剪枝上
            if (i>start && nums[i] == nums[i-1]){
                continue;
            }
            path.add(nums[i]);
            backtrack(nums,path,i+1);
            path.remove(path.size()-1);
        }
    }
}
