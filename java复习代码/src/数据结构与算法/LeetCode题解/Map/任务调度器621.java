package 数据结构与算法.LeetCode题解.Map;

import java.util.Arrays;

/**
 * 621. 任务调度器
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。
 * 其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。
 * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。
 * CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，
 * 因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
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
 * 思路解析：
 * 1、将任务按类型分组，正好A-Z用一个int[26]保存任务类型个数
 * 2、对数组进行排序，优先排列个数（count）最大的任务，
 *    如题得到的时间至少为 retCount =（count-1）* (n+1) + 1 ==> A->X->X->A->X->X->A(X为其他任务或者待命)
 * 3、再排序下一个任务，如果下一个任务B的个数和最大任务数一致，
 *    则retCount++ ==> A->B->X->A->B->X->A->B
 * 4、如果空位都插满之后还有任务，那就随便在这些间隔里面插入就可以，
 *    因为间隔长度肯定会大于冷却时间n，在这种情况下就是任务的总数是最小所需时间
 *
 *
 *（1）可以从另一个角度理解这个问题，参考桶思想，讲的非常清楚：
 * 我们先建立大小为n+1的桶子，个数为任务数量最多的那个任务，比如，等待时间n=2，A任务个数6个，我们建立6个桶子，每个容量为3：
 * 我们可以把一个桶子看作一轮任务：
 * 1）先从最简单的情况看起，现在就算没有其他任务，我们完成任务A所需的时间应该是（6-1）*3+1=16，因为最后一个桶子，不存在等待时间。
 * 2）接下来我们添加些其他任务
 *   可以看到C其实并没有对总体时间产生影响，因为它被安排在了其他任务的冷却期间；
 *   而B和A数量相同，这会导致最后一个桶子中，我们需要多执行一次B任务，现在我们需要的时间是（6-1）*3+2=17
 *
 *小结：前面两种情况，总结起来：总排队时间 = (桶个数 - 1) * (n + 1) + 最后一桶的任务数
 *
 * 3）当冷却时间短，任务种类很多时：
 *    比如，我们刚好排满了任务，此时所需时间还是17，如果现在我还要执行两次任务F，该怎么安排呢？
 *    此时我们可以临时扩充某些桶子的大小，插进任务F，对比一下插入前后的任务执行情况：
 *    插入前：ABC | ABC | ABD | ABD | ABD |AB
 *    插入后：ABCF | ABCF | ABD | ABD | ABD |AB
 *    我们在第一个、第二个桶子里插入了任务F，不难发现无论再继续插入多少任务，我们都可以类似处理，而且新插入元素肯定满足冷却要求
 *    继续思考一下，这种情况下其实每个任务之间都不存在空余时间，冷却时间已经被完全填满了。
 *    也就是说，我们执行任务所需的时间，就是任务的数量
 *
 * 4）这样剩下就很好处理了，我们只需要算两个数：
 *   1.记录最大任务数量N，看一下任务数量并列最多的任务有多少个，
 *   即最后一个桶子的任务数X，计算NUM1=（N-1）*（n+1）+x
 *   2.NUM2=tasks.size()
 *   3.再输出其中的较大值即可，因为存在空闲时间时肯定是NUM1大，不存在空闲时间时肯定是NUM2>=NUM1
 *
 *（2）复杂度分析：
 *     时间复杂度O（nlogn），空间复杂度O（1）
 *
 * 参考链接：https://leetcode-cn.com/problems/task-scheduler/solution/tong-zi-by-popopop/
 */
public class 任务调度器621 {

    public int leastInterval02(char[] tasks, int n) {
        if (tasks.length <= 1 || n < 1) return tasks.length;
        //1.统计各个任务出现的次数最多的任务有几种(老生常谈啦）
        int[] map = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            map[tasks[i] - 'A']++;
        }
        //2.对该map进行排序（升序），确定次数最多的任务是哪个（此时肯定在 map[25]，假设为任务A）
        Arrays.sort(map);
        int maxCount = map[25];
        //3.再求出最少时间，即“把前（maxCount-1）个A任务的空闲时间都填满其他任务时所需要的时间
        // （即相当于n+1个A任务执行的时间， 也即(maxCount - 1) * (n + 1)，因为每个任务执行的时间是一样的）”
        // + 最后一个A任务执行的时间（因为最后一个任务执行时，不存在冷却时间（很好理解），所以单独拿出来计算）。
        int retCount = (maxCount - 1) * (n + 1) + 1;
        //4.再依次考虑下一个最大的任务，如果下一个任务B的个数和最大任务数一致，
        //这会导致最后一个桶子中，我们需要多执行一次B任务，因此使retCount++即可，以此类推，直到把所有的任务都考虑完毕。
        //
        int i = 24;
        while (i >= 0 && map[i] == maxCount) {
            retCount++;
            i--;
        }
        //5.上面的情况是在冷却期内插其他任务，即冷却期还没有完全插满时的计算情况，
        //但如果我们在冷却期内已经排满了所有的任务，且还要执行两次任务F时，应该怎么办呢？
        //此时易知，我们可以临时扩充某些桶子的大小，插进任务F，
        //不难发现无论再继续插入多少任务，我们都可以类似处理，而且新插入元素肯定满足冷却要求
        //继续思考一下，这种情况下其实每个任务之间都不存在空余时间，冷却时间已经被完全填满了。
        //也就是说，我们执行任务所需的时间，就是任务的数量tasks.length
        //因此最后我们只需要取这二者的最大值即可
        return Math.max(retCount, tasks.length);
    }

}