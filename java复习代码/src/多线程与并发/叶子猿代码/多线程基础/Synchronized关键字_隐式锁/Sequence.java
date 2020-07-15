package 多线程与并发.叶子猿代码.多线程基础.Synchronized关键字_隐式锁;


/**
 * synchronized的三个用法：
 * 1、同步代码块
 * 2、修饰普通方法
 * 3、修饰静态方法
 */

 class Demo01 {

    private int value;

    /**
     * 1.synchronized 放在普通方法上，内置锁就是当前类的实例对象，使用this或具体实例对象即可获取到锁并调用
     *本例中即为对象s
     * @return
     */
    public synchronized int getNext() {
        System.out.println("普通同步方法执行啦---------------22222222222222");
        return value++;
    }

    /**
     * 2.修饰静态方法，内置锁是当前的Class字节码对象，
     * 即为Demo.class
     *
     * @return
     */
    public static synchronized int getPrevious() {
		//return value --;
        System.out.println("静态同步方法执行啦-----------------33333333333333");
        return 0;
    }

    //3.同步代码块

    /**
     * 底层原理实现：
     * synchronized同步语句块的实现使用的是monitorenter和monitorexit指令，其
     * 中monitorenter指令指向同步代码块的开始位置，monitorexit指令则指明同步代
     * 码块的结束位置。当执行monitorenter指令时，线程试图获取锁也就是获取
     * monitor(monitor对象存在于每个Java对象的对象头中，synchronized 锁便是通过这
     * 种方式获取锁的，也是为什么Java中任意对象可以作为锁的原因)的持有权。当计数器为
     * 0则可以成功获取，获取后将锁计数器设为1也就是加1。相应的在执行monitorexit指令
     * 后，将锁计数器设为0，表明锁被释放。如果获取对象锁失败，那当前线程就要阻塞等
     * 待，直到锁被另外一个线程释放为止。
     * @return
     */
    public int getValue() {
        // 指令monitorenter 指向同步代码块的开始位置，试图获取锁
        synchronized (new Object()) {

            if (value > 0) {
                System.out.println("同步代码块执行啦-----------------11111111");
                return value;
            } else {
                System.out.println("同步代码块执行啦-----------------11111111");
                return -1;
            }

        }
        // 指令monitorexit 指明同步代码块的结束位置，用于释放锁

    }

}
public class Sequence {

    public static void main(String[] args) {

        Demo01 s = new Demo01();
//		while(true) {
//			System.out.println(s.getNext());
//		}

        //创建三个线程，分别执行三个不同的方法
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getValue());
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
                while (true) {
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
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getPrevious());
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

