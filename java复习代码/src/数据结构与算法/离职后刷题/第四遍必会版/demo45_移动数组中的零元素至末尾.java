package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo45_移动数组中的零元素至末尾 {
    public void moveZeroes(int[] nums) {
        int index = 0;
        //1.先把非零元素排好
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }
        //2.再补零
        while (index < nums.length) {
            nums[index] = 0;
            index++;
        }
    }


    //自写一遍
    public void moveZeroes02(int[] nums) {
        int index =0;
        for (int i = 0;i<nums.length;i++){
            if (nums[i] != 0){
                nums[index] = nums[i];
                index++;
            }

            //补零
            while (index <nums.length){
                nums[index] = 0;
                index++;
            }
        }
    }
}
