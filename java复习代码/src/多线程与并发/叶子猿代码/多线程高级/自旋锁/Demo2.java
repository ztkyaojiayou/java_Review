package 多线程与并发.叶子猿代码.多线程高级.自旋锁;


import java.util.Random;
//自旋锁
/**
 * 多个线程执行完毕之后，打印一句话，结束，但由于自旋锁的存在，结束不了
 * @author worker
 *
 */
public class Demo2 {

    public static void main(String[] args) {

        //创建两个线程并启动
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 线程执行...");

                try {
                    Thread.sleep(new Random().nextInt(2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " 线程执行完毕了...");
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 线程执行...");

                try {
                    Thread.sleep(new Random().nextInt(2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " 线程执行完毕了...");
            }
        }).start();


        while(Thread.activeCount() != 1) {//返回还在活动的线程的数目，此时就只有主线程了，因此会进入自旋状态而无法自拔。
            // 自旋：即自我等待，会一直执行死循环
            System.out.println("自旋的意思就是会一直在这里面运行，相当于死循环，一直都跳不出来");
        }
        System.out.println("所有的线程执行完毕了...（这句话根本执行不了）");
    }

}

