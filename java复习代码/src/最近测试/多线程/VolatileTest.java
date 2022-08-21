package 最近测试.多线程;

/**
 * 使用Volatile+flag优雅停止线程
 * @author :zoutongkun
 * @date :2022/8/21 1:24 下午
 * @description :
 * @modyified By:
 */
public class VolatileTest extends Thread {
//若不加Volatile，则主线程无法获取到最新的值，则也无法停止
    private volatile boolean stopFlag = false;

    public boolean isStopFlag() {
        return stopFlag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        stopFlag = true;
        System.out.println(Thread.currentThread().getName() + " stopFlag = " + stopFlag);
    }

    public static void main(String[] args) {
        VolatileTest vt = new VolatileTest();
        vt.start();

        while (true) {
            if (vt.isStopFlag()) {
                System.out.println("stop");
                break;
            }
        }
    }
}




class VolatileTest02 {

//    public  int count = 0;
    //加volatile解决不了，加锁也解决不了，一般就是使用原子类！！！
   public volatile int count = 0;
    public  synchronized void add() {
        count++;
    }

    public static void main(String[] args) {
        final VolatileTest02 test = new VolatileTest02();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    test.add();
                }
            }).start();
        }
//        while (Thread.activeCount() > 2) {
//            //保证前面的线程都执行完
//            Thread.yield();
//        }
        System.out.println(test.count);
    }
}


