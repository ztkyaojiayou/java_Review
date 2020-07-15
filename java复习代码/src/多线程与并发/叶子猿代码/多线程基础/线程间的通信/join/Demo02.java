package 多线程与并发.叶子猿代码.多线程基础.线程间的通信.join;

//join方法：加塞
public class Demo02 {

    public void a(Thread joinThread) {

        System.out.println("方法a执行了...");
        joinThread.start();//在这里开始才启动加塞线程
        try {
            joinThread.join();//调用join方法就表示加塞，即a线程等该线程执行完毕之后，a线程后面的代码才允许
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("a方法执行完毕...");

    }

    public void b() {
        System.out.println("加塞线程开始执行....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("加塞线程执行完毕...");
    }

    public static void main(String[] args) {
        Demo02 demo = new Demo02();
        Thread joinThread = new Thread(new Runnable() {//线程一（加塞线程）的执行任务

            @Override
            public void run() {
                demo.b();//调用b方法
            }
        });//注意，此时还没有启动该线程

        new Thread(new Runnable() {//线程二（直接就包含了执行任务）

            @Override
            public void run() {
                demo.a(joinThread);//调用a方法（包含了加塞线程）
            }
        }).start();//先启动该线程，里面包含了加塞线程

        System.out.println("主线程在这呢————————————————");
    }

}

