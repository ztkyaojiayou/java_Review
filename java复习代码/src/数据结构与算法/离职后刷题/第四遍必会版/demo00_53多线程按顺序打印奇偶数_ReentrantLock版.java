package 数据结构与算法.离职后刷题.第四遍必会版;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class demo00_53多线程按顺序打印奇偶数_ReentrantLock版 {
    public static int number = 0;//共享资源

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        //类似于synchronized，也只需用一个condition既可
        Condition condition = lock.newCondition();
        //线程1，打印奇数，此时为偶数时，就得等
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (number < 100) {//目的是打印100以内的数
                    //加锁
                    lock.lock();// 相当于synchronized (object)
                    try {
                        if (number % 2 == 1) {//奇数，则打印，同时唤醒另一个线程
                            System.out.println(number);
                            number++;
                            //打印完就唤醒另一个线程
                            condition.signal();
                        } else {//偶数，则等待
                            try {
                                condition.await();
                            } catch (Exception e) {

                            }
                        }
                    } finally {
                        //释放锁
                        lock.unlock();//相当于synchronized中的代码块执行完毕后自动释放锁
                    }

                }
            }
        });

        //线程2，打印偶数，逻辑同上
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (number < 100) {
                    //加锁
                    lock.lock();
                    try {
                        if (number % 2 == 0) {//偶数
                            System.out.println(number);
                            number++;
                            //打印完就唤醒了一个线程
                            condition.signal();
                        } else {//此时为奇数时，就得等
                            try {
                                //等待
                                condition.await();
                            } catch (Exception e) {

                            }
                        }
                    } finally {
                        //释放锁
                        lock.unlock();
                    }
                }
            }
        });

        //开启线程
        t1.start();
        t2.start();
        //作用：先线程1，后线程2
        try {
            t1.join();
            t1.join();
        } catch (Exception e) {

        }
    }
}
