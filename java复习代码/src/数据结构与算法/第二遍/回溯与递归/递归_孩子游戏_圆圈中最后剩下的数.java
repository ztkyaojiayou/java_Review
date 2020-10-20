package 数据结构与算法.第二遍.回溯与递归;

public class 递归_孩子游戏_圆圈中最后剩下的数 {
public int lastRemaining(int n,int m){
    if (n == 0){//若没人，则返回-1
        return -1;
    }
    if (n == 1){
        return 0;//下标从0开始，当只有一个人时，那么最后那个人就是他本人
    }
    return (lastRemaining(n-1,m) + m )% n;
}
}
