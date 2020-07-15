package 多线程与并发.叶子猿代码.多线程高级.并发工具类3_Semaphore类;


import java.util.concurrent.Semaphore;

//并发工具类3：Semaphore类（信号类）的使用（其实很简单）

//是什么：在许可可用前会阻塞每一个 acquire()，然后再获取该许可。每个 release() 添加一个许可，从而可能释放一个正在阻塞的获取者。
//但是，不使用实际的许可对象，Semaphore 只对可用许可的号码进行计数，并采取相应的行动

//（说人话）Semaphore 就相当于是公共汽车上的售票员，它提供了一个许可证的概念，可以把这个许可证看作公共汽车车票，只有成功获取车票的人才能够上车，
//并且车票是有一定数量的，不可能毫无限制的发下去，否则就会导致公交车超载。
//所以当售票员把车票卖完的时候 ( 公交车已满载 ) ，其他人就只能等下一趟车了。
//如果中途有人下车，那么他的位置将会空闲出来，因此如果这时其他人想要上车的话就又可以获得车票了。

//使用场景：创建无数个线程，但真正执行的线程数由我们自己控制（只有获取许可的线程才可以正常执行），
//其他线程均处于等待状态,当此许可证（车票）被释放时，则下一批线程又可以获得此许可证（车票）而执行，
//以此类推，相当于是一批一批的在执行
public class Demo {

    //4.method方法，该方法传入了一个Semaphore对象
    public void method (Semaphore semaphore) {

        try {
            //acquire()用于请求信号，每调用一次，信号量便少一个。信号量用完以后，后续使用acquire()方法请求信号的线程便会加入阻塞队列挂起。
            semaphore.acquire();//从此信号量（售票员）获取一个许可（车票），在提供一个许可前一直将线程阻塞（等待），否则线程被中断。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " is run ...");

        try {
            Thread.sleep(2000);//相当于第一批与第二批线程执行的时间差，这里会分5批运行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //release()用于释放信号，调用一次信号量加一个。
        semaphore.release();//释放一个许可（车票），将其返回给信号量，此时其他线程又可以进来执行了，每批十个线程（但具体是哪十个则是随机的）。
    }


    public static void main(String[] args) {
        //在 java1.8 之前，匿名内部类在使用外部成员的时候要加上final关键字
        //否则会报错并提示 “Cannot refer to a non-final variable arg inside an inner class defined in a different method”
        int i;
        Demo d = new Demo();
        //1.创建具有给定的许可证（和非公平的公平设置）的 Semaphore
        Semaphore semaphore = new Semaphore(10);//这里设置为10，表示只有10个线程在真正运行，其他线程全部处于等待状态。

     for (i=0;i<50;i ++) {
    new Thread(new Runnable() {//2.创建多个（50）线程（但由于使用了Semaphore并发工具类，则只有10个线程在真正运行，其他线程全部处于等待状态）
        @Override
        public void run() {
            d.method(semaphore);//3.执行method(semaphore)方法，但只有获取了许可证（车票）的线程才可以正常执行
            // try {
            // Thread.sleep(100);
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }
        }
    }).start();
     }
        }

    }


