package 数据结构与算法.第三遍;

/**
 * 方法1：使用join，最简单
 */

public class demo83_多线程按顺序执行 {
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
