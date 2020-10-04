package 数据结构与算法.第二遍.位运算;

//位运算
public class 不用加减乘除做加法 {
public int add(int a,int b){
 int sum,carry;
 sum = a^b;//不考虑进位时，相加，亦或
 carry = (a & b) << 1;//进位，只有1和1相加时才有可能产生进位，
    // 那么我们可以使用对1和1进行按位与，但此时的结果是1，
    // 而1和1相加的结果应该为10，怎么使1变到10呢？只需把1向左移动一位即可
 if (carry == 0){//若进位为0，则说明计算结束，返回sum即可
     return sum;
 }
 //否则，继续继续对上面得到的两个数相加，直到进位为0
 sum = add(sum,carry);
 return sum;
}
}
