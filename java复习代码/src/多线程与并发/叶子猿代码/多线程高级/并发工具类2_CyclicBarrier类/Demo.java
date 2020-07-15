package 多线程与并发.叶子猿代码.多线程高级.并发工具类2_CyclicBarrier类;


import java.util.Random;
import java.util.concurrent.CyclicBarrier;

//并发工具类2：CyclicBarrier类（循环屏障类）的使用（其实很简单）

//是什么：也是一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。
//在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时 CyclicBarrier 很有用。
//因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier。

//需求（使用场景）：部门（十个人）开会，但要先等所有人都到达办公室（先到的要等后到的），再正式开会
public class Demo {

    Random random = new Random();

    public void meeting(CyclicBarrier barrier) {//该方法传入了一个CyclicBarrier类
        try {
            Thread.sleep(random.nextInt(4000));
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 到达会议室，等待开会..");

        try {//每个线程在到达屏障点的时候都会调用await方法将自己阻塞，
            // 此时计数器会减1，当计数器减为0的时候所有因调用await方法而被阻塞的线程将被唤醒
            barrier.await();//2.在还有人没有到达会议室（此公共屏障点barrier 上）之前，已经到达的其他人/线程将一直等待（他们的到来）。
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        /**
         * CyclicBarrier 支持一个可选的 Demo2.java 任务，
         * 在一组线程中的最后一个线程到达之后（但在释放所有线程之前）才执行该任务，该命令只在每个屏障点运行一次。
         */
        //创建一个新的 CyclicBarrier，指定部门开会人数（10人）和“开始开会”的操作。
//当所有人都到到达会议室时，开始执行指定操作的线程（正式开会），该操作由最后一个进入 barrier 的线程（开会者）执行。
        CyclicBarrier barrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("好！我们开始开会...");//3.所有人已经全部到齐，开始开会（执行此 Demo2.java 任务）
            }
        });

        for (int i = 0; i < 10; i++) {//0.创建十个线程，模拟员工赶往办公室
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.meeting(barrier);//1.调用meeting(barrier)方法，等所有人都到办公室
                }
            }).start();
        }

        //监控等待线程数
//			new Thread(new Demo2.java() {
//
//				@Override
//				public void run() {
//					while(true) {
//						try {
//							Thread.sleep(1000);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//						//System.out.println("等待的线程数 " + barrier.getNumberWaiting());
//						System.out.println("is broken " + barrier.isBroken());
//					}
//				}
//			}).start();


    }
/**
 * 结果为：
 * Thread-7 到达会议室，等待开会..
 * Thread-3 到达会议室，等待开会..
 * Thread-9 到达会议室，等待开会..
 * Thread-4 到达会议室，等待开会..
 * Thread-1 到达会议室，等待开会..
 * Thread-0 到达会议室，等待开会..
 * Thread-6 到达会议室，等待开会..
 * Thread-2 到达会议室，等待开会..
 * Thread-8 到达会议室，等待开会..
 * Thread-5 到达会议室，等待开会..
 * 好！我们开始开会...
 * }
 */

}

