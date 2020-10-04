package 数据结构与算法.第二遍.位运算;

//方法一（推荐）：易知，n 每和 (n-1) 相与一次，1就减一，
//因此可以把与完的结果再和其n-1与一次，再用计数器将其加1，直到n为0;
public class 一个数的二进制中1的个数 {
    public int bit1(int n){
        int count = 0;
        while (n != 0){
            count ++;
            n = n & (n-1);
        }
        return count;
    }
}
