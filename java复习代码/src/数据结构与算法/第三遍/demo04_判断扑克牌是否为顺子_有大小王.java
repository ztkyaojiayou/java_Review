package 数据结构与算法.第三遍;

import java.util.Arrays;

public class demo04_判断扑克牌是否为顺子_有大小王 {
    public boolean isContinuous(int[] nums) {
        //先排序
        Arrays.sort(nums);
        int count = 0;
        //先统计大小王（0）的个数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count++;
            }
        }
        //从第一个不是0的位置开始遍历，查看是否有重复元素或者间断的元素是否可以用0补齐（关键）
        for (int i = count; i < nums.length-1; i++) {
            if (nums[i + 1] == nums[i]) {//要有重复元素，则肯定不是顺子
                return false;
            }
            //往不连续的位置插大小王，即0，最后看是否还剩余了0即可
            count -= nums[i + 1] - nums[i] - 1;
        }
        if (count >= 0) {
            return true;
        }
        return false;
    }
}
