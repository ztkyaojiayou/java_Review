package 多线程与并发.叶子猿代码.多线程基础.Lock接口_显示锁;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//测试ReentrantLock的可重入性
public class ReentrantDemo {

    Lock lock = new ReentrantLock();

    //a方法里面调用b方法，但需要获取到它的锁才可以调用
    public void a() {
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }

    public void b() {
        lock.lock();
        System.out.println("b");
        c();
        lock.unlock();
    }

    public void c() {
        lock.lock();
        System.out.println("c");
        lock.unlock();
    }

    public static void main(String[] args) {
        ReentrantDemo d = new ReentrantDemo();

        new Thread(new Runnable() {

            @Override
            public void run() {
                d.a();//调用a方法（a方法中在调用b方法，b方法再调用c方法，但每个方法都上了锁，但是是同一把锁）
            }
        }).start();
        //结论是：可以重入的，即只要都是上的同一把锁，就只需获取一次就可畅通无阻。

//		new Thread(new Demo2.java() {
//
//			@Override
//			public void run() {
//				d.b();
//			}
//		}).start();

    }
}

