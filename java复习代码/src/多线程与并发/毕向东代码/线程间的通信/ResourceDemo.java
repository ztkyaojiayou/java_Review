package 多线程与并发.毕向东代码.线程间的通信;


/*
线程间通讯：
即多个线程在处理同一资源（即共享资源），但是任务却不同。

下面的demo是一个生产消费的例子，使用了同步代码块保证了线程安全问题，
不过没有做到先生产再消费，而是混乱的，因为没有使用线程间的通信。
*/

//共享资源
class Resource01
{
    String name;
    String sex;
}


//输入
class Input01 implements Runnable
{
    Resource01 r ;
    //	Object obj = new Object();
    Input01(Resource01 r)
    {
        this.r = r;
    }
    public void run()
    {
        int x = 0;
        while(true)
        {
            synchronized(r)//一般就是用的指定资源对象作为锁，保证安全性
            {
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
                x = (x+1)%2;
            }
            //若不加synchronized(r)，则会出现安全问题，因为是多线程在处理同一个共享资源。
//            if(x==0)
//            {
//                r.name = "mike";
//                r.sex = "nan";
//            }
//            else
//            {
//                r.name = "丽丽";
//                r.sex = "女女女女女女";
//            }
//            x = (x+1)%2;

        }
    }
}
//输出
class Output01 implements Runnable
{

    Resource01 r;
    //	Object obj = new Object();
    Output01(Resource01 r)
    {
        this.r = r;
    }

    public void run()
    {
        while(true)
        {
            synchronized(r)//同理，保证线程安全性
            {
                System.out.println(r.name+"....."+r.sex);
            }
        }
    }
}



public class  ResourceDemo
{
    public static void main(String[] args)
    {
        //创建资源
        Resource01 r = new Resource01();
        //创建任务
        Input01 in = new Input01(r);
        Output01 out = new Output01(r);
        //创建线程，执行任务（任务各不相同，但使用的资源却相同）
        Thread t1 = new Thread(in);
        Thread t2 = new Thread(out);
        //开启线程
        t1.start();
        t2.start();
    }
}

