package 多线程与并发.毕向东代码.生产消费模型.使用lock中的Codition实现_升级版;

/*
jdk1.5以后将同步和锁封装成了对象。
并将操作锁的隐式方式定义到了该对象中，
将隐式动作变成了显示动作。

Lock接口： 替代了同步代码块或者同步函数。将同步的隐式锁操作变成现实锁操作。
同时更为灵活。可以一个锁上加上多组监视器。

（1）线程安全性保证：使用lock锁接口中的lock和unlock主动加锁和释放锁来实现
lock():获取锁。
unlock():释放锁，通常需要定义finally代码块中。

（2）生产消费顺序的保证：
Condition接口：替代了Object中的wait notify notifyAll方法。
			将这些监视器方法单独进行了封装，变成Condition监视器对象。
			可以和任意锁进行组合，即一个锁可以对于多个Condition监视器对象，
			于是就可以分别使用其await、signal和signalAll方法进行精确等待和唤醒了，而不会产生死锁了，因为全程都可以只建立一个锁对象。
			（而在以前的同步代码块中，一个锁对象就只能使用一套wait、notify和notifyAll方法，
			不能进行区分和精确等待和唤醒，若想对不同的锁对象进行使用，就必须再建立锁，即再加一个同步代码块或同步方法，则很容易产生死锁现象）
await();等待
signal();唤醒某一个线程，可精准唤醒，则可唤醒对方的线程，贼爽
signalAll();，唤醒全部

本Demo使用lock中的await和signal/signalAll机制实现：
*/

import java.util.concurrent.locks.*;

//共享资源，里面包含了生产和消费的具体代码，但是是通过lock主动加锁来保证线程的安全性
class Resource06 {

    private String name;
    private int count = 1;
    private boolean flag = false;//保证生产消费顺序性的标记

    //	创建一个锁对象。
    Lock lock = new ReentrantLock();//可重入锁，是lock接口的一个具体实现类

    //通过已有的锁获取该锁上的监视器对象，使用newCondition方法。（类比synchronized中的锁/monitor）

    //通过已有的锁获取两组监视器，一组监视生产者，一组监视消费者。这才是它的优势
    //Condition condition = lock.newCondition();若只用一个condition，则其实和同步代码块中的wait，notify以及notifyAll是一样的，
    //并不能精确等待和唤醒，此时在唤醒时也必须用signalAll
    Condition producer_con = lock.newCondition();
    Condition consumer_con = lock.newCondition();

//1.生产的具体代码
    public  void set(String name)//  t0 t1
    {
        lock.lock();//主动加锁（可重入锁）
        try
        {
            while(flag)
                //使用生产者的监视器对象producer_con的await来等待，谁等待就用谁的监视器来调用该方法即可，通俗易懂，也很好操作。
//			try{线程安全问题_lock锁接口.wait();}catch(InterruptedException e){}//   t1    t0
                try{producer_con.await();}catch(InterruptedException e){}//   t1    t0

            this.name = name + count;//烤鸭1  烤鸭2  烤鸭3
            count++;//2 3 4
            System.out.println(Thread.currentThread().getName()+"...生产者5.0..."+this.name);//生产烤鸭1 生产烤鸭2 生产烤鸭3
            flag = true;
//			notifyAll();
//			con.signalAll();
            consumer_con.signal();//通过该语句可以唤醒绑定在消费者监视器上的所有线程，
            // 易知，这是可以精准唤醒的，想唤醒谁就通过谁的监视器来调用该方法即可，且易知，可不需要使用signalAll
        }
        finally
        {
            lock.unlock();//主动释放锁，一定要在finally中，因为锁是必须要释放的
        }

    }
    //2.生产的具体代码，同理
    public  void out()// t2 t3
    {
        lock.lock();//主动加锁（可重入锁）
        try
        {
            while(!flag)
                //使用消费者的监视器对象consumer_con的await来等待
//			try{this.wait();}catch(InterruptedException e){}	//t2  t3
                try{consumer_con.await();}catch(InterruptedException e){}	//t2  t3
            System.out.println(Thread.currentThread().getName()+"...消费者.5.0......."+this.name);//消费烤鸭1
            flag = false;
//			notifyAll();
//			con.signalAll();
            producer_con.signal();
        }
        finally
        {
            lock.unlock();//主动释放锁，一定要在finally中，因为锁是必须要释放的
        }

    }
}

//1.先生产
class Producer02 implements Runnable
{
    private Resource06 r;
    Producer02(Resource06 r)
    {
        this.r = r;
    }
    public void run()
    {
        while(true)
        {
            r.set("烤鸭");//调用生产者的具体方法
        }
    }
}

//2.再消费
class Consumer02 implements Runnable
{
    private Resource06 r;
    Consumer02(Resource06 r)
    {
        this.r = r;
    }
    public void run()
    {
        while(true)
        {
            r.out();//调用消费者的具体方法
        }
    }
}


//创建线程，开始生产和消费
public class  ProducerConsumerDemo2
{
    public static void main(String[] args)
    {
        Resource06 r = new Resource06();
        Producer02 pro = new Producer02(r);
        Consumer02 con = new Consumer02(r);

        //t0和t1生产，t2和t3消费
        Thread t0 = new Thread(pro);
        Thread t1 = new Thread(pro);
        Thread t2 = new Thread(con);
        Thread t3 = new Thread(con);
        t0.start();
        t1.start();
        t2.start();
        t3.start();

    }
}


