package 多线程与并发.毕向东代码.死锁;



/*
死锁：常见情景之一：同步的嵌套。
原因：同步中嵌套同步，但持有的锁不同
但要注意：这种情况只是可能出现死锁现象，但也有可能是正常的

*/
class Ticket implements Runnable
{
    private  int num = 100;
    Object obj = new Object();
    boolean flag = true;
    public void run()
    {


        if(flag)
            while(true)
            {
                //同步中嵌套同步，你不让我，我也不让你
                synchronized(obj)//同步代码块
                {
                    show();//同步方法
                }
            }
        else
            while(true)
                this.show();
    }
    //同理，同步中嵌套同步，你不让我，我也不让你
    public synchronized void show()//同步方法
    {

        synchronized(obj)//同步代码块
        {
            if(num>0)
            {
                try{Thread.sleep(10);}catch (InterruptedException e){}

                System.out.println(Thread.currentThread().getName()+".....sale...."+num--);
            }
        }
    }
}


public class DeadLockDemo
{
    public static void main(String[] args)
    {
        Ticket t = new Ticket();
//		System.out.println("t:"+t);

        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);

        t1.start();
        try{Thread.sleep(10);}catch(InterruptedException e){}
        t.flag = false;
        t2.start();
    }
}

