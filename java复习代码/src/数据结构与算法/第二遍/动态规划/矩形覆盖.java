package 数据结构与算法.第二遍.动态规划;

/**
 * 题目描述(最简单）
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 */

//和跳台阶一模一样，这里贴的优化版
public class 矩形覆盖 {
    public int RectCover(int target){
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
