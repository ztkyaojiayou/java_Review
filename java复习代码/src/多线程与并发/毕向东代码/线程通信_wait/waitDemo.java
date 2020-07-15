package 多线程与并发.毕向东代码.线程通信_wait;



//wait 和 sleep 区别？
//
//        1，wait可以指定时间也可以不指定。
//        sleep必须指定时间。
//
//        2，在同步中时，对cpu的执行权和锁的处理不同。
//        wait：释放执行权，释放锁。
//        sleep:释放执行权，不释放锁。



class Demo
{

    void show() throws InterruptedException {
        synchronized(this)//
        {

            wait();//t0 t1 t2

        }
    }
    void method()
    {
        synchronized(this)//t4
        {

            //wait();

            notifyAll();


        }//t4
    }
}




public class waitDemo {

public static void main(String[] args)
{
    System.out.println("Hello World!");
}
}
