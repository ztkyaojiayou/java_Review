package 数据结构与算法.第二遍.栈的实现;

//先入后出
public class 用数组实现Stack {
//定义成员变量
    private int[] nums;//用数组来存储元素，代表栈
    private int cur_count;//当前数组中元素的个数（也即当前栈中元素的个数）
    private int num_size;//数组的大小（也即栈的大小）
//构造器
    public 用数组实现Stack(int num_size){
        this.nums = new int[num_size];
        this.cur_count = 0;
        this.num_size = num_size;
    }

//入栈
    public boolean push(int num){
        if (cur_count == num_size){
            return false;
        }
        nums[cur_count] = num;
        cur_count++;
        return true;
    }

    //出栈
    public int pop(){
        if (cur_count == 0){
            throw new RuntimeException("stack is empty");
        }
        int res = nums[cur_count - 1];
        cur_count--;
        return res;
    }

    //查看栈顶元素
    public int peek(){
        if (cur_count == 0){
            throw new RuntimeException("stack is empty");
        }
        int res = nums[cur_count - 1];
        return res;
    }

    //查看栈是否为空
    public boolean isEmpty(){
        return cur_count == 0;
    }
}
