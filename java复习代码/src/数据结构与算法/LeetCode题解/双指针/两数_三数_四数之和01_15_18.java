package 数据结构与算法.LeetCode题解.双指针;

import java.util.*;

/**
 * 12. 两数之和（掌握）
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
//思路：使用map做字典映射即可
class 两数之和01 {
    public int[] twoSum(int[] nums,int target){
        //1.使用hashMap做映射
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){//2.开始遍历数组,
            if(map.containsKey(target-nums[i])){//2.1若在map中找到了当前值对应的另一个加数，就通过一个数组返回这两个值所对应的下标即可
                return new int[]{map.get(target-nums[i]),i};
            }
            map.put(nums[i],i);//2.2若没有找到，就把该值当做map中的key，而把其对应的下标就当做value放入map中
        }throw new IllegalArgumentException("没有找到");
    }
}

/**
 * 15. 三数之和（掌握）
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
 * 请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */

/**
 * 思路解析:使用双指针
 * 版本1：
 * 1）首先对数组进行排序，排序后固定一个数 nums[i]，再使用左右指针指向 nums[i]后面的两端，
 * 数字分别为 nums[L] 和 nums[R]，计算三个数的和 sum 判断是否满足为 0，满足则添加进结果集
 * 2）如果 nums[i]大于 0，则三数之和必然无法等于 0，结束循环
 * 3）如果 nums[i] == nums[i−1]，则说明该数字重复，会导致结果重复，所以应该跳过
 * 4）当 sum == 0 时，nums[L] == nums[L+1] 则会导致结果重复，应该跳过，L++
 * 5)当 sum == 0 时，nums[R] == nums[R−1] 则会导致结果重复，应该跳过，R−−
 *
 * 版本2：
 * 解题思路：
 * 暴力法搜索为 O(N^3),舍弃
 * 时间复杂度，可通过双指针动态消去无效解来优化效率。
 * 双指针法铺垫： 先将给定 nums 排序，复杂度为 O(NlogN)。
 * 双指针法思路： 固定 3 个指针中最左（最小）数字的指针 k，双指针 i，j 分设在数组索引 (k, len(nums))(k,len(nums)) 两端，
 * 通过双指针交替向中间移动，记录对于每个固定指针 k 的所有满足 nums[k] + nums[i] + nums[j] == 0 的 i,j 组合：
 * 当 nums[k] > 0 时直接break跳出：因为 nums[j] >= nums[i] >= nums[k] > 0，即 3 个数字都大于 00 ，在此固定指针 k 之后不可能再找到结果了。
 * 当 k > 0且nums[k] == nums[k - 1]时即跳过此元素nums[k]：因为已经将 nums[k - 1] 的所有组合加入到结果中，本次双指针搜索只会得到重复组合。
 * i，j 分设在数组索引 (k, len(nums))(k,len(nums)) 两端，当i < j时循环计算s = nums[k] + nums[i] + nums[j]，并按照以下规则执行双指针移动：
 * 当s < 0时，i += 1并跳过所有重复的nums[i]；
 * 当s > 0时，j -= 1并跳过所有重复的nums[j]；
 * 当s == 0时，记录组合[k, i, j]至res，执行i += 1和j -= 1并跳过所有重复的nums[i]和nums[j]，防止记录到重复组合。
 *
 * 复杂度分析：
 * 时间复杂度 O(N^2)：其中固定指针k循环复杂度 O(N)，双指针 i，j 复杂度 O(N)，N为数组长度。
 * 空间复杂度 O(1)O(1)：指针使用常数大小的额外空间。
 */
