package 数据结构与算法.LeetCode题解.数组;

/**
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */

/**
 * 解题思路：遍历数组，并重新给数组赋值，规则为：（太TM妙啦~）
 * （1）把不为0的元素移动数组前方，从位置0开始赋值。
 * （2）而为0的元素先直接跳过。
 * （3）遍历结束后，此时易知，非0元素全部排在了前面，此时只需对后面的元素统一设为0即可。
 */
public class 移动零元素至末尾283 {
    public void moveZeroes(int[] nums) {
        int index = 0;
        //for(int num:nums){//新式遍历方式
        //1.首先，遍历数组，把不为0的元素移动数组前方，从位置0开始赋值。
            for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                nums[index]=nums[i];
                index++;
            }
        }
            //2.非0元素统计完了，剩下的都是0了，所以此时只需把末尾的元素都赋为0即可
        while(index<nums.length){
            nums[index] = 0;
            index++;
        }
    }
}
