package 多线程与并发.毕向东代码.死锁;

//也是同步中嵌套同步，只是这个例子更纯粹，务必掌握
//都是使用的同步代码块
class Test implements Runnable
{
    private boolean flag;
    Test(boolean flag)
    {
        this.flag = flag;
    }

    public void run()
    {

        if(flag)
        {
            while(true)
                //同步代码块中嵌套同步代码块，但持有的锁不同
                synchronized(MyLock.locka)//持有了a锁，
                {
                    System.out.println(Thread.currentThread().getName()+"..if   locka....");
                    synchronized(MyLock.lockb)//但想去访问b锁
                    {

                        System.out.println(Thread.currentThread().getName()+"..if   lockb....");
                    }
                }
        }
        else
        {
            while(true)
                synchronized(MyLock.lockb)//持有了b锁
                {
                    System.out.println(Thread.currentThread().getName()+"..else  lockb....");
                    synchronized(MyLock.locka)//但想去访问a锁
                    {
                        System.out.println(Thread.currentThread().getName()+"..else   locka....");
                    }
                }
        }

    }

}

class MyLock
{
    public static final Object locka = new Object();
    public static final Object lockb = new Object();
}




public class DeadLockTest
{
    public static void main(String[] args)
    {
        Test a = new Test(true);
        Test b = new Test(false);

        Thread t1 = new Thread(a);
        Thread t2 = new Thread(b);
        t1.start();
        t2.start();
    }
}

