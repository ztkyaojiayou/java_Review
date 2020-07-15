package 多线程与并发.毕向东代码.线程安全问题_Synchronized关键字;

/*
静态的同步函数使用的锁是： 该函数所属字节码文件对象
可以用当前 类名.class 表示,也可以用 this.getClass方法获取
注意：意思也是一样的，即我们在静态方法上加上synchronized关键字之后，该方法就变成了线程安全的同步方法了，不需要我们做别的或胡思乱想了，因为只要加上该关键字，jvm就会为我们安排好一切。
我们只需要知道该同步方法使用的锁是什么就可以了，这样的话，当若需要使用同一把锁实现安全卖票时，就知道使用什么锁了。

下面使用同步代码块进行验证其持有的锁是否为类名.class
因为在同步代码块中可以明确指定某个锁
*/

class Ticket03 implements Runnable
{
    private static  int num = 100;
    	Object obj = new Object();
    boolean flag = true;
    public void run()
    {
//		System.out.println("this:"+this.getClass());

        if(flag)
            while(true)
            {
                synchronized(Ticket03.class)//(this.getClass()),此时当前两个线程持有的锁是一致的，因此不会出现安全性问题，这也验证了静态同步方法持有的锁就是：当前类名.class
                //synchronized(obj)//此时由于两个线程持有的锁不一致，因此会出现安全性问题，卖出0号票
                {
                    if(num>0)
                    {
                        try{Thread.sleep(10);}catch (InterruptedException e){}
                        System.out.println(Thread.currentThread().getName()+".....obj...."+num--);
                    }
                }
                //若不使用同步代码块，则可以写成如下形式，即都使用静态同步方法，则肯定是使用的同一把锁，则也不会出现安全性问题，
                //上述只是为了验证静态同步方法的锁到底是什么而已。
//                while(true)
//                    this.show();
            }
        else
            while(true)
                this.show();
    }

    //静态同步方法，其锁为类名.class，即Ticket03.class
    public static synchronized void show()
    {
        if(num>0)
        {
            try{Thread.sleep(10);}catch (InterruptedException e){}

            System.out.println(Thread.currentThread().getName()+".....function...."+num--);
        }
    }
}

public class SynchronizedDemo3 {
    public static void main(String[] args)
    {
        Ticket03 t = new Ticket03();

//		Class clazz = t.getClass();
//
//		Class clazz = Ticket03.class;
//		System.out.println("t:"+t.getClass());

        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);

        t1.start();
        try{Thread.sleep(10);}catch(InterruptedException e){}
        t.flag = false;
        t2.start();
    }
}
