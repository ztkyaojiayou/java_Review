package 多线程与并发.叶子猿代码.多线程基础.如何使多个线程按照顺序执行;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class demo06 {
        public static int number = 0;
        public static void main1(String[] args) {
            ReentrantLock reentrantLock = new ReentrantLock();
            Condition condition1 = reentrantLock.newCondition();
            //线程1，打印奇数，此时为偶数时，就得等
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (number < 100) {
                        reentrantLock.lock();
                        if (number % 2 == 1) {//奇数
                            System.out.println(Thread.currentThread().getId() + " : " + number);
                            number++;
                            //打印完就唤醒了一个线程
                            condition1.signal();
                        } else {//偶数
                            try {
                                //等待
                                condition1.await();
                            } catch (Exception e) {

                            }
                        }
                    }
                }
            });

            //线程2，打印偶数
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (number < 100) {
                        reentrantLock.lock();
                        if (number % 2 == 0) {//偶数
                            System.out.println(Thread.currentThread().getId() + " : " + number);
                            number++;
                            //打印完就唤醒了一个线程
                            condition1.signal();
                        } else {//此时为奇数时，就得等
                            try {
                                //等待
                                condition1.await();
                            } catch (Exception e) {

                            }
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
