package 多线程与并发.叶子猿代码.多线程基础.生产者消费者模型.使用Lock和Condition实现;

//消费者
public class TakeTarget implements Runnable {

    private Shop tmall;

    public TakeTarget(Shop tmall) {
        this.tmall = tmall;
    }

    @Override
    public void run() {
        while(true) {
            tmall.take();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

