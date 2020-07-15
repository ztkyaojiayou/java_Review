package 多线程与并发.毕向东代码.线程间的通信.等待唤醒机制_wait和notify;


/*
等待/唤醒机制（wait和notify/notifyAll）

涉及的方法：

1，wait(): 让线程处于冻结状态，被wait的线程会被存储到线程池中。
2，notify():唤醒线程池中一个线程(任意).
3，notifyAll():唤醒线程池中的所有线程。（推荐）

这些方法都必须定义在同步中。
因为这些方法是用于操作线程状态的方法。
必须要明确到底操作的是哪个锁上的线程。


为什么操作线程的方法wait notify notifyAll定义在了Object类中？

因为这些方法是监视器的方法。监视器其实就是锁，锁其实就是对象。
锁可以是任意的对象，任意的对象调用的方式一定定义在Object类中。

下面这个demo使用的是同步代码块保证了安全性问题，
且做到了先生产再消费的顺序性，因为使用线程间的通信，即wait和notify机制
*/

//共享资源
    class Resource02
    {
        String name;
        String sex;
        boolean flag = false;
    }


    //输入
    class Input02 implements Runnable
    {
        Resource02 r ;
        //	Object obj = new Object();
        Input02(Resource02 r)
        {
            this.r = r;
        }
        public void run()
        {
            int x = 0;
            while(true)
            {
                synchronized(r)
                {
                    if(r.flag)
                        try{r.wait();}catch(InterruptedException e){}
                    if(x==0)
                    {
                        r.name = "mike";
                        r.sex = "nan";
                    }
                    else
                    {
                        r.name = "丽丽";
                        r.sex = "女女女女女女";
                    }
                    r.flag = true;
                    r.notify();
                }
                x = (x+1)%2;

            }
        }
    }
    //输出
    class Output02 implements Runnable
    {
        Resource02 r;
        //	Object obj = new Object();
        Output02(Resource02 r)
        {
            this.r = r;
        }

        public void run()
        {
            while(true)
            {
                synchronized(r)
                {
                    if(!r.flag)
                        try{r.wait();}catch(InterruptedException e){}//让当前线程处于等待状态
                    System.out.println(r.name+"....."+r.sex);
                    r.flag = false;
                    r.notify();//随机唤醒一个线程，但一般不用notify，而是用notifyAll，否则有可能导致死锁（生产消费模型里面会讲）
                }
            }
        }
    }


    public class  ResourceDemo2 {
        public static void main(String[] args)
        {
            //创建资源。
            Resource02 r = new Resource02();
            //创建任务。
            Input02 in = new Input02(r);
            Output02 out = new Output02(r);
            //创建线程，执行路径。
            Thread t1 = new Thread(in);
            Thread t2 = new Thread(out);
            //开启线程
            t1.start();
            t2.start();
        }
    }
