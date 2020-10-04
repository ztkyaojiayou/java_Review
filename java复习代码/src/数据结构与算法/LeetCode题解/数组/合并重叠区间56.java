package 数据结构与算法.LeetCode题解.数组;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */

/**
 * 思路解析：可以被合并的区间一定是有交集的区间，前提是区间按照左端点排好序，这里的交集可以是一个点。
 *
 * 至于为什么按照左端点升序排序，这里要靠一点直觉猜想，我没有办法说清楚是怎么想到的，
 * 有些问题的策略是按照右端点升序排序（也有可能是降序排序，具体问题具体分析）。
 * 接着说，直觉上，只需要对所有的区间按照左端点升序排序，然后遍历。
 * 如果当前遍历到的区间的左端点 > 结果集中最后一个区间的右端点，说明它们没有交集，此时把区间添加到结果集；
 * 如果当前遍历到的区间的左端点 <= 结果集中最后一个区间的右端点，说明它们有交集，此时产生合并操作，
 * 即：对结果集中最后一个区间的右端点更新（取两个区间的最大值）。
 */
public class 合并重叠区间56 {
            public int[][] merge(int[][] nums) {
                List<int[]> res = new ArrayList<>();
                if (nums == null || nums.length == 0) return res.toArray(new int[0][]);
                // 1.先按照区间起始位置排序（即对二维数组的每一行的第一个元素进行升序排序）
                Arrays.sort(nums, (v1, v2) -> v1[0] - v2[0]);
                //自定义排序的传统写法如下：
                // Arrays.sort(intervals, new Comparator<int[]>()
                //{
                //    @Override
                //    public int compare(int[] o1, int[] o2) {
                //        // TODO Auto-generated method stub
                //        return o1[0]-o2[0];
                //    }
                //});
                int i = 0;
                while (i < nums.length) {//表示按照二维数组的每一行遍历
                    int left = nums[i][0];//一小行(即一个小区间）的左端点
                    int right = nums[i][1];//一小行的右端点
                    while (i < nums.length - 1 && nums[i + 1][0] <= right) {//若下一个小区间的左端点小于当前区间的右端点时，说明有交集，于是把右端点更新为该两个区间右端点的最大值
                        i++;
                        right = Math.max(right, nums[i][1]);
                    }//否则，区间不变
                    res.add(new int[]{left, right});
                    i++;
                }
                return res.toArray(new int[0][]);
            }
}

/**
 * 补充：
 * java中Arrays.sort()对二维数组进行排序
 * int [][]a = new int [5][2];//定义一个二维数组，其中所包含的一维数组具有两个元素
 * 对于一个已定义的二维数组a进行如下规则排序,首先按照每一个对应的一维数组第一个元素进行升序排序（即a[][0]）,
 * 若第一个元素相等,则按照第二个元素进行升序排序（a[][1]）。
 * (特别注意,这里的a[][0]或者a[][1]在java中是不能这么定义的,
 * 这里只是想说明是对于某一个一维数组的第0（即第一列）或第1个（即第二列）元素进行排序)
 *
 * 案例1：
 * Arrays.sort(a, new Comparator<int[]>() {
 * @Override
 * public int compare(int[] o1, int[] o2) {
 * if (o1[0]==o2[0]) return o1[1]-o2[1];
 * return o1[0]-o2[0];
 * }
 * });
 * 其中o1[1]-o2[1]表示对于第二个元素进行升序排序如果为o2[1]-o1[1]则表示为降序。
 *
 * 案例2：
 *   //只能根据列来比，但传进去的却是一个一个的一维数组行，即一行一行传进去，每次传两行进行比较，
 *         // 再根据每两个一维数组的第二列（即第二个数）来比较第一列的元素（即第一个数）
 *         //易知，该比较规则的前提是：该二维数组只能是两列，但对行数没有要求。（关键）
 *          Arrays.sort(count, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
 *          //老式写法：
 *         Arrays.sort(count, new Comparator<int[]>() {//这里的int[]是一个泛型，其只是表示要传入一维数组，
 *         但并不是强调只传入一个，实际上是要传两个一维数组来比较的
 *             @Override
 *             public int compare(int[] o1, int[] o2) {
 *                 if(o1[1] == o2[1]) return o1[0] - o2[0];
 *                 return o1[1]-o2[1];
 *             }
 *         });
 *
 *
 */