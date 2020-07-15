package 多线程与并发.叶子猿代码.多线程高级.Volatile关键字;


//volatile关键字：
// 两个作用：
// （1）能保证被原子性操作/修改的共享变量的可见性（但保证不了安全性）
// （2）能禁止指令重排
//（但保证不了非原子性操作，而synchronized关键字可以，只是由于其是重量级锁，会消耗系统性能，
// 因此我们有时候会使用volatile关键字，因为它是轻量级锁）
/**
 * 保证可见性的前提：多个线程拿到的是同一把锁，否则是保证不了的。
 *
 * @author worker
 *
 */
//作用1：能保证被原子性操作/修改的共享变量的可见性（但保证不了安全性）
public class Demo {

    public volatile int a = 1;

    public synchronized int getA() {//锁为this，这里是demo对象
        return a++;
    }

    public synchronized void setA(int a) {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.a = a;
    }

    public static void main(String[] args) {

        Demo demo = new Demo();

        demo.a = 10;//修改变量a的值为10，且对所有线程都可见

        //创建一个线程并启动
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(demo.a);//分线程，获取a的值，肯定为10（这里不需要获取锁）
            }
        }).start();

        //主线程
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("最终的结果为：" + demo.getA());//主线程也去获取a的值，肯定也是10

    }

}

