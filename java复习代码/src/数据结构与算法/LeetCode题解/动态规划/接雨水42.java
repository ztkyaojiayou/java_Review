package 数据结构与算法.LeetCode题解.动态规划;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，
 * 在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */

/**
 * 解题思路：有点类似于智力题，首先要明确，题给的数组的含义：即下标表示每根柱子的位置，值则表示其对应的高度，
 * 也因此，对于值为0的地方，还是要先把它看成该柱子的高度就为0，而不是一开始就想成该地方用于接雨水。
 * 其次，我们主要是要搞清楚什么时候可以接到雨水？
 * （1）根据常识，只有当前柱子的高度比起两侧柱子的高度低时，才可以接住水；
 * （2）而根据木桶效应，接的水的多少(最大值）则由那根矮柱子决定，具体关系为：（核心）
 *     每一个柱子的高度方向可以接的雨水的数量 = min(从当前柱子向左看的最高柱子高度, 从当前柱子向右看的最高柱子高度) - 当前柱子高度
 *
 * 因此我们可以使用动态规划求解，具体步骤为：
 * 1）先用两个数组left、right分别保存：从左往右遍历时下标i最高柱子高度，和从右往左遍历时下标i最高柱子高度。
 * 2）再遍历一遍每个位置，只有当height[i]的高度，比left[i]和right[i]都要小的时候才能接住雨水（否则总有一边会漏，接不到雨水）
 * 3）将所有可以接到雨水的柱子的数量加起来
 */
public class 接雨水42 {
        public int trap(int[] height) {
            int length = height.length;//数组长度，也就是柱子的数目
            int[] max_left = new int[length];//保存每一个下标位置处的柱子及其左边柱子的最大高度（从左往右遍历即可）
            int[] max_right = new int[length];//保存每一个下标位置处的柱子及其右边柱子的最大高度（从右往左遍历即可）
            int leftMax = 0;//左边柱子的最大高度
            int rightMax = 0;//右边柱子的最大高度
            int sum = 0;//总和（结果）

            //1.先分别计算left和right数组
            for (int i = 0; i < length; i++) {
                if (height[i] > leftMax) {//先正向遍历，若当前高度大于左边高度，
                    // 则更新左边最大高度为当前值，即保证了leftMax永远是当前柱子及其左边柱子的最大高度，
                    // 同时把该值存入left数组中，表示当前柱子及其左边柱子的最大高度
                    leftMax = height[i];
                }
                max_left[i] = leftMax;

                if (height[length-1-i] > rightMax) {//再反向遍历，即从最后一个位置开始，
                    // 同理，若当前高度大于右边高度，则更新右边最大高度为当前值，
                    // 同时把该值（从最后一个位置开始）存入right数组中
                    rightMax = height[length-1-i];
                }
                max_right[length-1-i] = rightMax;
            }

            //2.再开始遍历每一根柱子，累加雨水数量。
            for (int j = 0; j < length; j++) {
                //3.1只有当前柱子的高度比起两侧柱子的高度都低时，才可以接住水
                if (height[j] < max_left[j] && height[j] < max_right[j]) {
                    // 根据水桶效应：
                    //3.2每一个柱子的高度方向可以接的雨水的数量 = min(从当前柱子向左看的最高柱子高度, 从当前柱子向右看的最高柱子高度) - 当前柱子高度
                    sum = sum + Math.min(max_left[j], max_right[j]) - height[j];
                }
            }
            //3.最后返回结果即可
            return sum;
        }
    }

/**
 * 解法二：使用动态规划进行优化
 * 我们注意到，解法一中。对于每一列，我们求它左边最高的墙和右边最高的墙，都是重新遍历一遍所有高度，这里我们可以优化一下。
 * 首先用两个数组，max_left [i] 代表第 i 列左边最高的墙的高度，max_right[i] 代表第 i 列右边最高的墙的高度。
 * （一定要注意下，第 i 列左（右）边最高的墙，是不包括自身的，和 leetcode 上边的讲的有些不同）
 *
 * 对于 max_left我们其实可以这样求：
 * max_left [i] = Max(max_left [i-1],height[i-1])。它前边的墙的左边的最高高度和它前边的墙的高度选一个较大的，就是当前列左边最高的墙了。
 *
 * 同理，对于 max_right我们可以这样求：
 * max_right[i] = Max(max_right[i+1],height[i+1]) 。它后边的墙的右边的最高高度和它后边的墙的高度选一个较大的，就是当前列右边最高的墙了。
 *
 * 这样，我们再利用解法一的算法，就不用在 for 循环里每次重新遍历一次求 max_left 和 max_right 了。

 */
//优化版，使用动态规划
class 接雨水02{
    public int trap(int[] nums) {
        int sum = 0;
        int[] max_left = new int[nums.length];
        int[] max_right = new int[nums.length];
/**
 * max_left [i] 代表第 i 列左边最高的墙的高度，max_right[i] 代表第 i 列右边最高的墙的高度。
 * （一定要注意下，第 i 列左（右）边最高的墙，是不包括自身的，和 leetcode 上边的讲的有些不同）
 */
        for (int i = 1; i < nums.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], nums[i - 1]);
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], nums[i + 1]);
        }
        //再累加每一根柱子的雨水数量，记住，是一根柱子一根柱子来计算，而不是传统地使用长*宽来计算
        for (int i = 1; i < nums.length - 1; i++) {
            int min_hight = Math.min(max_left[i], max_right[i]);
            if (min_hight > nums[i]) {
                sum = sum + (min_hight - nums[i]);
            }
        }
        return sum;
    }
}


