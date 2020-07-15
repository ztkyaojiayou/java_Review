package 数据结构与算法.剑指offer题解.数组;

/**
 * (懂了）题目：输入一个整型数组，数组里有正数也有负数。数组中一个或连续的多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为 O(n)。
 *
 * 左神做法：
 cur 依次累加各个元素，一旦cur为负数时，则将cur清为零。
 并尝试更新一次result（最大值）
 最终返回result即为子数组的最大累加和

 解释：
 因为最大和的子数组：其任意数量的前缀一定不为负。
 也就是 cur如果没有累加出到负数，就继续往下走，否则cur置为0，重新开始累加（因为必须是连续的子序列，因此只好把前面的有小于0的序列舍弃掉了）。即模拟了“前缀不可能为负数”的情况。
 *
 */

public class array45连续子数组的最大和 {

    private static int FindGreatestSumOfSubArray(int[] array) {
        if (array==null || array.length<=0){
            return 0;
        }

        int cur = array[0];//表示当前的累加结果
        int res = cur;//表示最终要返回的最大值
        for (int i = 1; i < array.length; i++) {
            cur += array[i];//向后加，累加之后的结果重新赋给cur
            res = Math.max(res, cur);//对比累加了arr[i]之后的res值和没累加之前的res值，取其最大值作为最终的res

            //关键点：cur如果没有累加出到负数，就继续往下走，否则cur置为0（LeetCode的53题是置为当前遍历值，其实是一样的，
            //这里只是先置零后再加上当前的遍历值而已），重新从下一个数开始累加
            cur = (cur>0? cur:0);
        }
        return res;
    }
}