class 三数之和15 {
        public static List<List<Integer>> threeSum(int[] nums) {
            //0.1结果集
            List<List<Integer>> ans = new ArrayList();
            int len = nums.length;//数组长度
            //0.2特判
            if(nums == null || len < 3) return ans;
            //1.先对数组排序（默认是升序）
            Arrays.sort(nums);
            //2.再开始遍历数组中的每一个元素，把正在遍历的当前元素nums[i]固定起来，（非常重要）
            // 再从其后面的数中，定义两个指针，一前一后开始扫描
            for (int i = 0; i < len ; i++) {//一层循环即可，后面的四数相加则需要两层循环，但思路完全相同）
                //2.1如果第一个数字就大于0，则三数之和一定大于0（因为第一个元素已经是最小的数了），所以直接结束循环
                if(nums[i] > 0) break;
                //2.2去重，即跳过重复值
                if(i > 0 && nums[i] == nums[i-1]) continue;
                int L = i+1;//左指针，从正在遍历的当前元素的后一个元素开始遍历
                int R = len-1;//右指针，从末端元素开始遍历
                //2.3左右指针开始扫描（此时num[i]固定）
                while(L < R){
                    int sum = nums[i] + nums[L] + nums[R];//目标和
                    //2.3.1若和为0，则说明满足要求，此时就把结果存入结果集中，同时还要去重
                    if(sum == 0){
                        ans.add(Arrays.asList(nums[i],nums[L],nums[R]));//数组转化为list并存入
                        //1）去重（注意：此时的去重是指在固定了num[i]之后的去重
                        while (L<R && nums[L] == nums[L+1]) L++; // 去重
                        while (L<R && nums[R] == nums[R-1]) R--; // 去重
                        //2）接着继续寻找在固定了num[i]之后的下一组值，只需反向移动双指针i和j即可
                        L++;
                        R--;
                    }
                    //2.3.2若此时（即在固定了num[i]前提下）和不为0，
                    // 则可分为两种情况，即大于0和小于0，分别处理（因为数组已经排好序了，分开处理来的方便）
                    //1）若和小于0，则说明是左边的数太小，要向右移动，使nums[L]变大
                    else if (sum < 0) L++;
                    //2）若和大于0，则说明是右边的数太大，要向左移动，使nums[R]变小
                    else if (sum > 0) R--;
                }
            }
            //3.最后，当所有的元素都一一固定并遍历完之后，就可以返回结果集啦。
            return ans;
        }
    }

/**
 * 18. 四数之和（理解即可）
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
 * 判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？
 * 找出所有满足条件且不重复的四元组。
 *
 * 注意：
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 */

/**
 * 思路解析：和三数之和思路完全相同，也是使用双指针
 * 思路：
 * (1) 第一层循环选择第一个数固定然后去遍历其它三个数。
 * (2) 第二层循环选择第二个数固定然后去遍历最后两个数。
 * (3) 通过双指针选出最后符合条件的两个数。
 *
 * 双指针再双指针解法
 * 由于是每次四位数的相加
 * 所以可以先定出第一位数与最后一位数的指针，也就是i跟j
 * i为什么要减3，因为i到了最后的3个都是前面已经遍历过了，再遍历就有重复了
 * 第一次双指针是确定外围，第二次则是内围
 * 例如i=0 j则是最后一个
 * 然后第二位数与第三位数，都在i与j的基础上分别+1，-1
 * 直到缩到不能缩进行下一轮，直至集合完毕。
 */
class 四数之和18 {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> result = new LinkedList<>();
            if (nums == null || nums.length < 4) return result;
            // 排序
            Arrays.sort(nums);
            int len =  nums.length - 3;
            for (int i = 0; i < len; i++) {//第一层循环
                // 去重
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                for (int j = nums.length - 1; j > i + 1; j--) {//第二层循环（嵌套在了第一层循环里面）
                    int L = i + 1;
                    int R = j - 1;
                    while (R > L) {
                        int sum = nums[i] + nums[L] + nums[R] + nums[j];
                        if (sum == target) {
                            // 由于尾指针去重不了，所以加了一层校验，这一块待优化
                            List<Integer> list = Arrays.asList(nums[i], nums[L], nums[R], nums[j]);
                            if (!result.contains(list)) {
                                result.add(list);
                            }
                            while (R > L && nums[R - 1] == nums[R]) R--; // 去重
                            while (R > L && nums[L + 1] == nums[L]) L++; // 去重
                            L++;
                            R--;
                        }
                        if (sum > target) R--;
                        if (sum < target) L++;
                    }
                }
            }
            return result;
        }
    }
