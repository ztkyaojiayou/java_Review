package 数据结构与算法.LeetCode题解.排序;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
 * 找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 *
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 */

/**
 * 思路解析：有两种方法
 * （1）方法1（暴力解法）：线性扫描/即从左右两端依次遍历一遍数组，对 target 检查每一个下标，
 * 一个一个对比，一定能得到正确答案即可（不推荐）
 *
 * 具体步骤：
 * 1）首先，我们对 nums 数组从左到右做线性遍历，当遇到 target 时中止。
 * 如果我们没有中止过，那么 target 不存在，我们可以返回“错误代码” [-1, -1] 。
 * 2）如果我们找到了有效的左端点坐标，我们可以再来第二遍线性扫描，但这次从右往左进行。
 * 这一次，第一个遇到的 target 将是最右边的一个（因为最左边的一个存在，所以一定会有一个最右边的 target）。
 * 我们接下来只需要返回这两个坐标。
 *
 * 复杂度分析
 * 1）时间复杂度： O(n)
 * 这个暴力解法检测了num 数组中每个元素恰好两次，所以总运行时间是线性的。
 * 2）空间复杂度： O(1)
 * 线性扫描方法使用了固定大小的数组和几个整数，所以它的空间大小为常数级别的。
 */
class Solution34_1 {
    public int[] searchRange(int[] nums, int target) {
        //0.定义结果集，初始为（-1,-1），即默认没有找到目标值target
        int[] result = {-1, -1};

        //1.先从左边遍历，找到第一个目标值的下标，找到就退出
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                result[0] = i;
                break;
            }
        }

        //1.1如果扫完所有元素都没有找到目标值（即result[0] == -1时），
        //就说明该数组中压根就不存在该目标值，于是返回 [-1, -1]即可.
        if (result[0] == -1) {
            return result;
        }

        //2.同理，再从右边遍历，找到第二个目标值的下标，找到就退出
        for (int j = nums.length-1; j >= 0; j--) {
            if (nums[j] == target) {
                result[1] = j;
                break;
            }
        }
        //3.最后，返回结果集即可
        return result;
    }
}

/**
 * 方法 2：二分查找：因为数组已经排过序了，我们可以使用二分查找的方法去定位左右下标。
 *
 * 算法
 * 版本1：总体算法工作过程与线性扫描方法类似，除了找最左和最右下标的方法。
 * 这里我们仅仅做几个微小的调整，用这种修改过的二分查找方法去搜索这个排过序的数组。
 * 首先，为了找到最左边（或者最右边）包含 target 的下标（而不是找到的话就返回 true ），所以在我们找到一个 target 后不能马上停止。
 * 我们需要继续搜索，直到 left == right 且它们在某个 target 值处下标相同。
 *
 * 另一个改变是 isFindFirst 参数的引入，它是一个 boolean 类型的变量，用于指示我们在遇到 target == nums[mid] 时应该做什么。
 * 如果 isFindFirst 为 true ，那么我们递归查询左区间，否则递归右区间。
 * 考虑如果我们在下标为 i 处遇到了 target ，最左边的 target 一定不会出现在下标大于 i 的位置，
 * 所以我们此时永远不需要考虑右子区间。同理，当求最右下标时，道理同样适用。
 *
 * 版本2：这道题主要难点就在于缩小查找范围上，查找左边界就需要不断地向左边界缩小查找范围。
 * 如何缩小查询范围，到最后能够直接找到左边界的？在 nums[mid] == target 的时候，令 right = mid - 1 ，令查找范围向左边缩小。
 * 因此在查找左右边界的时候，只需要不断地缩小范围，最后返回符合条件的左右边界指针即可。
 * 为什么right = mid - 1，而不是像普通二分查找那样直接right = mid呢？
 * 因为需要查找的是左边界，需要不断的缩小当前查找范围，举例来说，如果target = 8，nums = [8]，因为一直满足 nums[mid] == target 这个条件，
 * 因此搜索范围不会产生变化，因此会进入死循环。
 *
 * 所以在返回左右边界指针时，还需要判断是否符合条件，即在跳出循环（即 left < right）返回的边界可能存在两种情况：
 * 1、可能越界
 * 2、指针所在位置不等于target元素
 * 这两种情况都直接返回-1，代表数组中不存在与target元素相等的值。
 *
 */
class solution34_2 {
        public int[] searchRange(int[] nums, int target) {
            //0.特判
            if(nums==null) {
                return new int[]{-1,-1};
            }
            //1.找到第一个位置和最后一个位置并返回
            int firstIndex = find(true,nums,target);
            int lastIndex = find(false,nums,target);
            return new int[]{firstIndex,lastIndex};
        }

        //2.开始查找第一个和最后一个元素
        //2.1首先，找到目标值的位置（就是标准的二分查找逻辑）
        private int find(boolean isFindFirst,int[] nums,int target) {
            int begin = 0;
            int end = nums.length-1;
            while(begin<=end) {
                int mid = begin+(end-begin)/2;
                if(nums[mid]>target) {
                    end = mid-1;
                }
                else if(nums[mid]<target) {
                    begin = mid+1;
                }

                else {//2.2找到目标值之后，开始定位到第一个和最后一个位置
                    //查找第一个和最后一个逻辑很类似，这里用一个变量isFindFirst区分是查找第一个还是查找最后一个
                    if(isFindFirst) {//2.2.1若为true，则表示要查找第一个位置了
                        //具体操作为：缩小右边界end为mid-1，继续往左边查找即可
                        //而前提条件为：
                        //1）因为往左搜索是end为mid-1，则要求mid不能越左界，即mid要大于0；
                        //2)且只有当其左边的值与当前值相等时才有向左搜索的必要，
                        //因为若没有与nums[mid]的值，就说明其左边的值必定比目标值小（因为是排序了的）了。
                        //否则就说明其前没有与目标值相等的值了，就返回mid即可
                        if(mid>0 && nums[mid]==nums[mid-1]) {
                            end = mid-1;
                        } else {//否则就说明其前没有与目标值相等的值了，就返回mid即可
                            return mid;
                        }
                    }
                    else {//2.2.2而若为false，则表示要查找最后一个位置了
                        //同理，具体操作为：增大左边界为mid+1，继续往右边查找
                        //前提条件也为：1）mid也不能越右界，
                        //2）且其右边的第一个值必须和nums[mid]相等，才有搜索的必要，否则就说明其后没有与目标值相等的值了，就返回mid即可
                        if(mid<nums.length-1 && nums[mid]==nums[mid+1]) {
                            begin = mid+1;
                        } else {//否则就说明其后没有与目标值相等的值了，就返回mid即可
                            return mid;
                        }
                    }
                }
            }
            //2.3若最后都没有找到一个mid值，即与目标值相等的索引，则直接返回-1即可
            return -1;
        }
    }

