package 数据结构与算法.LeetCode题解.贪心算法;

import java.util.Arrays;

/**
 * 621. 任务调度器
 *
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。
 * 其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。
 * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。
 * CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 *
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，
 * 因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 *
 * 你需要计算完成所有任务所需要的最短时间。
 *
 * 示例 ：
 * 输入：tasks = ["A","A","A","B","B","B"], n = 2
 * 输出：8
 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 *      在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，
 *      而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
 *
 * 提示：
 * 任务的总个数为 [1, 10000]。
 * n 的取值范围为 [0, 100]。
 */

/**
 * (没太懂）
 * 核心思想就是找到出现次数最多的字符（记为字符x），把那个按间隔排开。其他字符往间隔插入。分为几种情况。
 * 1 有次数和x一样的字符，这些字符会加长最终长度。因为这些字符的最后一个会排在x的后面
 * 2 空格不够的，会加大最终长度。因为要在间隔插入空格。
 * 3 其余情况不会增加最终长度
 *
 * 如: ['A','A','A','A','B','B','B','B','C','C','D','D','E'] 2
 * 先把A排开， 变成 A - - A - - A - - A
 * 插入B，变成 A B - A B - A B - A B (由于B和A一样是三次，故最后长度+1)
 * 插入C，变成 A B C A B C A B - A B (此时还有空格，长度不变)
 * 插入第一个D, 变成 A B C A B C A B D A B (此时还有空格，长度不变)
 * 插入第二个D, 此时无空格，D随意插入(只要不在上一个D旁边)，长度必然+1，如：A B C D A B C A B D A B E
 * 插入E, 此时无空格，E随意插入位置，长度必然+1，如：A B C D E A B C A B D A B E
 */
public class 任务调度器621 {
        public int leastInterval(char[] tasks, int n) {
            //统计出现的次数，排序
            int[] counts = new int[26];
            for(char ch: tasks){
                counts[ch-'A']++;//把每一个字符变成ASC码中对应的数字
            }
            Arrays.sort(counts);//升序排列

            int maxCycle = counts[25];                    //找到出现次数最多的是多少次
            int emptyPosition = (maxCycle-1) * (n + 1);   //有多少个空位
            int result = emptyPosition;                   //最后结果必不少于空位数
            int element = 0;
            for(int count : counts){
                if(count == maxCycle){
                    result++;
                    element += count-1;
                }else{
                    element += count;
                }
            }
            if(element <= emptyPosition){
                return result;
            }else{
                return result + (element - emptyPosition);
            }
        }
    }

