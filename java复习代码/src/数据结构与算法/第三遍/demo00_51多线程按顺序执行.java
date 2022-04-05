package 数据结构与算法.第三遍;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 方法1：使用join，最简单
 * @author zoutongkun
 */
//参考链接：https://www.cnblogs.com/svennee/p/4081155.html（关于如何实现多个线程并发运行）
public class demo00_51多线程按顺序执行 {
    public static void main(String[] args) {
        //线程1
        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("A");
            }
        });

        //线程2
        final Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B");
            }
        });


        //线程3
        final Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("C");
            }
        });
        //启动这三个线程
        t1.start();
        t2.start();
        t3.start();
    }
}

/**
 * 方法2：使用synchronized + wait + notify
 */
class Demo02 {

    private int flag;//定义一个标记，用于条件的判断，初始值为0
    /**
     * 方法1（相当于线程1）
     */
    public synchronized void a() {//同步方法,锁为this
        while (flag != 0) {//不为0则等待，等于0时才执行
            try {
                this.wait();//当前线程等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //等于0时就执行，同时唤醒所有正在等待的线程
        System.out.println("a");
        flag++;//加一，则为1
        this.notifyAll();//唤醒全部线程，但此时只有b方法的线程执行，因为此时signal为1
    }

    /**
     * 方法2（相当于线程2）
     */
    public synchronized void b() {
        while (flag != 1) {//不为1则等待，等于1时才执行
            try {
                this.wait();//当前线程等待
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //等于1时就执行，同时唤醒所有正在等待的线程
        System.out.println("b");
        flag++;//再加一，则为2
        this.notifyAll();//唤醒全部线程，但此时只有c方法的线程执行，因为此时signal为2
    }

    /**
     * 方法3（相当于线程3）
     */
    public synchronized void c() {
        while (flag != 2) {//不为2则等待，等于2时才执行
            try {
                this.wait();//当前线程等待
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //等于2时就执行，同时唤醒所有正在等待的线程
        System.out.println("c");
        flag = 0;//重新设置为0
        this.notifyAll();//唤醒全部线程，但此时只有a方法的线程执行，因为此时flag又设置为了0
    }
}

/**
 * 方法3：使用lock + condition + await + signal
 */
class Demo03 {
    private int flag;//定义一个标记，用于条件的判断（和之前的Demo是一样的），初始值为0
    Lock lock = new ReentrantLock();//创建一个显示锁
    //再在此锁上绑定三个Condition对象，就可以分别在不同的线程上调用了,太好用了
    Condition con1 = lock.newCondition();
    Condition con2 = lock.newCondition();
    Condition con3 = lock.newCondition();

    //方法1（相当于线程1）
    public void a() {
        lock.lock();//上锁
        while (flag != 0) {
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
        while (flag != 1) {
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
        lock.unlock();//释放锁
    }

    //方法3（相当于线程3）
    public void c() {
        lock.lock();
        while (flag != 2) {
            try {
                con3.await();//让c线程等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("c");
        flag = 0;//又重新设置为0，用于循环顺序打印
        con1.signal();//再次唤醒a线程
        lock.unlock();//释放锁
    }
}