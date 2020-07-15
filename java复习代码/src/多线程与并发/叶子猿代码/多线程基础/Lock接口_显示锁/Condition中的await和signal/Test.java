package 多线程与并发.叶子猿代码.多线程基础.Lock接口_显示锁.Condition中的await和signal;


//主程序，开始进行多线程测试
public class Test {

    public static void main(String[] args) {

        Tmall tmall = new Tmall();

        PushTarget p = new PushTarget(tmall);
        TakeTarget t = new TakeTarget(tmall);

        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();

        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();


    }

}

