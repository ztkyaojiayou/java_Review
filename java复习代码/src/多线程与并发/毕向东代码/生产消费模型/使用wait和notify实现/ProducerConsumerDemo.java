package 多线程与并发.毕向东代码.生产消费模型.使用wait和notify实现;


/*
生产者消费者模型。
即多个线程执行同一资源，只是任务不同，关键问题是要保证生产了就先消费掉，再去生产；而不是先积压库存后消费。
（其实就是线程之间的通信，之前有重复，见“线程间的通信”包。）

场景：两个生产者，两个消费者，边生产边消费

1、方案：
（1）使用同步方法来保证线程的安全性（隐式锁，即加锁和释放锁在虚拟机内执行，我们看不到）

（2）使用wait和notify机制来保证生产和消费的顺序性：
notify:只能唤醒一个线程，如果本方唤醒了本方，没有意义。而且使用“while判断标记+notify”模式会导致程序被挂起，暂停运行。它适用于单生产单消费的场景。
notifyAll则解决了本方线程一定会唤醒对方线程的问题，在多生产多消费场景中，只能使用它。

2、相关问题：
（1）为什么要使用while判断标记呢？
因为if判断标记时，只有一次，会导致不该运行的线程运行了。出现了数据错误的情况，即有可能出现：生产了两次，但只消费了一次，或者相反。
而while判断标记时，会多次判断，会判断当线程获取执行权后，是否要运行，从而避免安全问题！

（2）唤醒时，为什么不用notify而要用notifyAll？（划重点：此时是生产者和消费者各2个，而不是1个）
因为notify只随机唤醒线程池中正在等待的某一个线程（但一般都是唤醒最先等待的那个线程），
因此有可能唤醒的是自己方的本应该等待的线程，而没有唤醒对方的线程（则对方线程还处于等待状态），
则本方被唤醒的线程再判断一下标记后又得继续等待，于是就造成了所有线程都在等待，从而使程序暂停。
而使用notifyAll则不会出现这样的问题，因为它是唤醒所有线程，这样就不会出现问题了，因为至少会把对方线程唤醒，
此时即便把自己方的线程唤醒也没事，因为唤醒只会还需要判断一下标记，则该怎么样就怎么样，我们的目的也就是要把对方线程唤醒。

注意：易知，若生产者消费者各只有一个时，则使用notify肯定会把对方线程唤醒，即此时不会出现该问题。
*/
//资源类，里面有同步的生产和消费的具体方法，用以保证线程安全
class Resource04
{
    private String name;
    private int count = 1;
    private boolean flag = false;

    /**
     * 注意：本来消费者和生产者线程是相互交替执行的，
     * 我们是不能确定谁先执行谁后执行的，但我们可以假设先生产，再消费来写代码和理解代码
     */

    //1.生产的具体实现   t1   t0
    public synchronized void set(String name)//同步方法，因此锁为this
    {
        //3.最后，自己等，等消费完毕并唤醒我，我再开始新一轮生产
        while(flag)//注意：由于一开始是false，则不会执行此等待操作，而要等先生产完修改了标记之后才会进入等待状态
            try{this.wait();}catch(InterruptedException e){}//   t1   t0
        //1.先生产并修改标记（此时消费者都在等待）
        this.name = name + count;//烤鸭1  烤鸭2  烤鸭3
        count++;//2 3 4
        System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);//生产烤鸭1 生产烤鸭2 生产烤鸭3
        flag = true;//修改标记（供消费者使用）
        //2.再唤醒全部消费者开始消费(如果有在等待的话，自己也有可能被唤醒，这就是一个弊端）
        //notify();//会出现唤醒本方的线程，从而使得所有线程均处于等待状态，程序被挂起，暂停运行
        notifyAll();//唤醒所有线程，这样就不会出现问题了，因为唤醒只会还需要判断一下标记，则该怎么样就怎么样。
    }

    //2.消费的具体实现  t2  t3
    public synchronized void out()
    {
        //1.先自己等，等生产者生产完毕并唤醒我，我再开始消费
        while(!flag)//注意：由于一开始是false，则这里为true，则会先等，等生产者生产完毕同时修改标记后就会变为false，则开始执行消费代码
            try{this.wait();}catch(InterruptedException e){}	//t2  t3
        //2.开始消费并修改标记（此时生产者都在等待）
        System.out.println(Thread.currentThread().getName()+"...消费者........"+this.name);//消费烤鸭1
        flag = false;//修改标记（供生产者使用）
        //3.再唤醒全部生产者（如果有在等待的话，自己也有可能被唤醒，这就是一个弊端）进行下一轮生产
        //notifyAll();
        notify();
    }
}

//先生产者
class Producer implements Runnable
{
    private Resource04 r;
    Producer(Resource04 r)//构造方法
    {
        this.r = r;
    }
    public void run()
    {
        while(true)
        {
            r.set("烤鸭");//调用同步方法
        }
    }
}

//后消费
class Consumer implements Runnable
{
    private Resource04 r;
    Consumer(Resource04 r)//构造方法
    {
        this.r = r;
    }
    public void run()
    {
        while(true)
        {
            r.out();//调用同步方法
        }
    }
}


//创建4个线程并执行
public class  ProducerConsumerDemo
{
    public static void main(String[] args)
    {
        Resource04 r = new Resource04();
        Producer pro = new Producer(r);//生产者
        Consumer con = new Consumer(r);//消费者
        //生产
        Thread t0 = new Thread(pro);
        Thread t1 = new Thread(pro);
        //消费
        Thread t2 = new Thread(con);
        Thread t3 = new Thread(con);
        //开始执行
        t0.start();
        t1.start();
        t2.start();
        t3.start();

    }
}


