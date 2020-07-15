package 多线程与并发.叶子猿代码.多线程高级.ThreadLocal;


//ThreadLocal:线程的局部变量（也可以解决线程的安全性问题）

/**
 * 原理：
 * 我们知道，多线程访问同一个共享变量的时候容易出现并发问题，特别是多个线程对一个变量进行写入的时候，
 * 为了保证线程安全，一般使用者在访问共享变量的时候需要加各种锁才能保证线程安全性，如synchronized。
 * 而ThreadLocal是除了加锁这种同步方式之外的一种保证在多线程访问下不出现线程安全问题的方法，
 * ThreadLocal是JDK包提供的，它提供线程本地变量，如果创建一个ThreadLocal对象，
 * 那么每个访问这个变量的线程都会有这个变量的一个副本，
 * 在实际多线程操作的时候，操作的是自己本地内存中的变量，从而规避了线程安全问题.
 *
 * 使用步骤：
 * （1）先创建一个ThreadLocal对象
 * （2）再往这个对象里面塞值
 * （3）然后就可以供多个线程安全地获取此对象啦，完美解决问题
 */
public class ThreadLocalDemo {


    //1.先创建一个ThreadLocal对象；各线程操作该对象里面的操作是独立的（关键）
    private ThreadLocal<Integer> count = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
            return new Integer(0);//这个对象里面的初值value的初始值设置为0
        };
    };

    public int getNext() {
        //2.再使用这个对象来对value值执行“加一”操作
        //（先从该对象里取value，再对其加1，再把更新后的值塞进该对象里)；
        Integer value = count.get();//2.1取值
        value++;//2.2操作该值
        count.set(value);//2.3再往这个对象里面塞值/更新值
        return value;
    }

    public static void main(String[] args) {
        ThreadLocalDemo d = new ThreadLocalDemo();

        //创建两个线程并启动
        new Thread(new Runnable() {//线程一
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + d.getNext());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {//线程二
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + d.getNext());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
/**
 * 结果如下：（单独看线程1和线程2，会发现，各自的增值是不影响的）
 * Thread-0 1
 * Thread-1 1
 * Thread-1 2
 * Thread-1 3
 * Thread-1 4
 * Thread-0 2
 * Thread-1 5
 * Thread-1 6
 * Thread-1 7
 * Thread-1 8
 * Thread-0 3
 */
}
