package 多线程与并发.叶子猿代码.多线程基础.死锁;


//死锁，即同步中嵌套同步，注意是可能发生，而不是肯定发生
public class Demo3 {

    private Object obj1 = new Object();
    private Object obj2 = new Object();

    public void a () {
        synchronized (obj1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj2) {
                System.out.println("a");
            }
        }
    }

    public void b () {
        synchronized (obj2) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj1) {
                System.out.println("b");
            }
        }
    }

    public static void main(String[] args) {

        Demo3 d = new Demo3();

        new Thread(new Runnable() {

            @Override
            public void run() {
                d.a();
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                d.b();
            }
        }).start();
    }

}

