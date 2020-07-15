package 多线程与并发.叶子猿代码.多线程基础.生产者消费者模型.使用Lock和Condition实现;


public class TestDemo {

    public static void main(String[] args) {
        //方法1测试
        Shop tmall = new Tmall();//方法1

        PushTarget p = new PushTarget(tmall);
        TakeTarget t = new TakeTarget(tmall);
//        //方法2和3测试
//        Shop tmall2 = new Tmall2();//方法2
//        Shop tmall3 = new Tmall3();//方法3
//
//        PushTarget p2 = new PushTarget(tmall2);
//        TakeTarget t2 = new TakeTarget(tmall2);
//        PushTarget p3 = new PushTarget(tmall3);
//        TakeTarget t3 = new TakeTarget(tmall3);

        //多个线程生产
        new Thread(p).start();
        new Thread(p).start();
//        new Thread(p2).start();
//        new Thread(p2).start();
//        new Thread(p3).start();
//        new Thread(p3).start();

        //多个线程消费
        new Thread(t).start();
        new Thread(t).start();
//        new Thread(t2).start();
//        new Thread(t2).start();
//        new Thread(t3).start();
//        new Thread(t3).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                tmall.size();
            }
        }).start();
    }

}

