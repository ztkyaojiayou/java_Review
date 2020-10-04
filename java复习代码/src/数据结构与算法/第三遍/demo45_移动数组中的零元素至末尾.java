package 数据结构与算法.第三遍;

public class demo45_移动数组中的零元素至末尾 {
    public void moveZeroes(int[] nums) {
        int index  = 0;
        for (int i = 0;i<nums.length;i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }

             //再补零
            while (index < nums.length){
                nums[index] = 0;
                index++;
            }
    }
}
