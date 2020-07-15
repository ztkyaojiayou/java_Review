package 多线程与并发.毕向东代码.多线程中的异常问题;


//线程中的异常问题
class Demo extends Thread
{
    private String name;
    Demo(String name)
    {
//		super(name);
        this.name = name;
    }
    public void run()
    {
        int[] arr = new int[3];
        System.out.println(arr[3]);//会报数组越界异常
        for(int x=0; x<10; x++)
        {
            System.out.println("....x="+x+".....name="+Thread.currentThread().getName());
        }
    }
}




public class ThreadExceptionDemo
{
    public static void main(String[] args)
    {
        Demo d1 = new Demo("小胡");
                Demo d2 = new Demo("xiaoqiang");
        d1.start();

        d2.start();

        System.out.println(4/0);//会报除零异常

        for(int x=0; x<20; x++)
        {
            System.out.println(x+"...."+Thread.currentThread().getName());
        }
    }
}

