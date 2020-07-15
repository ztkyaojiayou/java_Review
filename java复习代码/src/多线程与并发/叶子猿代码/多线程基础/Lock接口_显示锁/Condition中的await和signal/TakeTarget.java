package 多线程与并发.叶子猿代码.多线程基础.Lock接口_显示锁.Condition中的await和signal;


//消费者，也即买票程序
public class TakeTarget implements Runnable {

    private Tmall tmall;

    public TakeTarget(Tmall tmall) {
        this.tmall = tmall;
    }

    @Override
    public void run() {
        while(true) {
            tmall.take();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

