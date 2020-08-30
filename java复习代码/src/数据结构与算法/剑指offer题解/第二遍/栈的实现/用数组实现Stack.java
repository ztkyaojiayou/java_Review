package 数据结构与算法.剑指offer题解.第二遍.栈的实现;

//先入后出
public class 用数组实现Stack {
//定义成员变量
    private int[] nums;//用数组来存储元素，代表栈
    private int count;//当前数组中元素的个数（也即当前栈中元素的个数）
    private int n;//数组的大小（也即栈的大小）
//构造器
    public 用数组实现Stack(int n){
        this.nums = new int[n];
        this.count = 0;
        this.n= n;
    }

//入栈
    public boolean push(int num){
        if (count == n){
            return false;
        }
        nums[count] = num;
        count++;
        return true;
    }

    //出栈
    public int pop(){
        if (count == 0){
            throw new RuntimeException("stack is empty");
        }
        int res = nums[count - 1];
        count--;
        return res;
    }

    //查看栈顶元素
    public int peek(){
        if (count == 0){
            throw new RuntimeException("stack is empty");
        }
        int res = nums[count - 1];
        return res;
    }

    //查看栈是否为空
    public boolean isEmpty(){
        return count == 0;
    }


}
