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
    private int flag;//定义一个标记，用于条件的判断（和之前的Demo是一样的），初始值为0
    Lock lock = new ReentrantLock();//创建一个显示锁
    //再在此锁上绑定三个Condition对象，就可以分别在不同的线程上调用了,太好用了
    Condition con1 = lock.newCondition();
    Condition con2 = lock.newCondition();
    Condition con3 = lock.newCondition();

    //方法1（相当于线程1）
    public void a() {
        lock.lock();//上锁
        while(flag != 0 ) {
            try {
                con1.await();//让a线程等待，这里的await用于代替之前的wait方法，只不过这个方法可以指定哪个线程等待，谁调用谁就等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("a");
        flag++;//加1，变成1
        con2.signal();//唤醒b线程。该方法就是用来代替之前的notify方法的，其优势就是可以唤醒某一个指定线程
        lock.unlock();//释放锁
    }
    //方法2（相当于线程2）
    public void b() {
        lock.lock();
        while(flag != 1) {
            try {
                con2.await();//让b线程等待
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("b");
        flag++;//加1，变成2
        con3.signal();//唤醒c线程
        lock.unlock();
    }

    //方法3（相当于线程3）
    public  void c () {
        lock.lock();
        while(flag != 2) {
            try {
                con3.await();//让c线程等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("c");
        flag = 0;//又重新设置为0，用于循环顺序打印
        con1.signal();//再次唤醒a线程
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


