package 多线程与并发.叶子猿代码.多线程基础.如何使多个线程按照顺序执行;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//用Condition来指定唤醒某一个线程,解决多线程按照顺序执行的问题

class A2 implements Runnable {

    private Demo02 demo;

    public A2(Demo02 demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        while(true) {
            demo.a();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
class B2 implements Runnable {

    private Demo02 demo;

    public B2(Demo02 demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        while(true) {
            demo.b();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
class C2 implements Runnable {

    private Demo02 demo;

    public C2(Demo02 demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        while(true) {
            demo.c();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

public class Demo02 {

    private int signal;////定义一个标记，用于条件的判断（和之前的Demo是一样的），初始值为0
    // 与signal()方法是两码事，千万不要误会

    Lock lock = new ReentrantLock();//创建一个显示锁
    //再在此锁上绑定三个Condition对象，就可以分别在不同的线程上调用了,太好用了
    Condition a = lock.newCondition();
    Condition b = lock.newCondition();
    Condition c = lock.newCondition();


    public void a() {
        lock.lock();//上锁
        while(signal != 0 ) {
            try {
                a.await();//让a线程等待，这里的await用于代替之前的wait方法，只不过这个方法可以指定哪个线程等待，谁调用谁就等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("a");
        signal ++;//加1，变成1
        b.signal();//唤醒b线程。该方法就是用来代替之前的notify方法的，其优势就是可以唤醒某一个指定线程
        lock.unlock();//释放锁
    }

    public  void b() {
        lock.lock();
        while(signal != 1) {
            try {
                b.await();//让b线程等待
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("b");
        signal ++;//加1，变成2
        c.signal();//唤醒c线程
        lock.unlock();
    }

    public  void c () {
        lock.lock();
        while(signal != 2) {
            try {
                c.await();//让c线程等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("c");
        signal = 0;//又重新设置为0，用于循环顺序打印
        a.signal();//再次唤醒a线程
        lock.unlock();
    }

    public static void main(String[] args) {
        //创建三个线程并启动
        Demo02 d = new Demo02();
        A2 a21 = new A2(d);
        B2 b21 = new B2(d);
        C2 c21 = new C2(d);

        new Thread(a21).start();
        new Thread(b21).start();
        new Thread(c21).start();

    }
}


