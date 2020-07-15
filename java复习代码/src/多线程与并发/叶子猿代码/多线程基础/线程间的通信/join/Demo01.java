package 多线程与并发.叶子猿代码.多线程基础.线程间的通信.join;


public class Demo01 {

    public void target (Thread joinThread) {

        System.out.println("第一步：target 方法执行了...");
        try {
            joinThread.start();//再开启线程2
            joinThread.join();//线程1等待线程2执行完毕后再执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("第三步：join 线程执行完毕...");

    }


    public static void main(String[] args) {
        Demo01 d = new Demo01();
//线程2
        Thread joinThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    d.a();//调用a方法
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
//线程1，先执行，调用线程2
        new Thread(new Runnable() {

            @Override
            public void run() {
                d.target(joinThread);//调用包含线程2的target方法
                System.out.println("最后，轮到我线程1再执行完毕");
            }
        }).start();//开启线程
    }


    protected void a() throws InterruptedException {
        System.out.println("第二步：join 线程进入");
        Thread.sleep(1000);
    }

}

