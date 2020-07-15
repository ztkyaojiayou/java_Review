package 多线程与并发.叶子猿代码.多线程基础.线程间的通信.wait和notify;


public class Target1 implements Runnable {

    private Demo3 demo;

    public Target1(Demo3 demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        demo.set();
    }

}

