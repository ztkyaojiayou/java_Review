package 数据结构与算法.剑指offer题解.第二遍.动态规划;

//1.普通版
class 普通版 {
    //方法1：使用动态规划
public int JumpFloor01(int target){//target表示第target级台阶
    //0.特判
    if (target == 1){
        return 1;
    }
    //1.定义一个dp数组
    int[] dp = new int[target+1];
    //2.再确定初始值
    dp[0] = 0;
    dp[1] = 1;
    dp[2] = 2;
    //3.再考虑一般情况
    for (int i= 3;i<target;i++){
        dp[i] = dp[i-1] + dp[i-2];
    }
    //4.最后返回结果即可
    return dp[target];
}


//方法2：优化版
//思路：我们发现，每次就用到了最近的两个数，
// 所以我们可以只存储最近的两个数即可
    public int JumpFloor02(int target){
    if (target <= 2){
        return target;
    }

    int pre1 = 1,pre2 = 2,sum = 0;
    for (int i =3;i<target;i++){
        sum = pre1 + pre2;
        pre1 = pre2;
        pre2 = sum;
    }

    return sum;
    }

}


//2.变态版，即一次可以有n中跳法,通过数学推导，发现它是一个递推关系式，所以很简单。
class 变态版{
    public int JumpFloor(int target){
        if (target == 0 ||target == 1){
            return 1;
        }
        int cur=1,sum = 0;
        for (int i = 2;i<target;i++){
            sum = cur<<1;
            cur = sum;
        }
        return sum;
    }

}