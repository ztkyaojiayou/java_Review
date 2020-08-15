package 多线程与并发.毕向东代码.创建线程的两种方式;


/*

/*
进程：正在进行中的程序(直译).

线程：就是进程中一个负责程序执行的控制单元(执行路径)
一个进程中可以多执行路径，称之为多线程。

一个进程中至少要有一个线程。

开启多个线程是为了同时运行多部分代码。

每一个线程都有自己运行的内容。这个内容可以称为线程要执行的任务。

多线程好处：解决了多部分同时运行的问题。

多线程的弊端：线程太多回到效率的降低。


其实应用程序的执行都是cpu在做着快速的切换完成的。这个切换是随机的。


JVM启动时就启动了多个线程，至少有两个线程可以分析的出来。

1，执行main函数的线程，
		该线程的任务代码都定义在main函数中。

2，负责垃圾回收的线程。

如何创建一个线程呢？

创建线程方式一：继承Thread类。

步骤：
1，定义一个类继承Thread类。
2，覆盖Thread类中的run方法。
3，直接创建Thread的子类对象创建线程。
4，调用start方法开启线程并调用线程的任务run方法执行。

可以通过Thread的getName获取线程的名称 Thread-编号(从0开始)

主线程的名字就是main。
*/


class Demo01 extends Thread
{
    private String name;
    Demo01(String name)
    {
        super(name);
        //this.name = name;
    }
    public void run()
    {
        for(int x=0; x<10; x++)//d1和但d2这两个线程都会执行10次，且也都只会10次。只是执行顺序是随机的，此时并没有什么安全问题。
        {
            //for(int y=-9999999; y<999999999; y++){}
            System.out.println(Thread.currentThread().getName()+"....."+x);
        }
    }
}

public class ThreadDemo01
{
    public static void main(String[] args)
    {

		/*
		创建线程的目的是为了开启一条执行路径，去运行指定的代码和其他代码，实现同时运行。
		而运行的指定代码就是这个执行路径的任务。
		jvm创建的主线程的任务都定义在了主函数中。

		那么自定义的线程它的任务在哪儿呢？
		Thread类用于描述线程，线程是需要任务的。所以Thread类也是对任务的描述。
		这个任务就通过Thread类中的run方法来体现。
		也就是说，run方法就是封装自定义线程运行任务的函数。

		run方法中定义就是线程要运行的任务代码。
		开启线程是为了运行指定代码，所以只有继承Thread类，并复写run方法。
		将运行的代码定义在run方法中即可。

		*/
//
//		Thread t1 = new Thread();//这只是单纯的创建一个线程对象
        //创建两个线程
        Demo01 d1 = new Demo01("旺财");
        Demo01 d2 = new Demo01("xiaoqiang");
        //开启线程
        d1.start();//开启线程，调用run方法。
        d2.start();
        //因为多线程执行的随机性，因此主线程中的这一句并不一定是最后执行的
        System.out.println(Thread.currentThread().getName()+"....over");
    }
}
//调用run和调用start有什么区别？

