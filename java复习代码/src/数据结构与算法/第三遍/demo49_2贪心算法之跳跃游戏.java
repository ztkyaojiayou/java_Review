package 数据结构与算法.第三遍;

public class demo49_2贪心算法之跳跃游戏 {
    //入门版：能否达到
    public boolean canJump01(int[] nums) {
        //最远可达的位置，默认第一个位置（下标从0开始）
        int max_position = 0;
        for (int i = 0; i < nums.length; i++) {
            //当前位置需可达，即当前位置小于可达的最远位置（该位置要实时维护）
            if (i <= max_position) {
                // 更新最远位置
                max_position = Math.max(max_position, i + nums[i]);
                //核心：若最远可达位置大于或等于了最后一个位置，说明就可达呀
                if (max_position >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }


    //自写一遍
    //入门版：能否达到
    public boolean canJump001(int[] nums) {
        int maxPosition = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= maxPosition) {
                maxPosition = Math.max(maxPosition, i + nums[i]);
                if (maxPosition >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }


    //进阶版：尽量使跳跃步数最少
    public int jump(int[] nums) {
        int max_position = 0;
        //每一次的最远位置的边界，达到该边界后再跳，很明显此时所用步数最小！
        int end_position = 0;
        int step = 0;
        //这里要注意一个细节，就是 for 循环中，i < nums.length - 1，少了末尾。
        // 因为开始的时候边界是第 0 个位置，steps 已经加 1 了。
        // 如果最后一步刚好跳到了末尾，此时 steps 其实不用加 1 了。如果是 i < nums.length，
        // i 遍历到最后的时候，会进入 if 语句中，steps 会多加 1。
        //链接：https://leetcode-cn.com/problems/jump-game-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-10/
        for (int i = 0; i < nums.length-1; i++) {
            //当前位置需可达，即当前位置小于可达的最远位置（该位置要实时维护）
            if (i <= max_position) {
                // 更新最远位置
                max_position = Math.max(max_position, i + nums[i]);
                //每次都一步跳到最远位置，很明显此时步数最小
                if (i == end_position) {
                    //当达到一个最原位置时，更新下一个最远位置，并更新步数，即直接跳到这个位置，一步即可
                    end_position = max_position;
                    step++;
                }
            }
        }
        return step;
    }


    //自写一遍
    //进阶版：尽量使跳跃步数最少
    public int jump02(int[] nums) {
        int maxPosition = 0;
        //每一步都要跨到边界去，就是这么牛逼！！！
        int endPosition = 0;
        int step = 0;
        //注意这里不用遍历到末尾元素
        for (int i = 0; i < nums.length-1; i++) {
            //当前位置要可达，也即只要在最远位置范围内即可，因为这说明总能达到
            if (i <= maxPosition) {
                //更新最远可达位置
                maxPosition = Math.max(maxPosition, i + nums[i]);
                //跨到了最远处，于是步数加1，并更新下一个最远处呀！！！
                if (i == endPosition) {
                    endPosition = maxPosition;
                    step++;
                }
            }
        }
        return step;
    }

}

