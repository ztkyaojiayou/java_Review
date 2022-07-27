package 数据结构与算法.离职后刷题.第四遍必会版;


//先定义两个锁对象
class MyLock {
    public static final Object lockA = new Object();
    public static final Object lockB = new Object();
}

//线程任务，里面通过一个标志位来巧妙地模拟两个线程任务
//注意：由于是实现了runnable接口，因此这个任务可以被多个线程共用，
// 这也是相比继承thread类的一个优势
class deadLock implements Runnable {
    //加一个标志位
    private boolean flag;

    //构造函数
    deadLock(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        //1.flag为true的线程要执行的任务
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
            //2.flag为false的线程要执行的任务
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
class demo82_死锁案例 {
    public static void main(String[] args) {
        deadLock a = new deadLock(true);//线程a的任务
        deadLock b = new deadLock(false);//线程b的任务
        //创建线程，并加入任务（要实现Runnable接口）
        Thread t1 = new Thread(a);
        Thread t2 = new Thread(b);
        //启动线程
        t1.start();
        t2.start();
    }
}
//测试成功


class TestLock {
    public static final Object lockA1 = new Object();
    public static final Object lockB1 = new Object();
}

class mydeadLock implements Runnable {
    //加一个flag标志位
    boolean flag;

    //构造函数
    public mydeadLock(Boolean flag) {
        this.flag = flag;
    }

    //线程任务
    @Override
    public void run() {
        if (flag) {
            while (true) {
                synchronized (TestLock.lockA1) {
                    System.out.println("lockA1");
                    synchronized (TestLock.lockB1) {
                        System.out.println("lockB1");
                    }
                }
            }
        } else {
            while (true) {
                synchronized (TestLock.lockB1) {
                    System.out.println("lockA1");
                    synchronized (TestLock.lockA1) {
                        System.out.println("lockB1");
                    }
                }
            }
        }
    }
}

class test {
    public static void main(String[] args) {
        //创建两个线程
        //1.创建线程所要执行的任务
        mydeadLock task1 = new mydeadLock(true);
        mydeadLock task2 = new mydeadLock(false);
        //2.创建线程
        Thread thread = new Thread(task1);
        Thread thread1 = new Thread(task2);
        //3.启动线程
        thread.start();
        thread1.start();
    }
}