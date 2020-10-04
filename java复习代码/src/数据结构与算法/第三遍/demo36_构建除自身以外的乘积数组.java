package 数据结构与算法.第三遍;

public class demo36_构建除自身以外的乘积数组 {
    public int[] constructArr(int[] nums) {
        if (nums == null || nums.length == 0){
            return null;
        }
        int len = nums.length;
        //结果数组
        int[] res = new int[len];
        //左边乘积，即先把数组填充为左乘积
        int left_sum = 1;
        for (int i = 0;i<len;i++){//从左往右
            res[i] = left_sum;
            left_sum = left_sum*nums[i];
        }
        //右边乘积，即在左乘积的基础上再乘上右乘积
        int right_sum = 1;
        for (int j = len-1;j>0;j--){//从右往左
            right_sum = right_sum * nums[j];
            res[j]  = right_sum;
        }
        return res;
    }
}
