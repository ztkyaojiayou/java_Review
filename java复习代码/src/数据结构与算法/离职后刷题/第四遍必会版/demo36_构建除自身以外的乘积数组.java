package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo36_构建除自身以外的乘积数组 {
    public int[] constructArr(int[] nums) {
//        if (nums == null || nums.length == 0){
//            return null;
//        }
        int len = nums.length;
        //结果数组
        int[] res = new int[len];
        //左边乘积，即先把数组填充为左乘积
        int left_sum = 1;
        for (int i = 0; i < len; i++) {//从左往右
            res[i] = left_sum;
            left_sum = left_sum * nums[i];
        }
        //右边乘积，即在左乘积的基础上再乘上右乘积
        //（即此时的res数组越已经变成了个各元素除自己外的左乘积啦，非常巧妙！！！）
        int right_sum = 1;
        for (int j = len - 1; j > 0; j--) {//从右往左
            //先将该位置的左乘积乘上，再更新（与上面相反哦）
            right_sum = right_sum * nums[j];
            res[j] = right_sum;
        }
        return res;
    }

    //自写一遍
    public int[] constructArr02(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        int leftSum = 1;
        //将res数组更新为左数组的乘积（当然，也是除自己外的左数组）
        for (int i = 0; i < len; i++) {
            res[i] = leftSum;
            leftSum *= nums[i];
        }
        //再从右边开始乘，即可得到结果（此时res已经是除自己外的左乘积了哦！！！）
        int rightSum = 1;
        for (int j = len - 1; j > 0; j--) {
            //先将该位置的左乘积乘上，再更新（与上面相反哦!!!）
            rightSum *= nums[j];
            res[j] = rightSum;
        }
        return res;
    }
}
