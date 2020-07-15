package 数据结构与算法.剑指offer题解.数组;

/**
 * 题目：统计一个数字：在排序数组中出现的次数。
 * 例如输入排序数组｛ 1, 2, 3, 3, 3, 3, 4, 5｝和数字 3 ，
 *      由于 3 在这个数组中出现了 4 次，因此输出 4 。
 *
 * 【解】：
 *      方法1：暴力破解：定义一个计数变量cnts，遍历整个数组，若和k相等，则cnts+1，即可
 *      方法2：二分查找：
 *            既然是排好序的数组,（就可以考虑二分查找，这要形成一个惯性思维）
 *            所以我们可以直接使用两次二分查找，得到数字第一次出现和最后一次出现的下标，
 *            时间复杂度：2 * O(logN)
 *
 *
 */

public class array52某一数字在排序数组中出现的次数 {

    /**
     * 方法1：暴力破解，即一个一个去比较，其实也是可以的，很多人都在用
     * @param array
     * @param k
     * @return
     */
    public int GetNumberOfK01(int [] array , int k) {
        int cnts=0;
        int len=array.length;
        for(int i=0;i<len;i++){//遍历数组
            if(array[i]==k) cnts++;//相等就计数
        }

        return cnts;
    }


    /**
     * 方法2：二分查找
     * 写法1
     * @param array
     * @param k
     * @return
     */
    public int GetNumberOfK02(int [] array , int k) {
        //使用二分查找即可,先找到搜索值k的第一个索引index
        //可是这样直接用API（Arrays）里面的二分查找真的好吗？
        int index = binarySearch(array, k);//在目标数组array中返回搜索值k的索引
        if(index<0)return 0;
        int cnt = 1;
        //先向index之后统计值为k的个数
        for(int i=index+1; i < array.length && array[i]==k;i++)
            cnt++;
        //再index之前统计值为k的个数
        for(int i=index-1; i >= 0 && array[i]==k;i--)
            cnt++;
        return cnt;
    }

    /**
     * 写法2
     * @param nums
     * @param K
     * @return
     */
    //这个方法也是使用的相同方法，即二分查找，但是这里是假设传入的目标排好序的数组是连续的，谁说的？？？
    public int GetNumberOfK03(int[] nums, int K) {
        int first = binarySearch(nums, K);
        int last = binarySearch(nums, K + 1);//要是没有k+1呢？？？
        return (first == nums.length || nums[first] != K) ? 0 : last - first;
    }

    //二分查找的具体实现，获取k第一次出现的下标
    private int binarySearch(int[] nums, int K) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= K){//则说明k在左边
                right = mid;
            }
            else{//说明k在右边
                left = mid + 1;
            }
        }

        return left;//返回最左边的索引，即为k第一次出现的下标，若为right，则为最后一次出现的下标
    }


}
