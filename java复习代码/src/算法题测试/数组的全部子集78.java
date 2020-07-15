package 算法题测试;
import java.util.ArrayList;
import java.util.List;
public class 数组的全部子集78 {
    List<List<Integer>> results = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        public List<List<Integer>> subsets(int[] nums) {
            backtrack(nums,0,list,results);
            return results;
        }

        void backtrack(int[] nums, int i, List<Integer> sub, List<List<Integer>> res) {
            for (int j = i; j < nums.length; j++) {
                if (j > i && nums[j] == nums[j - 1]) continue;
                sub.add(nums[j]);
                res.add(new ArrayList<Integer>(sub));
                backtrack(nums, j + 1, sub, res);
                sub.remove(sub.size() - 1);
            }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> results = new 数组的全部子集78().subsets(nums);
        System.out.println(results);
    }
}


