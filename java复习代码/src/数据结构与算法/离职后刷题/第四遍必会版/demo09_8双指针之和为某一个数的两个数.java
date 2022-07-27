package 数据结构与算法.离职后刷题.第四遍必会版;

import java.util.*;


//假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
public class demo09_8双指针之和为某一个数的两个数 {
    //方法1：双指针
    public List<Integer> twoSum(int[] arr, int sum) {
        //先排序
        Arrays.sort(arr);
        //双指针
        int left = 0;
        int right = arr.length - 1;
        List<Integer> list;
        while (left < right) {
            int cur_sum = arr[left] + arr[right];
            if (cur_sum == sum) {
                list = Arrays.asList(arr[left], arr[right]);
//                //1.或者直接返回list（推荐）
//                return Arrays.asList(arr[left],arr[right]);
                  //2.构建list的常规方法
//                list.add(arr[left]);
//                list.add(arr[right]);
//                //3.若要有多组值，则需要返回一个list<list>型，此时可以这么写
//                //即用一个list存储每一组值，并将所有结果再存入一个list（参照“三数之和为0”那个题）
//                list.add(Arrays.asList(arr[left],arr[right]));
//                List<Integer> test = Arrays.asList(1, 2, 3);
                return list;
            } else if (cur_sum < sum) {
                left++;
            } else {
                right--;
            }
        }
        //否则，返回空list即可
        return new ArrayList<>();
    }

    //方法2：使用map（结果集是对应的下标）
    public int[] twoSum02(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //若找到，就通过一个数组返回这两个值
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            //把该值放入map中（不管有没有找到）
            //key:元素，value：对应的下标
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("没有找到");
    }

    //自写一遍
    public List<Integer> twoSum001(int[] arr, int target) {
        List<Integer> list = new ArrayList<>();
        //先排序，很重要
        Arrays.sort(arr);
        //双指针
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int sum01 = arr[left] + arr[right];
            if (sum01 == target) {
                list.add(arr[left]);
                list.add(arr[right]);
                return list;
            } else if (sum01 < target) {
                left++;
            } else {
                right--;
            }
        }
        //若没找到，则返回空数组
        return new ArrayList<>();
    }

    //使用map
    //这里返回其对应的下标，而不是元素本身
    public List<Integer> twoSum002(int[] arr, int target) {
        List<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(target - arr[i])) {
                list.add(i);
                list.add(map.get(target - arr[i]));
                return list;
            }
            map.put(arr[i], i);
        }
        //否则返回空list
        return new ArrayList<>();
    }
}
