package 多线程与并发.毕向东代码.线程安全问题_Synchronized关键字;



/*
同步函数的使用的锁是this；
这意味着什么呢？
首先，同步方法就意味着它可以保证线程安全，只需在该方法上加上synchronized关键字即可，则它就有一个锁，这个锁就是this
但是对于一个卖票程序，由于有共享资源，如果多个线程使用的锁不一样，则即便每个线程是安全的，但对于整个结果而言依然是有可能不安全的
比如如下的demo，两个线程发分别用同步代码块和同步方法实现各自的安全性，但是若分别使用的是不同的锁，则最终结果也会出现问题。
比如，若同步代码块中使用obj锁，而同步方法使用的是this（不可变），则最终会出现买了0号票，这显然是错误的
只有当同步代码块中也使用this锁时才会保证真正的安全性。（因为同步方法的锁是固定的，因此只能修改同步代码块中的锁）
注意：同步代码块中可以放任意对象，当然也就包括this了，这个this对象就是它的锁，务必明确这一点。

同步函数和同步代码块的区别：
同步函数的锁是固定的this对象。
同步代码块的锁是任意的对象。

建议使用同步代码块。
*/
class Ticket04 implements Runnable
{
    private  int num = 100;
    //Object obj = new Object();
    boolean flag = true;
    public void run()
    {
        System.out.println("this:"+this);

        if(flag)
            while(true)
            {
                synchronized(this)//这里人为地使用this锁，为的就是使两个线程的锁一致
                // 若为obj锁，则会由于两个线程的锁不一致而导致最终会出现安全性问题（卖出0号票）
                {
                    if(num>0)
                    {
                        try{Thread.sleep(10);}catch (InterruptedException e){}
                        System.out.println(Thread.currentThread().getName()+".....obj...."+num--);
                    }
                }
            }
        else
            while(true)
                this.show();//该代码的意思是调用show这个同步方法，因为同步方法的锁就是this对象
                //show();其实不写this也是可以的
        //System.out.println("this:"+this);this:ztk.多线程与并发.毕向东代码.线程安全问题_Synchronized关键字.Ticket04@610455d6
        //注意：this一般就是用于指代该类的一个实例对象，和主方法main中的对象t是相同的，这样就清楚多了。
    }

    public synchronized void show()//同步方法，它持有的锁为this，且不可变
    {
        if(num>0)
        {
            try{Thread.sleep(10);}catch (InterruptedException e){}

            System.out.println(Thread.currentThread().getName()+".....function...."+num--);
        }
    }
}

public class SynchronizedDemo1
{
    public static void main(String[] args)
    {
        //这个对象t和this是同一个对象
        Ticket04 t = new Ticket04();
		//System.out.println("t:"+t);//t:ztk.多线程与并发.毕向东代码.线程安全问题_Synchronized关键字.Ticket04@610455d6

        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);

        t1.start();
        try{Thread.sleep(10);}catch(InterruptedException e){}
        t.flag = false;//修改标记，给show方法执行的机会
        t2.start();
    }
}

