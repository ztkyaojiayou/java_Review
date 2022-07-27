package 数据结构与算法.离职后刷题.第四遍必会版;

import java.util.HashSet;

//当然可以使用hashMap或set，不过可能面试官不满意，
// 因此还可以使用一种更高阶的算法，即双指针,但感觉比较难懂，还是用普通解法吧hhh
public class demo14_6set或map之寻找数组中的重复数 {
    //方法2：双指针，高阶（掌握）
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        //寻找相遇点
        while (slow != fast) {
            //易知，一层嵌套就是走一步，两层就是走两步
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        //slow 从起点出发, fast 从相遇点出发, 一次走一步
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }


    //方法1：使用set,简单解法，掌握
    public int findDuplicate01(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])){
                return nums[i];
            }
            set.add(nums[i]);
        }
        return -1;

    }



}
