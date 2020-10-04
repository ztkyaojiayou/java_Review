package 数据结构与算法.第二遍;

public class 求1至n的和 {
public int Sum(int n){
    int sum = n;
    boolean flag =  (n>1) && ((sum+=Sum(n-1))>0);
    return sum;
}
}
