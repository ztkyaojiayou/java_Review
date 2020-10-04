package 数据结构与算法.第三遍;

public class demo35_找出数组中的任一重复数字 {
    public int findRepeatNumber(int[] nums) {
        int len = nums.length;
        //使用map即可
        int[] map = new int[len];
        for (int i = 0;i<len;i++){
            if (map[nums[i]] > 1){
                return nums[i];
            }
            map[nums[i]]++;
        }
        return -1;
    }
}
