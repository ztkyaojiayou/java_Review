package 多线程与并发.叶子猿代码.多线程基础.线程间的通信.wait和notify;


public class Target2 implements Runnable {

    private Demo3 demo;

    public Target2(Demo3 demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        demo.get();
    }

}

