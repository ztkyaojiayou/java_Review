package 数据结构与算法.第三遍;

public class demo14_2map之找出数组中的任一重复数字 {
    //重复：即频率大于1
    public int findRepeatNumber(int[] nums) {
        int len = nums.length;
        //使用map即可
        int[] map = new int[len];
        for (int i = 0;i<len;i++){
            //若某数重复，就返回
            if (map[nums[i]] > 1){
                return nums[i];
            }
            map[nums[i]]++;
        }
        return -1;
    }
}
