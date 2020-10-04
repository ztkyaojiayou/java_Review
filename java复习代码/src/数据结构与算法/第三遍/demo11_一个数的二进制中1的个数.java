package 数据结构与算法.第三遍;

public class demo11_一个数的二进制中1的个数 {
    public int NumberOf1(int n) {
        int count = 0;
        while (n != 0){//直到为0为止
            count++;
            n &= (n-1);//不断地和（n-1）相与（&）
        }
        return count;
    }
}
