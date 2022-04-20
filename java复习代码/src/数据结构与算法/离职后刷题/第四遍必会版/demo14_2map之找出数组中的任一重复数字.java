package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo14_2map之找出数组中的任一重复数字 {
    //重复：即频率大于1
    public int findRepeatNumber(int[] nums) {
        int len = nums.length;
        //使用map即可，这里使用的是数组
        //那么逻辑就是：
        // 元素本身看成map中的key，
        // 频次看成数组的值/也即相当于map中的value，也很巧妙！！！
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
