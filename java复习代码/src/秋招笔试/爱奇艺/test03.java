package 秋招笔试.爱奇艺;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class test03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        String[] str = sc.nextLine().split(" ");
        for (int i = 0; i < str.length; i++) {
            list.add(Integer.valueOf(str[i]));
        }
        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }

        //处理
        List<List<Integer>> res = test03.threeSum(nums);
        for (int i = 0;i<res.size();i++){
            for (int j = 0; j < res.get(i).size(); j++) {
                System.out.print(res.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
        public static List<List<Integer>> threeSum(int[] nums){
            List<List<Integer>> res = new ArrayList<>();
            int len = nums.length;
            //先排序
            Arrays.sort(nums);
            for (int i = 0;i<len;i++){
                if (nums[i] > 0){
                    break;
                }
                //双指针
                int left = i+1;
                int right = len-1;
                while (left < right){//一直循环
                    int cur_sum = nums[i] + nums[left] + nums[right];
                    if (cur_sum == 0){
                        res.add(Arrays.asList(nums[i],nums[left],nums[right]));
                        //去重
                        while (left < right && nums[left] == nums[left+1]){
                            left++;
                        }
                        while (left < right && nums[right] == nums[right-1]){
                            right--;
                        }
                        //找到了第一组，还要继续探索是否有下一组
                        left++;
                        right--;
                    }else if (cur_sum < 0){
                        left++;
                    }else {
                        right--;
                    }
                }
            }
            //返回结果即可
            return res;
        }
}
