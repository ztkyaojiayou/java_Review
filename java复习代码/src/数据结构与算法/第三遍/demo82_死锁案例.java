package 数据结构与算法.第三遍;

//先定义两个锁对象
class MyLock {
    public static final Object lockA = new Object();
    public static final Object lockB = new Object();
}

//线程任务
class deadLock implements Runnable {
    //加一个标志位
    private boolean flag;

    deadLock(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            while (true) {
                synchronized (MyLock.lockA) {
                    System.out.println("持有了A锁");
                    synchronized (MyLock.lockB) {
                        System.out.println("还想试图获取B锁");
                    }
                }
            }
        } else {
            while (true) {
                synchronized (MyLock.lockB) {
                    System.out.println("持有了B锁");
                    synchronized (MyLock.lockA) {
                        System.out.println("还想试图获取A锁");
                    }
                }
            }
        }
    }
}

//测试
public class demo82_死锁案例 {
    public static void main(String[] args) {
        deadLock a = new deadLock(true);//线程a的任务
        deadLock b = new deadLock(false);//线程b的任务
        Thread t1 = new Thread(a);
        Thread t2 = new Thread(b);
        t1.start();
        t2.start();
    }
}
//测试成功
