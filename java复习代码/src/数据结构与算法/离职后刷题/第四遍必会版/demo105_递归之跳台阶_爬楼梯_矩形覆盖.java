package 数据结构与算法.离职后刷题.第四遍必会版;

public class demo105_递归之跳台阶_爬楼梯_矩形覆盖 {
    //方法1：递归版
    public int JumpFloor01(int target) {
        if (target < 3) {
            return target;
        }
        //对于一般情况，使用递归
        return JumpFloor01(target - 1) + JumpFloor01(target - 2);
    }

    //方法2：迭代版（推荐）
    public int JumpFloor02(int target) {
        int pre1 = 1, pre2 = 2, sum = 0;
        for (int i = 3; i <= target; i++) {
            sum = pre1 + pre2;
            pre1 = pre2;
            pre2 = sum;
        }
        return sum;
    }

    //自写一遍
    //方法1：递归版
    public int JumpFloor001(int target) {
        //递归出口
        if (target < 3) {
            return target;
        }
        return JumpFloor001(target - 1) + JumpFloor001(target - 2);
    }

    //方法2：迭代版
    public int JumpFloor002(int target) {
        int pre1 = 1;
        int pre2 = 2;
        int sum = 0;
        for (int i = 3; i <= target; i++) {
            //先求前两项之和（核心，相当于是递归到最后的反向计算）
            sum = pre1 + pre2;
            //再更新前两项的值即可
            pre1 = pre2;
            pre2 = sum;
        }
        return sum;
    }
}
