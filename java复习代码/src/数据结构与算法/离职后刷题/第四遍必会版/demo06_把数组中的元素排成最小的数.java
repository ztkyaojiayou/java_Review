package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo06_把数组中的元素排成最小的数 {
    public String PrintMinNumber(int[] nums) {
        //先使用暴力循环把两两元素排序，小数在前，大数在后
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                //再把这两个元素拼接成一个Integer型进行比较（属于自定义比较规则）
                //必须加上“”，因为不然的话，就是直接相加了
                Integer append1 = Integer.parseInt(nums[i] + "" + nums[j]);
                //同理
                Integer append2 = Integer.parseInt(nums[j] + "" + nums[i]);
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
        for (int i = 0; i < nums.length; i++) {
            str += nums[i];
        }
        //结果
        return str;
    }


    //自写一遍
    public String PrintMinNumber02(int[] nums) {
        //先修改原数组的顺序：两两拼接比较
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                //拼接
                int num1 = Integer.parseInt(nums[i] + "" + nums[j]);
                int num2 = Integer.parseInt(nums[j] + "" + nums[i]);
                //此时即应该交换一下这两个元素
                if (num1 > num2) {
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
            }
        }
        //此时只需拼接即为所有拼接数中的最小数
        String res = "";
        for (int i = 0; i < nums.length; i++) {
            res += nums[i];
        }
        return res;
    }

}
