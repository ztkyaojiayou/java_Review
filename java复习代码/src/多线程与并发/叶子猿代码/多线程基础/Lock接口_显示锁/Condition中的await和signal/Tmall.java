package 多线程与并发.叶子猿代码.多线程基础.Lock接口_显示锁.Condition中的await和signal;



//生产者与消费者模式（其实就是之前讲的买票程序，是多线程里面的经典例子）
//买卖票具体业务实现（重点，涉及到锁）（在“生产者消费者模型”包里面有重复）

//先用原来的方法实现，即Synchronized
public class Tmall {

    private int count;

    public final int MAX_COUNT = 10;

    //卖票
    public synchronized void push () {
        while(count >= MAX_COUNT) {//只能有while，不能使用if
            try {
                System.out.println(Thread.currentThread().getName() + " 库存数量达到上限，生产者停止生产。");
                wait();//当前的卖票线程等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count ++;
        System.out.println(Thread.currentThread().getName() + " 生产者生产，当前库存为：" + count);
        notifyAll();//当为多线程生产消费时，不能用notify，否则可能只把自己方的线程唤醒，则无意义，且易发生所有线程挂起，程序被迫暂停
    }

    //买票
    public synchronized void take () {

        while(count <= 0) {//一样的道理
            try {
                System.out.println(Thread.currentThread().getName() + " 库存数量为零，消费者等待。");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count --;
        System.out.println(Thread.currentThread().getName() + " 消费者消费，当前库存为：" + count);
        notifyAll();//一样的道理
    }

}

