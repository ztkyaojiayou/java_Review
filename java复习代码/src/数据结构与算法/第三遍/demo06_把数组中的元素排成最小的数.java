package 数据结构与算法.第三遍;

public class demo06_把数组中的元素排成最小的数 {
    public String PrintMinNumber(int [] nums) {
        //先使用暴力循环把两两元素排序，小数在前，大数在后
        for (int i = 0;i<nums.length-1;i++){
            for (int j = i+1;j<nums.length;j++){
                //再把这两个元素拼接成一个Integer型进行比较（属于自定义比较规则）
                Integer append1 = Integer.valueOf(nums[i] + "" + nums[j]);//必须加上“”，因为不然的话，就直接相加了
                Integer append2 = Integer.valueOf(nums[j] + "" + nums[i]);//同理
                //调换，使较小数排在前面
                if (append1 > append2) {
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
            }
        }
        //此时原数组的元素顺序拼接后就是最小数了，于是只需拼接即可
        String str = "";
        for (int i = 0;i<nums.length;i++){
            str += nums[i];
        }
        //结果
        return str;
    }
}
