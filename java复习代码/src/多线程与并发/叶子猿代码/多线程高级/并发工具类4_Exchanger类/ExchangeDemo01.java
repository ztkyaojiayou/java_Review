package 多线程与并发.叶子猿代码.多线程高级.并发工具类4_Exchanger类;


import java.util.concurrent.Exchanger;


//并发工具类4：Exchanger类（交换类）的使用（也很简单，但没太懂）

//是什么：Exchanger是两个任务之间交换对象的栅栏（汇合点），可以在两个线程之间交换数据(注意：但只能是2个线程，他不支持更多的线程之间互换数据）。
//当这些任务进入栅栏时，各自拥有一个对象，当它们离开时，它们都拥有之前由对象持有的对象。
//当线程A调用Exchange对象的exchange()方法后，他会陷入阻塞状态，等待另一个线程到达交换点（如果当前线程没有被中断），
// 直到线程B也调用了exchange()方法，然后将已知的对象传给对方，返回接收的对象。
//如果另外一个线程已经在交换点等待，那么恢复线程计划并接收通过当前线程传给的对象，
//然后以线程安全的方式交换数据，之后线程A和B继续运行

//(说人话）可简单地将Exchanger对象理解为一个包含两个格子的容器，通过exchanger方法可以向两个格子中填充信息。
//当两个格子均被填充时，该对象会自动将两个格子的信息交换，然后返回给线程，从而实现两个线程的信息交换。

//注意：该类只适用于两个线程，此时肯定知道对方线程是谁，会一直等它到来。

//需求（使用场景）：A线程和B线程交换数据并进行比对，看其数据是否相同

public class ExchangeDemo01 {

    public void a (Exchanger<String> exch) {//该方法传入了一个Exchanger对象

        System.out.println("a 方法执行...");

        try {
            System.out.println("a 线程正在抓取数据...");
            Thread.sleep(2000);
            System.out.println("a 线程抓取到数据...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String res1 = "12345";

        try {
            System.out.println("a 等待对比结果...");
            /*
             *  （关键）在此处等待另外一个线程到来，并进行数据交换，如果没有另一个线程到来，那么当前这个线程会处于休眠状态，直到3件事情发生：
             *  1、等待另一个线程到达交换点（重点）
             *  2、被另一个线程中断
             *  3、等待超时，当调用exchanger.exchange(x, timeout, unit)方法时有效
             */
            String value1 = exch.exchange(res1);//这句话的意思是说：用自己的res1去和另外一个线程的res2进行交换，得到的value1是res2的值
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void b (Exchanger<String> exch) {//同理，该方法传入了一个Exchanger对象
        System.out.println("b 方法开始执行...");
        try {
            System.out.println("b 方法开始抓取数据...");
            Thread.sleep(4000);//同理，会等对方的到来
            System.out.println("b 方法抓取到数据...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String res2 = "1234";

        try {
            String value2 = exch.exchange(res2);//这句话的意思是说：用自己的res2去和另外一个线程的res1进行交换，得到的value2是res1
            System.out.println("开始进行比对...");
            System.out.println("比对结果为：" + value2.equals(res2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ExchangeDemo01 d = new ExchangeDemo01();
        Exchanger<String> exch = new Exchanger<>();//创建一个Exchanger类的实例对象
        new Thread(new Runnable() {//创建线程A
            @Override
            public void run() {
                d.a(exch);
            }
        }).start();

        new Thread(new Runnable() {//创建线程B
            @Override
            public void run() {
                d.b(exch);
            }
        }).start();

    }

}

