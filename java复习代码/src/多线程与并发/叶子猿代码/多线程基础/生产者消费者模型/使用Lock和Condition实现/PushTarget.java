package 多线程与并发.叶子猿代码.多线程基础.生产者消费者模型.使用Lock和Condition实现;

//生产者
public class PushTarget implements Runnable {

    private Shop tmall;

    public PushTarget(Shop tmall) {
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

