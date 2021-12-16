package 数据结构与算法.第三遍;

public class demo10_1位运算之不用加减乘除做加法 {
    public int addTwoSum(int a, int b){
        int sum,carry;
        sum = a ^ b;//不算进位时的和
        carry = (a & b) << 1;//进位
        if (carry == 0){//若进位为0，则表示计算结束，否则继续相加
            return sum;
        }
        sum = addTwoSum(sum,carry);
        return sum;
    }
}
