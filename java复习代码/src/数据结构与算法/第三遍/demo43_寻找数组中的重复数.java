package 数据结构与算法.第三遍;

import java.util.HashSet;

//当然可以使用hashMap或set，不过可能面试官不满意，
// 因此还可以使用一种更高阶的算法，即双指针,但感觉比较难懂，还是用普通解法吧hhh
public class demo43_寻找数组中的重复数 {
    //方法1：使用set,简单解法，掌握
    public int findDuplicate01(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return nums[i];
            }
            set.add(nums[i]);
        }
        return -1;

    }

    //方法2：双指针，高阶，尽量理解吧
    public int findDuplicate02(int[] nums) {
        int fast = nums[0];
        int slow = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
