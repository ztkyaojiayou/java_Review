package 多线程与并发.叶子猿代码.多线程基础.Lock接口_显示锁;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//1.lock显示锁 接口的使用
//同样用于解决安全性问题
//案例场景：三个线程同时对value执行加一操作，保证顺序不乱套。
//这和和卖票是一个道理，是典型的多线程安全问题
public class LockDemo {

    private int value;
    Lock lock = new ReentrantLock();//可重入锁，lock接口的一个具体实现

    /**
     * 关键部分：即把可能会产生安全性问题的代码块（这里是int a = value ++;）锁起来
     */
    public  int getNext() {
        lock.lock();//主动上锁
        int a = value ++;
        lock.unlock();//主动释放锁
        return a;
    }
//创建三个线程同时去调用getNext方法
//此时不会产生安全性问题
    public static void main(String[] args) {

        LockDemo s = new LockDemo();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

}

