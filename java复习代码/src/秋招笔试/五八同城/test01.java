package 秋招笔试.五八同城;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class test01 {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        Set<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        for (int i = 1; i <= len; i++) {
            if (!hashSet.contains(i))
                return i;
        }
        return len + 1;
    }

}
