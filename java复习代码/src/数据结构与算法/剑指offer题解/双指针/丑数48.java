package 数据结构与算法.剑指offer题解.双指针;

/**
 * 题目描述
 * 把质因子只包含2、3和5的数称作丑数（Ugly Number）。
 * 例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。
 * 求按从小到大的顺序的第N个丑数。
 */

/**
 * 解题思路：三指针
 * 我们每次只新增一个最小值，然后用三个指针记录当前2，3，5质因子形成的最大丑数位置，
 * 这3者取最小就是下一位置上的丑数
 * 这样的话就会形成递增的丑数队列，而且遍历的次数也很容易就知道，即n-1,
 * 因为1是第一个丑数，n-1次遍历后，我们就可以得到一个含有n个丑数的有序数组，返回最后一个即可
 */
public class 丑数48 {
        public int GetUglyNumber_Solution(int index) {
            if (index <= 6)
                return index;

            int p2=0,p3=0,p5=0;//初始化三个指向三个潜在成为最小丑数的位置
            int[] result = new int[index];
            result[0] = 1;
            for(int i=1; i < index; i++){//每循环一次就找到了一个丑数，且是从小到大的，易知result[index-1]即为所求。

                //从小到大，找这个数的2倍，3倍，5倍的数的最小值，
                // 若该最小值为其中某个数对应的倍数，则令其加1。
                result[i] = Math.min(result[p2]*2, Math.min(result[p3]*3, result[p5]*5));
                if(result[i] == result[p2]*2)p2++;//为了防止重复
                if(result[i] == result[p3]*3)p3++;
                if(result[i] == result[p5]*5)p5++;
            }
            return result[index-1];
        }
    }