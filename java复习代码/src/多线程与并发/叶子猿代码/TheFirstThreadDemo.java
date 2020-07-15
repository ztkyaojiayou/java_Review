package 多线程与并发.叶子猿代码;


public class TheFirstThreadDemo implements Runnable {

    @Override
    public synchronized void run() {
        while(true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("自定义的线程执行了....");
        }
    }

    public static void main(String[] args) {

        TheFirstThreadDemo n = new TheFirstThreadDemo();

        // 初始化状态
        Thread thread = new Thread(n); // 创建线程,并指定线程任务

        thread.start(); // 启动线程

        while(true) {
            synchronized (n) {
                System.out.println("主线程执行了...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                n.notifyAll();
            }

        }

    }

}

