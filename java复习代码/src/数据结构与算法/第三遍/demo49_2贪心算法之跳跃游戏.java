package 数据结构与算法.第三遍;

public class demo49_2贪心算法之跳跃游戏 {
    //入门版：能否达到
    public boolean canJump01(int[] nums) {
        int max_position = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= max_position) {
                max_position = Math.max(max_position, i + nums[i]);//更新最远位置
                if (max_position >= nums.length - 1) {//核心
                    return true;
                }
            }
        }
        return false;
    }

    //进阶版：尽量使跳跃步数最少
    public int jump(int[] nums) {
        int max_position = 0;
        int end_position = 0;
        int step = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= max_position) {
                max_position = Math.max(max_position, i + nums[i]);
                if (i == end_position) {
                    end_position = max_position;
                    step++;
                }
            }
        }
        return step;
    }
}
