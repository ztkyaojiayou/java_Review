package 数据结构与算法.第三遍;

public class demo60_递归之跳台阶_爬楼梯_矩形覆盖 {
    //方法1：递归版
    public int JumpFloor01(int target) {
        if (target < 3){
            return target;
        }
        //对于一般情况，使用递归
        return JumpFloor01(target-1) + JumpFloor01(target-2);
    }

    //方法2：迭代版
    public int JumpFloor02(int target) {

        if (target < 3){
            return target;
        }

        int pre1 = 1,pre2 = 2,sum = 0;
        for (int i = 3;i<=target;i++){
            sum = pre1 + pre2;
            pre1 = pre2;
            pre2 = sum;
        }
        return sum;
    }
}
