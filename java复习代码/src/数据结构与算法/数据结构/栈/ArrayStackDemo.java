package 数据结构与算法.数据结构.栈;
//用数组模拟（实现）栈（先进后出，类比水桶）
//用top表示栈顶

//0.先做准备工作
class ArrayStack {
    private int[] stack;//定义一个数组，名字就命名为stack
    private int top = -1;//定义栈顶，设置其初始值为-1
    private int maxSize;//栈的大小（其实就是数组的大小，因为这里就是用数组来模拟栈

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //0.1.栈满判断(入栈时要先考虑）
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //0.2.栈空判断(出栈时要先考虑）
    public boolean isEmpty(){
        return top == -1;
    }

    //下面编写栈的操作代码
    //1.入栈-push，先得判断栈是否满
    public void push(int value){//要传入的这个值就是我们要存入栈中的值
        //先判断栈是否满
        if(isFull()) {
            System.out.println("栈满");
            return;
        }
        //再入栈（也叫压栈
        top++;//因为top的初始值为-1，先加一，再存值
        stack[top] = value;//存值，入栈
    }


    //2.出栈-pop，即获取第一个（top）元素的值（每次只能取一个）先得判断栈是否空
    //切记，是从最上面开始取，因此是top--
    public int pop(){
        //先判断栈是否空
        if(isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据~");
        }
        int value = stack[top];//出栈就是获取到第一个（top）元素的值
        top--;//top下移
        return value;
    }

    //3.显示栈的情况-showStack[遍历栈]，注意：遍历时，需要从栈顶开始显示数据，先可单独判断栈是否空
    public void showStack(){
        if(isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        //从栈顶开始遍历，显示数据
        for (int i = top; i >= 0 ; i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}




//测试代码,不重要
public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试代码
        System.out.println("测试代码");
    }
}
