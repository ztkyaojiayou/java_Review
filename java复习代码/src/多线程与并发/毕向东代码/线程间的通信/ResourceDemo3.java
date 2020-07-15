package 多线程与并发.毕向东代码.线程间的通信;


//下面的demo也是一个生产消费的例子，但是是使用的同步方法来解决安全问题，
//且也做到了先生产再消费的顺序性，因为使用线程间的通信，即wait和notify机制
class Resource03
{
    private String name;
    private String sex;
    private boolean flag = false;//标记，对执行顺序很重要

    //先输入
    public synchronized void set(String name,String sex)//使用同步方法，刚才是使用的同步代码块
    {
        if(flag)//2.一开始是false，输入后就为true，于是等待（等待输出线程唤醒，再进行下一次的输入）
            try{this.wait();}catch(InterruptedException e){}
        this.name = name;
        this.sex = sex;
        flag = true;//输入结束就设置为true
        this.notify();//1.输入结束，唤醒输出线程（而不是本线程，本线程又没有等待！！！）
    }

    //后输出，则先使用wait等待输入结束
    public synchronized void out()//使用同步方法，刚才是使用的同步代码块
    {
        if(!flag)//2.一进来就是false（因为输入完毕后设置为了true），于是先等待输入结束并唤醒该线程
            //或者，输出线程刚进来先执行，发现原flag为true，则此时为false，于是等待，等待输入结束并唤醒该线程进行输出
            try{this.wait();}catch(InterruptedException e){}
        System.out.println(name+"...+...."+sex);
        flag = false;
        this.notify();//3.已经输出完毕，唤醒输入线程（而不是本线程，本线程不是好好的嘛！！！）进行下一轮的输入
    }
}


//输入
class Input03 implements Runnable
{
    Resource03 r ;
    //	Object obj = new Object();
    Input03(Resource03 r)
    {
        this.r = r;
    }
    public void run()
    {
        int x = 0;
        while(true)
        {
            if(x==0)
            {
                r.set("mike","nan");//执行同步方法
            }
            else
            {
                r.set("丽丽","女女女女女女");//执行同步方法
            }
            x = (x+1)%2;
        }
    }
}
//输出
class Output03 implements Runnable
{

    Resource03 r;
    //	Object obj = new Object();
    Output03(Resource03 r)
    {
        this.r = r;
    }

    public void run()
    {
        while(true)
        {
            r.out();//执行同步方法
        }
    }
}



public class  ResourceDemo3
{
    public static void main(String[] args)
    {
        //创建资源。
        Resource03 r = new Resource03();
        //创建任务。
        Input03 in = new Input03(r);
        Output03 out = new Output03(r);
        //创建线程，分别执行不同的任务。
        Thread t1 = new Thread(in);
        Thread t2 = new Thread(out);
        //开启线程
        t1.start();
        t2.start();
    }
}

