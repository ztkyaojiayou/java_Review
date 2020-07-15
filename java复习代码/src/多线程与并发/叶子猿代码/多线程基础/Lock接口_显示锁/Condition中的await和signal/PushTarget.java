package 多线程与并发.叶子猿代码.多线程基础.Lock接口_显示锁.Condition中的await和signal;


//生产者，也即卖票程序
public class PushTarget implements Runnable {

    private Tmall tmall;

    public PushTarget(Tmall tmall) {
        this.tmall = tmall;
    }

    @Override
    public void run() {
        while(true) {
            tmall.push();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

