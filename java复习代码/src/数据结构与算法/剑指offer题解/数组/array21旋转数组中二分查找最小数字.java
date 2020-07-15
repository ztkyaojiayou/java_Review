package 数据结构与算法.剑指offer题解.数组;

/**
 * 【题目】
 * 把一个数组最开始的若干个元素搬到数组的末尾， 我们称之数组的旋转。
 * （注意：但这若干个元素的顺序是不动的）
 * 输入一个非递减排序（即有可能有重复值）的数组的一个旋转， 输出旋转数组的最小元素。
 * 例如输入数组{ 3, 4, 5, 1, 2} 为{ 1, 2, 3, 4, 5}的一个旋转，该数组的最小值为 1。
 *
 * 【解】（1）首先明确：1.输入的数组是一个由非递减数组旋转之后的数组，而不是那个原非递减数组
 *                    2.对于二分查找,务必注意：
 *                    2.1不要考虑数据个数的奇偶问题，直接按奇数处理即可（偶数也就同时满足），不用纠结
 *                    2.2其边界left和right只需要按照题目要求和mid交换即可，不用考虑其他值，即范围只有三种：
 *                       最开始的[left,right],后面的[left,mid](即令right=mid）或[mid,right]（即令left=mid）
 *
 *      （2）思路：虽然是被旋转了，但这两小块仍然是非递减数组，因此可以考虑利用二分查找：
 *               将被旋转数组对半分，可以得到一个包含最小元素的新旋转数组（如{5,1,2}），以及一个非递减排序的数组(如{3,4}。
 *               新的旋转数组的数组元素是原数组的一半，从而将问题规模减少了一半，
 *               这种折半性质的算法的时间复杂度为 O(logN)（为了方便，这里将 log<sub>2</sub>N 写为 logN）。

 *               此时问题的关键在于确定对半分得到的两个数组哪一个是旋转数组，哪一个是非递减数组。
 *               我们很容易知道非递减数组的第一个元素一定小于等于最后一个元素。
 *               通过修改二分查找算法进行求解（left 代表 左边界，mid代表 中间值，right 代表 右边界）：
 *
 * - 1.当 nums[mid] <= nums[right] 时，表示 [mid, right] 区间内的数组是非递减数组，[left, mid] 区间内的数组是旋转数组，
 * 此时令 right = mid；即范围缩小到左半边，即[left,mid]
 * - 2.否则 [mid + 1, right] 区间内的数组是旋转数组，令 left = mid + 1,即范围缩小到右半边，即[mid + 1,left]。
 *
 * - 3.但若数组元素允许重复，会出现一个特殊的情况：nums[left] == nums[mid] == nums[right]，
 *     此时无法确定解在哪个区间，需要切换到顺序查找。
 *     例如对于数组 {1,1,1,0,1}，left、mid 和 right 指向的数都为 1，此时无法知道最小数字 0 在哪个区间，
 *     只需从头到尾一个一个比较即可（每一个区间遇到这样的情况都这样处理即可）。
 */

/**
 * 就以数组{ 1, 2, 3, 4, 5}（普通情况）和{0,1,1,1,1}（特殊情况）为原数组为例：
 * 1.当为{ 1, 2, 3, 4, 5}时，则其旋转数组（有且仅）分为两种情况：
 *  （1）{4,5,1,2,3}：即旋转之后，nums[mid] <= nums[right],中间数比右边的数小（或中间数及其右边数还是构成递增数列），此时最小数肯定在中间数的右边
 *  （2）{3,4,5,1,2}：即旋转之后，中间数比右边的数大（或中间数及其右边数不再构成递增数列），此时最小数肯定在中间数的左边
 * 2.当为特殊情况{0,1,1,1,1}时，若被旋转之后的数组为{1,1,1,0,1}，则nums[left] == nums[mid] == nums[right]，此时只需要切换到顺序/普通查找，一个一个比较，直到找到最小值即可。
 */
public class array21旋转数组中二分查找最小数字 {//非递归版
    public int minNumberInRotateArray(int[] nums) {//这个数组已经是旋转之后的数组了，如{5,6,1,2,3},{3,4,5,1,2}
        if (nums.length == 0)
            return 0;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;//即中间值（（left + right）/2）
            if (nums[left] == nums[mid] && nums[mid] == nums[right])//特殊情况，单独考虑,从头到尾，一个一个比较即可
                return minNumber(nums, left, right);
            else if (nums[mid] <= nums[right])//表示 [mid, right] 区间内的数组是非递减数组，[left, mid] 区间内的数组是被旋转的数组，此时令 right = mid；
                right = mid;
            else
                left = mid + 1;//否则 [mid + 1, right] 区间内的数组是被旋转的数组，令 left = mid + 1。
        }
        return nums[left];
    }

    //特殊情况，单独考虑，从头开始，一个一个比较即可
    private int minNumber(int[] nums, int left, int right) {
        int minNumber = 0;
        for (int i = left; i < right; i++) {
            if (nums[i] > nums[i + 1]) {
                minNumber = nums[i + 1];
            } else {
                minNumber = nums[i];
            }
        }
        return minNumber;
    }
}
